package com.donymarulloh.testalodokter.ui.main

import com.donymarulloh.testalodokter.data.repository.Repository
import com.donymarulloh.testalodokter.ui.base.BaseViewModel
import com.donymarulloh.testalodokter.util.rx.SchedulerProvider

class MainViewModel (
    private val appRepository: Repository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel(){

}