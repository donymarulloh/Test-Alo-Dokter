package com.donymarulloh.testalodokter.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.donymarulloh.testalodokter.databinding.ActivitySplashBinding
import com.donymarulloh.testalodokter.ui.base.BaseActivity
import com.donymarulloh.testalodokter.ui.login.LoginActivity
import com.donymarulloh.testalodokter.ui.main.MainActivity
import com.donymarulloh.testalodokter.util.ext.observe
import org.koin.android.viewmodel.ext.android.viewModel


class SplashActivity : BaseActivity() {

    private val viewModel by viewModel<SplashViewModel>()

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.startSplash()
    }

    override fun observeChange() {
        observe(viewModel.statusLogin, ::onSplash)
    }


    private fun onSplash(data: Boolean) {
        if(data){
            MainActivity.startActivity(this)
            finish()
        }else {
            LoginActivity.startActivity(this)
            finish()
        }
    }


    companion object {
        fun startActivity(context: Context?) = context?.startActivity(
            Intent(context, SplashActivity::class.java)
        )
    }

}