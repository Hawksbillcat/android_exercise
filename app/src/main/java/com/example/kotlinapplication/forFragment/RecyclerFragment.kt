package com.example.kotlinapplication.forFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlinapplication.GridLayoutItemDecoration
import com.example.kotlinapplication.R
import com.example.kotlinapplication.databinding.FragmentRecyclerBinding
import com.example.kotlinapplication.forAdapter.RecyclerGridAdapter


class RecyclerFragment : Fragment() {

    lateinit var binding:FragmentRecyclerBinding
    private lateinit var arrayList: ArrayList<Int>
    private lateinit var adapter: RecyclerGridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        initArray()
        return binding.root
    }


    private fun initArray(){
        arrayList = ArrayList()
        for(i in 0..3){
            //val img = context?.let { getDrawable(it,R.drawable.smile) }
            arrayList.add(R.drawable.smile)
        }
        val mContext = requireContext()
        adapter = RecyclerGridAdapter(arrayList,mContext)
        val gridLayout = GridLayoutManager(requireContext(),4)
        binding.recyclerView.layoutManager = gridLayout
        binding.recyclerView.addItemDecoration(GridLayoutItemDecoration(5))
        binding.recyclerView.adapter = adapter

    }


}