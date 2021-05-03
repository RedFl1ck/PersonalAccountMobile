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
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.example.coursework.R
import com.google.android.material.textfield.TextInputLayout

class RecoveryPasswordEmail : Fragment() {

    private var textEmail: TextInputLayout? = null
    private var submitButton : Button? = null
    private var linearLayout : LinearLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_recovery_password_email, container, false)

        textEmail = root.findViewById(R.id.recovery_pass_e_mail)
        submitButton = root.findViewById(R.id.recovery_pass_e_mail_button)
        linearLayout = root.findViewById(R.id.recovery_linear_layout)

        setListeners()

        return root
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

    private fun validation(){
        validateEmail(textEmail)
        if (validateEmail(textEmail)){
            val phoneNum = textEmail?.editText?.text.toString()
            // TODO: send data
        }
    }

    private fun setListeners(){
        submitButton?.setOnClickListener {
            validation()
        }
        textEmail?.editText?.setOnEditorActionListener { v, actionId, event ->
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
    }
}