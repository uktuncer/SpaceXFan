package com.example.spacexfan.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.TypeConverter
import com.example.spacexfan.R
import com.example.spacexfan.databinding.FragmentRocketListBinding
import com.example.spacexfan.service.RocketAPIService
import com.example.spacexfan.viewmodel.RocketListViewModel
import com.squareup.moshi.Moshi

class RocketListFragment : Fragment() {

    private lateinit var viewModel: RocketListViewModel

    private var rocketListUuid=0

    private lateinit var dataBinding : FragmentRocketListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_rocket_list,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            rocketListUuid = RocketListFragmentArgs.fromBundle(it).rocketListUuid
        }

        viewModel= ViewModelProvider(this).get(RocketListViewModel::class.java)
        viewModel.getDataFromRoom(rocketListUuid)


        observeLiveData()
    }

    @TypeConverter
    fun stringToLatLng(input: String?): RocketListFragment? =
        input?.let { Moshi.Builder().build().adapter(RocketListFragment::class.java).fromJson(it) }

    @TypeConverter
    fun latLngToString(input: RocketListFragment): String? =
        Moshi.Builder().build().adapter(RocketListFragment::class.java).toJson(input)

    private fun observeLiveData(){
        val rocket=RocketAPIService()
        rocket.getData()

        viewModel.rocketListLiveData.observe(viewLifecycleOwner, Observer { rocket ->
            rocket?.let {
                dataBinding.selectedRocket = rocket
            }
        })
    }

}