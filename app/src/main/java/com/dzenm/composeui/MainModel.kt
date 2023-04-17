package com.dzenm.composeui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainModel : ViewModel() {

    private var count = 0
    private val counter = MutableLiveData<Int>()

    fun increase() {
        counter.value = ++count
    }

    val counterLiveData: LiveData<Int>
        get() = counter

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