package com.example.coursework.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coursework.MainViewModel
import com.example.coursework.MainViewModelFactory
import com.example.coursework.R
import com.example.coursework.model.UserApi
import com.example.coursework.repository.Repository
import com.example.coursework.user.User.Companion.user
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import java.security.AccessController.getContext


class UserLogin : AppCompatActivity() {

    private var loginButton : Button? = null
    private var registerButton : Button? = null
    private var forgetPassButton : Button? = null
    private var textEmail : TextInputLayout? = null
    private var textPass : TextInputLayout? = null

    private  lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)
        supportActionBar?.hide()

        loginButton = findViewById(R.id.buttonLogIN)
        registerButton = findViewById(R.id.buttonReg)
        forgetPassButton = findViewById(R.id.buttonForgetPass)
        textEmail = findViewById(R.id.log_e_mail)
        textPass = findViewById(R.id.log_password)

        setListeners()
    }

    private fun validation(){
        validateEmail(textEmail)
        validatePass(textPass)
        if (validateEmail(textEmail) &&
            validatePass(textPass)){
            val name = textEmail?.editText?.text.toString()
            val pass = textPass?.editText?.text.toString()
            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
            viewModel.auth(UserApi(name, pass))
            viewModel.myResponse.observe(this, Observer {
                user = if(it.isSuccessful){
                    Log.d("Response", it.toString())
                    finish()
                    true
                }else {
                    /*val jsonArray = JSONArray(it)
                    val jsonObject: JSONObject = jsonArray.getJSONObject(0)
                    val date = jsonObject.get("code")
                    Log.d("Response", date.toString())*/
                    Log.d("Response", it.toString())
                    textEmail?.editText?.setText(it.toString())
                    false
                }
            })
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
        return if (temp.isEmpty()){
            field?.error = resources.getString(R.string.error_empty)
            false
        } else if (temp.count() < 4){
            //TODO: auth check
            field?.error = resources.getString(R.string.error_short_password)
            false
        } else {
            field?.error = null
            true
        }
    }

    private fun setListeners(){
        registerButton?.setOnClickListener {
            startActivity(Intent(this, UserRegister::class.java))
        }
        loginButton?.setOnClickListener {
            validation()
        }
        forgetPassButton?.setOnClickListener {
            startActivity(Intent(this, UserForgetPassword::class.java))
        }
        textPass?.editText?.setOnEditorActionListener { v, actionId, event ->
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