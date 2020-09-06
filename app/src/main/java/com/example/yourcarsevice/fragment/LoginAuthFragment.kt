package com.example.yourcarsevice.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.yourcarsevice.R
import com.example.yourcarsevice.model.retrofit.user.User
import com.google.android.material.textfield.TextInputLayout
import com.example.yourcarsevice.viewmodel.UserViewModel

class LoginAuthFragment : Fragment() {

    private val userViewModel by viewModels<UserViewModel>()

    private lateinit var textLoginInputEmail: TextInputLayout
    private lateinit var textLoginInputPassword: TextInputLayout
    private val sharedPrefs by lazy {
        context?.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         if (sharedPrefs?.getString(BEARER_TOKEN, "error") != "error") {
            findNavController().navigate(R.id.action_LoginFragment_to_PartFragment)
        }
        textLoginInputEmail = view.findViewById(R.id.textLoginInputEmail)
        textLoginInputPassword = view.findViewById(R.id.textLoginInputPassword)

        view.findViewById<Button>(R.id.button_log_in).setOnClickListener {
            userViewModel.getTokenUser(
                User(
                    null, textLoginInputEmail.editText?.text.toString(),
                    textLoginInputPassword.editText?.text.toString()
                )
            )
            findNavController().navigate(R.id.action_LoginFragment_to_PartFragment)
        }

        view.findViewById<Button>(R.id.button_registration).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}


