package com.dzenm.composeui.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainModel : ViewModel() {

    // 选中的标签
    private val _selectedIndex = MutableLiveData<Int>()

    /**
     * 更新选中的下标
     */
    fun updateSelectedIndex(index: Int) {
        _selectedIndex.value = index
    }

    /**
     * 获取选中的下标
     */
    val selectedIndex: LiveData<Int>
        get() = _selectedIndex
}