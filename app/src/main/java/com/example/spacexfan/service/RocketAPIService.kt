package com.example.spacexfan.service
import com.example.spacexfan.model.Rocket
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RocketAPIService {


  //private val BASE_URL="burada .com/ a kadar olan kısım kopyalanacak"
  private val BASE_URL = "https://api.spacexdata.com/"

    private val api= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(RocketAPI::class.java)


        fun getData(): Single<List<Rocket>> {

            return api.getRockets()
        }
}


