package com.amether.kmmpager.di

import com.amether.kmmpager.MainViewModel
import org.kodein.di.*

class DiModule : DIAware {

    override val di: DI by DI.lazy {
        bindProvider { MainViewModel() }
    }

    val viewModel: MainViewModel by di.instance()
}