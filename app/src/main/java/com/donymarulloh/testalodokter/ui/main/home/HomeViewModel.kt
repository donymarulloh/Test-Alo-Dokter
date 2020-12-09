package com.donymarulloh.testalodokter.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.donymarulloh.newsapp.data.model.sources.Sources
import com.donymarulloh.testalodokter.data.mapper.SourcesDataMapper
import com.donymarulloh.testalodokter.data.model.gambar.Gambar
import com.donymarulloh.testalodokter.data.repository.Repository
import com.donymarulloh.testalodokter.ui.adapter.viewholder.LoadingStateItem
import com.donymarulloh.testalodokter.ui.base.BaseViewItem
import com.donymarulloh.testalodokter.ui.base.BaseViewModel
import com.donymarulloh.testalodokter.util.Constant
import com.donymarulloh.testalodokter.util.SingleLiveEvent
import com.donymarulloh.testalodokter.util.ext.addTo
import com.donymarulloh.testalodokter.util.rx.SchedulerProvider

class HomeViewModel (
    private val appRepository: Repository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private var sourcesList = listOf<Gambar>()

    private val _sourcesListViewItems = MutableLiveData<List<BaseViewItem>>()
    val sourcesListViewItems: LiveData<List<BaseViewItem>> get() = _sourcesListViewItems

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    val errorMessage = SingleLiveEvent<String>()


    fun getList() {
        _sourcesListViewItems.value = listOf(LoadingStateItem())

        appRepository.getGambar().observeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .map {
                sourcesList = it
                val transformedList = SourcesDataMapper.transform(it).toMutableList()

                transformedList.toList()
            }
            .subscribe({
                _sourcesListViewItems.postValue(it)
            }, {
                it.printStackTrace()
                errorMessage.postValue(Constant.ERROR_MESSAGE)
            })
            .addTo(compositeDisposable)

    }



}