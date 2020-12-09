package com.donymarulloh.testalodokter.ui.login

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.donymarulloh.testalodokter.data.model.login.LoginResponse
import com.donymarulloh.testalodokter.databinding.ActivityLoginBinding
import com.donymarulloh.testalodokter.ui.base.BaseActivity
import com.donymarulloh.testalodokter.ui.main.MainActivity
import com.donymarulloh.testalodokter.util.ext.observe
import org.koin.android.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity() {

    private val viewModel by viewModel<LoginViewModel>()
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

    }

    override fun observeChange() {
        observe(viewModel.loginLiveData, ::handleLoginResult)
        observe(viewModel.errorMessage, ::showSnackbarMessage)
        observe(viewModel.loading, ::loadingLogin)
    }

    fun loadingLogin(loaded: Boolean) {
        if (loaded){
            binding.login.visibility = View.GONE
            binding.loaderView.visibility = View.VISIBLE
        } else {
            binding.loaderView.visibility = View.GONE
            binding.login.visibility = View.VISIBLE
        }
    }


    private fun handleLoginResult(status: LoginResponse) {

        MainActivity.startActivity(this)
        finish()
    }

    private fun initView() {
        binding.login.setOnClickListener {
            Log.d("masuk","click")
            doLogin() }
    }

    private fun doLogin() {
        Log.d("masuk","doLogin")
        viewModel.doLogin(binding.username.text.trim().toString(),
            binding.password.text.toString())
    }

    companion object {
        fun startActivity(context: Context?) = context?.startActivity(
            Intent(context, LoginActivity::class.java)
        )
    }
}
