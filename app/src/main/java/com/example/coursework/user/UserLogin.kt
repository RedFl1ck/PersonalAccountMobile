package com.example.coursework.user

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.coursework.MainViewModel
import com.example.coursework.MainViewModelFactory
import com.example.coursework.R
import com.example.coursework.model.UserApi
import com.example.coursework.repository.Repository
import com.example.coursework.ui.about.AboutActivity
import com.example.coursework.user.User.Companion.user
import com.example.coursework.user.User.Companion.userLog
import com.google.android.material.textfield.TextInputLayout


class UserLogin : AppCompatActivity() {

    private var loginButton : Button? = null
    private var registerButton : Button? = null
    //private var forgetPassButton : Button? = null
    private var aboutButton : Button? = null
    private var textEmail : TextInputLayout? = null
    private var textPass : TextInputLayout? = null

    private  lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)
        supportActionBar?.hide()

        loginButton = findViewById(R.id.buttonLogIN)
        registerButton = findViewById(R.id.buttonReg)
        //forgetPassButton = findViewById(R.id.buttonForgetPass)
        textEmail = findViewById(R.id.log_e_mail)
        textPass = findViewById(R.id.log_password)
        aboutButton = findViewById(R.id.about_button)

        setListeners()
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    private fun validation(){
        validateEmail(textEmail)
        validatePass(textPass)
        if (validateEmail(textEmail) &&
            validatePass(textPass)){
            if(isOnline(applicationContext)) {
                val name = textEmail?.editText?.text.toString()
                val pass = textPass?.editText?.text.toString()
                val repository = Repository()
                val viewModelFactory = MainViewModelFactory(repository)
                viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
                viewModel.auth(UserApi(name, pass))
                viewModel.myResponse.observe(this, {
                    userLog = if(it.code() == 200){
                        Log.d("Response", it.body().toString())
                        user.id = it.body()?.result?.id!!
                        user.name = it.body()?.result?.name!!
                        user.surname = it.body()?.result?.surname!!
                        user.middleName = it.body()?.result?.middleName!!
                        val date = it.body()?.result?.birthday!!.toString().split('T').toTypedArray()
                        user.birthday = date[0]
                        user.email = it.body()?.result?.email!!
                        user.specialty = it.body()?.result?.specialty!!
                        user.groupName = it.body()?.result?.groupName
                        user.courseNumber = it.body()?.result?.courseNumber
                        user.status = it.body()?.result?.status!!
                        finish()
                        true
                    } else {
                        Log.d("Response", it.toString())
                        textEmail?.error = it.message()
                        false
                    }
                })
            } else {
                Toast.makeText(applicationContext, resources.getString(R.string.no_internet_access), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validateEmail(field: TextInputLayout?): Boolean {
        val temp = field?.editText?.text.toString()
        return if (temp.isEmpty()){
            field?.error = resources.getString(R.string.error_empty)
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(temp).matches()){
            field?.error = resources.getString(R.string.error_wrong_e_mail)
            false
        } else {
            //TODO: auth check
            field?.error = null
            true
        }
    }

    private fun validatePass(field: TextInputLayout?): Boolean {
        val temp = field?.editText?.text.toString()
        return when {
            temp.isEmpty() -> {
                field?.error = resources.getString(R.string.error_empty)
                false
            }
            temp.count() < 4 -> {
                //TODO: auth check
                field?.error = resources.getString(R.string.error_short_password)
                false
            }
            else -> {
                field?.error = null
                true
            }
        }
    }

    private fun setListeners(){
        aboutButton?.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
        registerButton?.setOnClickListener {
            startActivity(Intent(this, UserRegister::class.java))
        }
        loginButton?.setOnClickListener {
            validation()
        }
        /*forgetPassButton?.setOnClickListener {
            startActivity(Intent(this, UserForgetPassword::class.java))
        }*/
        textPass?.editText?.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                validation()
                true
            } else {
                false
            }
        }
        textEmail?.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateEmail(textEmail)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateEmail(textEmail)
            }
        })
        textPass?.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validatePass(textPass)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatePass(textPass)
            }
        })
    }

    override fun onBackPressed() {}
}