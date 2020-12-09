package com.donymarulloh.testalodokter.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.donymarulloh.testalodokter.databinding.FragmentProfileBinding
import com.donymarulloh.testalodokter.ui.base.BaseFragment
import com.donymarulloh.testalodokter.ui.login.LoginActivity
import com.donymarulloh.testalodokter.ui.main.MainActivity
import com.donymarulloh.testalodokter.util.ext.observe
import org.koin.android.viewmodel.ext.android.sharedViewModel


class ProfileFragment : BaseFragment() {

    private val viewModel by sharedViewModel<ProfileViewModel>()

    lateinit var binding: FragmentProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding) {
                binding.logout.setOnClickListener { viewModel.doLogout() }

        }
    }


    override fun observeChange() {
        observe(viewModel.statusLogout, ::onSplash)

    }

    private fun onSplash(data: Boolean) {
            LoginActivity.startActivity(activity)
            activity!!.finish()
    }


    companion object {
        fun newInstance() = ProfileFragment()
    }

}