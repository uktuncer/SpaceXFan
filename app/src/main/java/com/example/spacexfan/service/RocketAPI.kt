package com.example.spacexfan.service
import com.example.spacexfan.model.Rocket
import io.reactivex.Single
import retrofit2.http.GET
interface RocketAPI {


  //@GET("buraya apiden gelecen url nin .com/ dan sonraki kısmını alıp buraya yapıştıracaksın")

  @GET("v4/launches/latest")

    fun getRockets():Single<List<Rocket>>

}