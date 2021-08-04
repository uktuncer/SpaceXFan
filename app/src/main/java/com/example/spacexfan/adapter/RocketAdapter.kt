package com.example.spacexfan.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.spacexfan.R
import com.example.spacexfan.databinding.ItemRocketBinding
import com.example.spacexfan.model.Rocket
import com.example.spacexfan.view.RocketFragment
import com.example.spacexfan.view.RocketFragmentDirections
import kotlinx.android.synthetic.main.item_rocket.view.*


class RocketAdapter(val rocketLıst: ArrayList<Rocket>): RecyclerView.Adapter<RocketAdapter.RocketViewHolder>(),RocketClickListener {

    class RocketViewHolder(val view: ItemRocketBinding) : RecyclerView.ViewHolder(view.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemRocketBinding>(inflater,R.layout.item_rocket,parent,false)
        return RocketViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rocketLıst.size
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {

        holder.view.rocket = rocketLıst[position]

        holder.view.listener = this

        Log.i("asd","saddsadsa")
    }

    fun updateRocketList(newRocketList: List<Rocket>) {
        rocketLıst.clear()
        rocketLıst.addAll(newRocketList)
        notifyDataSetChanged()
    }

    override fun onRocketClicked(v: View) {
        val uuid=v.rocketUuidText.text.toString().toInt()
        val action = RocketFragmentDirections.actionRocketFragmentToRocketListFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

}
