package com.donymarulloh.testalodokter.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.donymarulloh.testalodokter.R
import com.donymarulloh.testalodokter.databinding.ActivityMainBinding
import com.donymarulloh.testalodokter.ui.base.BaseActivity
import com.donymarulloh.testalodokter.ui.main.home.HomeFragment
import com.donymarulloh.testalodokter.ui.main.profile.ProfileFragment
import com.donymarulloh.testalodokter.ui.splash.SplashViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.android.viewmodel.ext.android.viewModel

const val TOTAL_STATE = 0
const val DELTA_STATE = 1


class MainActivity : BaseActivity() {
    private val viewModel by viewModel<SplashViewModel>()

    private lateinit var binding: ActivityMainBinding

    private var currentState = TOTAL_STATE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatePageAdapter()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.pager.currentItem = tab.position
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                val count = fm.backStackEntryCount
                if (count >= 1) {
                    supportFragmentManager.popBackStack()
                }
                ft.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // setAdapter();


            }

            override fun onTabReselected(tab: TabLayout.Tab) {

                //   viewPager.notifyAll();
            }
        })
    }


    private fun setStatePageAdapter(){

        val myViewPageStateAdapter:MyViewPageStateAdapter = MyViewPageStateAdapter(supportFragmentManager)
        myViewPageStateAdapter.addFragment(HomeFragment(),"")
        myViewPageStateAdapter.addFragment(ProfileFragment(),"")
        binding.pager.adapter=myViewPageStateAdapter
        binding.tabLayout.setupWithViewPager(binding.pager,true)
        setupTabIcons()
    }

    private fun setupTabIcons() {
        binding.tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_home_white)
        binding.tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_account_circle_white)
    }


    override fun observeChange() {

    }


    companion object {
        fun startActivity(context: Context?) = context?.startActivity(
            Intent(context, MainActivity::class.java)
        )
    }

}

class MyViewPageStateAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){
    val fragmentList:MutableList<Fragment> = ArrayList<Fragment>()
    val fragmentTitleList:MutableList<String> = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList.get(position)
    }

    fun addFragment(fragment:Fragment,title:String){
        fragmentList.add(fragment)
        fragmentTitleList.add(title)

    }
}
