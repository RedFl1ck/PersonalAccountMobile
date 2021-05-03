package com.example.coursework.user

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.coursework.R
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern


class UserRegister : AppCompatActivity() {

    private var regButton : Button? = null

    private var textName : TextInputLayout? = null
    private var textSurname : TextInputLayout? = null
    private var textPatronymic : TextInputLayout? = null
    private var textBirthDate : TextInputLayout? = null
    private var textPhoneNum : TextInputLayout? = null
    private var textEmail : TextInputLayout? = null
    private var textPass : TextInputLayout? = null
    private var textPassSubmit : TextInputLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_register)

        supportActionBar?.hide()

        regButton = findViewById(R.id.buttonRegIN)
        textName = findViewById(R.id.reg_name)
        textSurname = findViewById(R.id.reg_surname)
        textPatronymic = findViewById(R.id.reg_patronymic)
        textBirthDate = findViewById(R.id.reg_birth_date)
        textPhoneNum = findViewById(R.id.reg_phone_num)
        textEmail = findViewById(R.id.reg_e_mail)
        textPass = findViewById(R.id.reg_password)
        textPassSubmit = findViewById(R.id.reg_password_submit)

        setListeners()
    }

    private fun validation(){
        validateName(textName)
        validateSurname(textSurname)
        validatePatronymic(textPatronymic)
        validateBirthDate(textBirthDate)
        validatePhoneNum(textPhoneNum)
        validateEmail(textEmail)
        validatePass(textPass)
        validatePassSubmit(textPassSubmit)
        if (validateName(textName) &&
            validateSurname(textSurname) &&
            validatePatronymic(textPatronymic) &&
            validateBirthDate(textBirthDate) &&
            validatePhoneNum(textPhoneNum) &&
            validateEmail(textEmail) &&
            validatePass(textPass) &&
            validatePassSubmit(textPassSubmit)){
            val name = textName?.editText?.text.toString()
            val surname = textSurname?.editText?.text.toString()
            val patronymic = textPatronymic?.editText?.text.toString()
            val birthDate = textBirthDate?.editText?.text.toString()
            val phoneNum = textPhoneNum?.editText?.text.toString()
            val email = textEmail?.editText?.text.toString()
            val pass = textPass?.editText?.text.toString()
            val passSubmit = textPassSubmit?.editText?.text.toString()
            // TODO: send data
        }
    }

    private fun validateName(field: TextInputLayout?): Boolean {
        val temp = field?.editText?.text.toString()
        return if (temp.isEmpty()){
            field?.error = resources.getString(R.string.error_empty)
            false
        } else {
            field?.error = null
            true
        }
    }

    private fun validateSurname(field: TextInputLayout?): Boolean {
        val temp = field?.editText?.text.toString()
        return if (temp.isEmpty()){
            field?.error = resources.getString(R.string.error_empty)
            false
        } else {
            field?.error = null
            true
        }
    }

    private fun validatePatronymic(field: TextInputLayout?): Boolean {
        val temp = field?.editText?.text.toString()
        return if (temp.isEmpty()){
            field?.error = resources.getString(R.string.error_empty)
            false
        } else {
            field?.error = null
            true
        }
    }

    private fun validateBirthDate(field: TextInputLayout?): Boolean {
        val temp = field?.editText?.text.toString()
        return if (temp.isEmpty()){
            field?.error = resources.getString(R.string.error_empty)
            false
        } else if (!Pattern.matches("\\d\\d.\\d\\d.\\d\\d\\d\\d", temp)){
            field?.error = resources.getString(R.string.error_wrong_birth_date)
            false
        } else {
            field?.error = null
            true
        }
    }

    private fun validatePhoneNum(field: TextInputLayout?): Boolean {
        val temp = field?.editText?.text.toString()
        return if (temp.isEmpty()){
            field?.error = resources.getString(R.string.error_empty)
            false
        } else if (!Patterns.PHONE.matcher(temp).matches()){
            field?.error = resources.getString(R.string.error_wrong_phone)
            false
        } else {
            field?.error = null
            true
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
            field?.error = resources.getString(R.string.error_short_password)
            false
        } else {
            field?.error = null
            true
        }
    }

    private fun validatePassSubmit(field: TextInputLayout?): Boolean {
        val temp = field?.editText?.text.toString()
        return if (temp.isEmpty()){
            field?.error = resources.getString(R.string.error_empty)
            false
        } else if (temp != textPass?.editText?.text.toString()){
            field?.error = resources.getString(R.string.error_match_password)
            false
        } else {
            field?.error = null
            true
        }
    }

    private fun setListeners(){
        regButton?.setOnClickListener {
            validation()
        }
        textPassSubmit?.editText?.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                validation()
                true
            } else {
                false
            }
        }
        textName?.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateName(textName)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateName(textName)
            }
        })
        textSurname?.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateSurname(textSurname)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateSurname(textSurname)
            }
        })
        textPatronymic?.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validatePatronymic(textPatronymic)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatePatronymic(textPatronymic)
            }
        })
        textBirthDate?.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateBirthDate(textBirthDate)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateBirthDate(textBirthDate)
            }
        })
        textPhoneNum?.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validatePhoneNum(textPhoneNum)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatePhoneNum(textPhoneNum)
            }
        })
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
        textPassSubmit?.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validatePassSubmit(textPassSubmit)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatePassSubmit(textPassSubmit)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun close(view: View) {
        finish()
    }

}