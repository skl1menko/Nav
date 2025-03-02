package com.example.nav

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _currentTab = MutableStateFlow(Tab.Alc)
    val currentTab: StateFlow<Tab> = _currentTab.asStateFlow()

    fun onTabSelected(tab: Tab) {
        _currentTab.value = tab
    }
}
