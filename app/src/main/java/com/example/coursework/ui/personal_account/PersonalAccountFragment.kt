package com.example.coursework.ui.personal_account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.coursework.R
import com.example.coursework.user.User
import com.example.coursework.user.User.Companion.user
import com.example.coursework.user.User.Companion.userLog
import com.example.coursework.user.UserLogin
import com.google.android.material.textfield.TextInputLayout


class PersonalAccountFragment : Fragment() {

    private var loginButton : Button? = null
    private var exitButton : Button? = null
    private var textBirthDate : TextInputLayout? = null
    private var textEmail : TextInputLayout? = null
    private var textGroup : TextInputLayout? = null
    private var textCourse : TextInputLayout? = null
    private var textSpecialty : TextInputLayout? = null
    private var textStatus : TextView? = null
    private var textName : TextView? = null


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_personal_account, container, false)

        loginButton = root.findViewById(R.id.buttonLogUser)
        exitButton = root.findViewById(R.id.personal_exit_button)
        textBirthDate = root.findViewById(R.id.personal_birth_date)
        textEmail = root.findViewById(R.id.personal_email)
        textGroup = root.findViewById(R.id.personal_group)
        textCourse = root.findViewById(R.id.personal_course)
        textName = root.findViewById(R.id.personal_name)
        textSpecialty = root.findViewById(R.id.personal_specialty)
        textStatus = root.findViewById(R.id.personal_status)

        if (!userLog){
            startActivity(Intent(activity, UserLogin::class.java))
        }

        loginButton?.setOnClickListener {
            //TODO: exit current user
            userLog = false
            startActivity(Intent(activity, UserLogin::class.java))
        }

        exitButton?.setOnClickListener {
            exitUser()
        }

        return root
    }

    private fun changeVisibility(state: Boolean) {
        exitButton?.isVisible = state
        textBirthDate?.isVisible = state
        textEmail?.isVisible = state
        textGroup?.isVisible = state
        textCourse?.isVisible = state
        textName?.isVisible = state
        loginButton?.isVisible = !state
    }

    private fun exitUser() {
        val builder = android.app.AlertDialog.Builder(activity)
        builder.setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
            userLog = false
            startActivity(Intent(activity, UserLogin::class.java))
        }
        builder.setNegativeButton(resources.getString(R.string.no)) { _, _ -> }
        builder.setTitle(resources.getString(R.string.exit))
        builder.setMessage(resources.getString(R.string.exit_sure))
        builder.create().show()
    }

    private fun setText() {
        textName?.text = "${user.name} ${user.surname} ${user.middleName}"
        textBirthDate?.editText?.setText(user.birthday)
        textEmail?.editText?.setText(user.email)
        textGroup?.editText?.setText(user.groupName.toString())
        textCourse?.editText?.setText(user.courseNumber.toString())
        textSpecialty?.editText?.setText(user.specialty)
        textStatus?.text = user.status.toString()
    }


    override fun onStart() {
        super.onStart()
        setText()
        changeVisibility(userLog)
    }

}