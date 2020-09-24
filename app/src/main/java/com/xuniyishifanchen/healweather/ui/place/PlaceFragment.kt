package com.xuniyishifanchen.healweather.ui.place

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xuniyishifanchen.healweather.R
import kotlinx.android.synthetic.main.fragment_place.*

class PlaceFragment : Fragment() {

    val viewModel by lazy { ViewModelProviders.of(this).get(PlaceViewModel::class.java) }

    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        recycle_place.layoutManager = layoutManager
        adapter = PlaceAdapter(viewModel.placeList)
        recycle_place.adapter = adapter
        search_place_et.addTextChangedListener { editable ->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                viewModel.searchPlaces(content)
            } else {
                viewModel.placeList.clear()
                bg_image.visibility = View.VISIBLE
                adapter.notifyDataSetChanged()
            }

        }
        viewModel.placeLiveData.observe(this, Observer {
            val places = it.getOrNull()
            if(places != null){
                recycle_place.visibility = View.VISIBLE
                bg_image.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity,"未能查到地址",Toast.LENGTH_LONG).show()
            }
        })
    }
}