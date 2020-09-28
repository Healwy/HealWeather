package com.xuniyishifanchen.healweather.ui.place

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xuniyishifanchen.healweather.databinding.FragmentPlaceBinding

class PlaceFragment : Fragment() {

    val viewModel by viewModels<PlaceViewModel>()

    private lateinit var adapter: PlaceAdapter

    private lateinit var dataBinding: FragmentPlaceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentPlaceBinding.inflate(inflater, container, false)
        return dataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        dataBinding.recyclePlace.layoutManager = layoutManager
        adapter = PlaceAdapter(viewModel.placeList)
        dataBinding.recyclePlace.adapter = adapter
        dataBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            placeVM = viewModel
        }
//        search_place_et.addTextChangedListener { editable ->
//            val content = editable.toString()
//            if (content.isNotEmpty()) {
//                viewModel.searchPlaces(content)
//            } else {
//                viewModel.placeList.clear()
//                bg_image.visibility = View.VISIBLE
//                adapter.notifyDataSetChanged()
//            }
//
//        }
        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer {
            val places = it.getOrNull()
            if (places != null) {
                dataBinding.recyclePlace.visibility = View.VISIBLE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查到地址", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.isPlaceListEmptyLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("xuchen", "onActivityCreated: $it")
        })
    }
}