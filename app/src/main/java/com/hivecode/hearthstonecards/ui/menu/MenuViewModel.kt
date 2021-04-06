package com.hivecode.hearthstonecards.ui.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hivecode.hearthstonecards.base.Navigation

class MenuViewModel: ViewModel() {
    var selectedItem = String()
    val navigation = MutableLiveData<Navigation>()

    fun onSelectedItem() {
        navigation.value = Navigation(selectedItem)
    }
}