package com.example.spacexfan.view
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.spacexfan.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController=findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)


       // val rocket= RocketAPIService()
       // rocket.getData()

       // fetchJson()
    }
}
/*
private fun fetchJson(){
        val url = "https://api.spacexdata.com/v4/launches"
        val request=Request.Builder().url(url).build()

        val client=OkHttpClient()
        client.newCall(request).enqueue(object :Callback{
            override fun onResponse(call: Call, response: Response) {
                val body=response?.body()?.string()
                println(body)

                val gson=GsonBuilder().create()

              val RocketFeed=gson.fromJson(body, RocketFeed::class.java)
            }

            override fun onFailure(call: Call, e: IOException) {

            }



        })
    }
    class RocketFeed(val name:String,val details:String){

}

 */




