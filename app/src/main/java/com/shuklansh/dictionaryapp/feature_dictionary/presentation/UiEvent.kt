package com.shuklansh.dictionaryapp.feature_dictionary.presentation

sealed class UiEvent{
    data class showSnackBar(val message : String) : UiEvent()
}
