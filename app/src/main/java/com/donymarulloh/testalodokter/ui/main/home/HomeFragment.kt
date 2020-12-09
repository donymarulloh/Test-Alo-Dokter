package com.donymarulloh.testalodokter.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.donymarulloh.testalodokter.R
import com.donymarulloh.testalodokter.data.model.gambar.ImagesItem
import com.donymarulloh.testalodokter.databinding.FragmentHomeBinding
import com.donymarulloh.testalodokter.ui.adapter.createAdapter
import com.donymarulloh.testalodokter.ui.adapter.viewholder.GambarItem
import com.donymarulloh.testalodokter.ui.base.BaseFragment
import com.donymarulloh.testalodokter.ui.base.BaseViewItem
import com.donymarulloh.testalodokter.ui.detail.DetailActivity
import com.donymarulloh.testalodokter.util.ext.observe
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.rxbinding.widget.RxTextView
import org.koin.android.viewmodel.ext.android.sharedViewModel
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class HomeFragment : BaseFragment() {

    private val viewModel by sharedViewModel<HomeViewModel>()

    lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getList()
    }

    private fun initView() {
        with(binding) {
            recyclerView.adapter = sourceAdapter
            recyclerView.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))

        }
    }

    private val sourceAdapter by lazy {
        createAdapter(
            ::onItemClick
        )
    }


    override fun observeChange() {
        observe(viewModel.loading, ::loading)
        observe(viewModel.errorMessage, ::showSnackbarMessage)
        observe(viewModel.sourcesListViewItems, ::onListChanged)

    }

    fun loading(b: Boolean) {
        Log.d("Loading","${b.toString()}")
    }

    private fun onListChanged(data: List<BaseViewItem>) {
        sourceAdapter.submitList(data)
    }

    private fun swipeLoading(loading: Boolean) {
//        with(binding.swipeRefresh) {
//            post { isRefreshing = loading }
//        }
    }


    private fun onItemClick(item: BaseViewItem, view: View) {
        when (item) {
            is GambarItem -> {
                when (view.id) {
                    R.id.root -> {
                        item?.let {

                            val gson = Gson()
                            val gsonPretty = GsonBuilder().setPrettyPrinting().create()

                            val jsonTutsList: String = gson.toJson(item.images)
                            println(jsonTutsList)

                            DetailActivity.startActivity(context,jsonTutsList)
                        }
                    }
                }
            }
        }
    }


    companion object {
        fun newInstance() = HomeFragment()
    }

}