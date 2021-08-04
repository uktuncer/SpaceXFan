package com.example.spacexfan.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.spacexfan.model.Rocket
import com.example.spacexfan.service.RocketAPIService
import com.example.spacexfan.service.RocketDatabase
import com.example.spacexfan.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class RocketViewModel(application: Application) : BaseViewModel(application) {

    private val rocketApiService = RocketAPIService()

    private val disposable = CompositeDisposable()

    private var customPreferences= CustomSharedPreferences(getApplication())

    private var refreshTime= 10 * 60 * 1000 * 1000 * 1000L

    val rockets = MutableLiveData<List<Rocket>>()

    val rocketLoading = MutableLiveData<Boolean>()


    fun refreshData(){

        val updateTime= customPreferences.getTime()

        if (updateTime !=null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){

            Log.d("myTag","get from sqlite")
            getDataFromSQLite()

        }else{
            Log.d("myTag","get from api")
            getDataFromAPI()
        }
}
    fun refreshFromAPI(){

        getDataFromAPI()
    }

       private fun getDataFromSQLite(){
           rocketLoading.value=true
           launch {
               val rockets= RocketDatabase(getApplication()).rocketDao().getAllRockets()
               Log.d("myTag","rocket size : " + rockets.size)
               showRockets(rockets)
               Toast.makeText(getApplication(),"Rockets From SQLite ",Toast.LENGTH_LONG).show()

           }
       }

 private fun getDataFromAPI(){

     rocketLoading.value=true
            disposable.add(
            rocketApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Rocket>>(){
                    override fun onSuccess(t: List<Rocket>) {
                        Log.d("myTag","success in api call")
                        storeInSQLite(t)
                         Toast.makeText(getApplication(),"Rockets From API ",Toast.LENGTH_LONG).show()
                    }
                    override fun onError(e: Throwable) {
                        rocketLoading.value=false
                        Log.d("myTag","error is here")
                        e.printStackTrace()
                    }
                })
        )
    }
    private fun showRockets(rocketList: List<Rocket>){

        rockets.value=rocketList

        rocketLoading.value=false
    }
    private fun storeInSQLite (list: List<Rocket>){

        launch {
            val dao = RocketDatabase(getApplication()).rocketDao()
            dao.deleteAllRockets()
            val listLong = dao.insertAll(*list.toTypedArray())
            var i =0
            while (i<list.size){
                list[i].uuid=listLong[i].toInt()
                i = i + 1
            }
            showRockets(list)
        }
        customPreferences.saveTime(System.nanoTime())

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}