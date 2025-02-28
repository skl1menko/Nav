package com.example.nav

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {
    private val _currentTab = MutableStateFlow(Tab.Alc)
    val currentTab: StateFlow<Tab> = _currentTab

    fun onTabSelected(tab: Tab) {
        _currentTab.value = tab
    }
}