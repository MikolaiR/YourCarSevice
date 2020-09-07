package com.example.yourcarsevice.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.yourcarsevice.R
import com.example.yourcarsevice.databinding.FragmentAuthLoginBinding
import com.example.yourcarsevice.model.retrofit.user.User
import com.google.android.material.textfield.TextInputLayout
import com.example.yourcarsevice.viewmodel.UserViewModel
const val IMAGE_URL = "https://st.depositphotos.com/1001911/1438/v/450/depositphotos_14382675-stock-illustration-v-sign-emoticon.jpg"

class LoginAuthFragment : Fragment() {

    private val userViewModel by viewModels<UserViewModel>()
    private lateinit var fragmentAuthLoginBinding: FragmentAuthLoginBinding


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
        fragmentAuthLoginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_auth_login,container,false)
        fragmentAuthLoginBinding.clickHandler = LoginAuthFragmentHandlers()
        Glide.with(requireContext()).load(IMAGE_URL).into(fragmentAuthLoginBinding.imageViewTitle)
        return fragmentAuthLoginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         if (sharedPrefs?.getString(BEARER_TOKEN, "error") != "error") {
            findNavController().navigate(R.id.action_loginFragment_to_partFragment)
        }
    }

    inner class LoginAuthFragmentHandlers{
        fun onClickLogin(view: View){
            userViewModel.getTokenUser(
                User(
                    null, fragmentAuthLoginBinding.textLoginInputEmail.editText?.text.toString(),
                    fragmentAuthLoginBinding.textLoginInputPassword.editText?.text.toString()
                )
            )
            findNavController().navigate(R.id.action_loginFragment_to_partFragment)
        }

        fun onClickRegistration(view: View){
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

}


