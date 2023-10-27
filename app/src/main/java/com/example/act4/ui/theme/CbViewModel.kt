package com.example.act4.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.act4.Data.DataForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CbViewModel : ViewModel() {
    var namaUsr : String by mutableStateOf("")
        private set
    var noTlp : String by mutableStateOf("")
        private set
    var jenisKl : String by mutableStateOf("")
        private set
    var jenisSt : String by mutableStateOf("")
        private set
    var eMail : String by mutableStateOf("")
        private set
    var Tempat : String by mutableStateOf("")
        private set
    private val _uiState = MutableStateFlow(DataForm())
    val uiState: StateFlow<DataForm> = _uiState.asStateFlow()

    fun BacaData(jk: String, jst: String, meil: String, almt:String){

        jenisKl=jk;
        jenisSt=jst;
        eMail=meil;
        Tempat=almt;
    }
    fun setJenisK(pilihJK: String){
        _uiState.update{currentState -> currentState.copy(sex = pilihJK)}
    }
    fun setStatusnkh(pilihSts: String){
        _uiState.update{currentState -> currentState.copy(status = pilihSts)}
    }
}