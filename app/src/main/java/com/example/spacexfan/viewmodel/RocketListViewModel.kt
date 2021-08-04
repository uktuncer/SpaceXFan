package com.example.spacexfan.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacexfan.model.Cores
import com.example.spacexfan.model.Rocket
import com.example.spacexfan.service.RocketDatabase
import kotlinx.coroutines.launch
import java.util.*

class RocketListViewModel(application: Application) : BaseViewModel(application) {

     val rocketListLiveData=MutableLiveData<Rocket>()

    fun getDataFromRoom(uuid: Int){

        launch {

            val dao= RocketDatabase(getApplication()).rocketDao()
            val rocket = dao.getRockets(uuid)
            rocketListLiveData.value=rocket
        }

    }

}