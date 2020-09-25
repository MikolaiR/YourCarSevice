package com.example.yourcarsevice.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.example.yourcarsevice.R
import com.example.yourcarsevice.model.retrofit.user.User
import com.example.yourcarsevice.viewmodel.UserViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.content_main.*

const val PREFS_NAME = "token_prefs"
const val BEARER_TOKEN = "token_bearer"

class RegistrationAuthFragment : Fragment() {

    private val userViewModel by viewModels<UserViewModel>()

    private lateinit var textInputEmail: TextInputLayout
    private lateinit var textInputName: TextInputLayout
    private lateinit var textInputPassword: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textInputEmail = view.findViewById(R.id.textInputEmail)
        textInputName = view.findViewById(R.id.textInputName)
        textInputPassword = view.findViewById(R.id.textInputPassword)

        view.findViewById<Button>(R.id.button_registration).setOnClickListener {
            userViewModel.registrationAndGetToken(
                User(
                    textInputName.editText?.text.toString(),
                    textInputEmail.editText?.text.toString(),
                    textInputPassword.editText?.text.toString()
                ),nav_host_fragment,true
            )
        }
    }
}