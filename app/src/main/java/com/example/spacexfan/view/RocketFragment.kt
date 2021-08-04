package com.example.spacexfan.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacexfan.R
import com.example.spacexfan.adapter.RocketAdapter
import com.example.spacexfan.viewmodel.RocketViewModel
import kotlinx.android.synthetic.main.fragment_rocket.*


class RocketFragment : Fragment() {

    private lateinit var viewModel: RocketViewModel
    private val rocketAdapter= RocketAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_rocket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(this).get(RocketViewModel::class.java)

        viewModel.refreshData()

        rocketList.layoutManager=LinearLayoutManager(context)
        rocketList.adapter=rocketAdapter

        swipeRefreshLayout.setOnRefreshListener {

            rocketList.visibility=View.GONE

            rocketLoading.visibility=View.VISIBLE

            viewModel.refreshFromAPI()

            swipeRefreshLayout.isRefreshing=false
        }

        observeLiveData()

         }

    private fun observeLiveData(){
        viewModel.rockets.observe(viewLifecycleOwner, Observer {rockets ->
            rockets?.let {

                rocketList.visibility=View.VISIBLE
                rocketAdapter.updateRocketList(rockets)
                Log.d("myTag","rockets : " + rockets.get(0).details)
                rocketList.adapter?.notifyDataSetChanged()
            }
        })



        viewModel.rocketLoading.observe(viewLifecycleOwner, Observer { loading ->

            loading?.let {
                if (it){
                    rocketLoading.visibility=View.VISIBLE
                    rocketList.visibility=View.GONE

                }else
                    rocketLoading.visibility=View.GONE
            }

        })
    }

    }


