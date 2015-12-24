package com.benny.library.kbinding.view

import com.benny.library.kbinding.bind.BindingDisposer
import com.benny.library.kbinding.bind.ItemViewModel
import com.benny.library.kbinding.bind.ViewModel

/**
 * Created by benny on 12/23/15.
 */
interface BindingDisposerGenerator {
    val bindingDisposer: BindingDisposer

    fun sViewCreator(viewBinderComponent: ViewBinderComponent<*>, viewModelFactory: () -> ItemViewModel<*>): ViewCreator = ViewCreator(bindingDisposer, viewBinderComponent, viewModelFactory)
    fun ViewBinder.sBindTo(viewModel: ViewModel) = this.bindTo(bindingDisposer, viewModel)

}