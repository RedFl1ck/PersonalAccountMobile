package com.example.coursework.user

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import com.example.coursework.R
import com.google.android.material.textfield.TextInputLayout

class RecoveryPasswordPhone : Fragment() {

    private var textPhoneNum : TextInputLayout? = null
    private var submitButton : Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_recovery_password_phone, container, false)

        textPhoneNum = root.findViewById(R.id.recovery_pass_phone)
        submitButton = root.findViewById(R.id.recovery_pass_phone_button)

        setListeners()

        return root
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

    private fun validation(){
        validatePhoneNum(textPhoneNum)
        if (validatePhoneNum(textPhoneNum)){
            val phoneNum = textPhoneNum?.editText?.text.toString()
            // TODO: send data
        }
    }

    private fun setListeners(){
        submitButton?.setOnClickListener {
            validation()
        }
        textPhoneNum?.editText?.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                validation()
                true
            } else {
                false
            }
        }
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
    }
}