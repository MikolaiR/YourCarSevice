package com.example.yourcarsevice.view.authorization

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.yourcarsevice.R
import com.example.yourcarsevice.databinding.FragmentAuthLoginBinding
import com.example.yourcarsevice.model.retrofit.user.User
import com.example.yourcarsevice.view.PartActivity
import com.example.yourcarsevice.viewmodel.UserViewModel

const val IMAGE_URL = "https://i112.fastpic.ru/big/2020/0917/24/62eea0c37ea28dd0ca578de7f36ed624.png"

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
            startActivity(Intent(context, PartActivity::class.java))
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
        }

        fun onClickRegistration(view: View){
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }
}


