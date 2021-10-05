package com.example.kotlinapplication.forFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.MainActivity
import com.example.kotlinapplication.R
import com.example.kotlinapplication.databinding.FragmentFavoriteBinding
import com.example.kotlinapplication.forAdapter.MyAdapter
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class FragmentFavorite : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var bind:FragmentFavoriteBinding
    var myAdapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentFavoriteBinding.inflate(inflater,container, false)
        bind.close.setOnClickListener(View.OnClickListener {
            (context as MainActivity).supportFragmentManager.popBackStackImmediate("FragmentModeSelect",0)
        })
        bind.recyclerView.layoutManager = LinearLayoutManager(context)
        myAdapter = MyAdapter(context as Activity)
        bind.recyclerView.adapter = myAdapter
        myAdapter!!.showData()
        setRecyclerFunction(bind.recyclerView)
        return bind.root
    }

    private fun setRecyclerFunction(recyclerView: RecyclerView){
        val helper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(0, ItemTouchHelper.LEFT)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                when(direction){
                    ItemTouchHelper.LEFT ,ItemTouchHelper.RIGHT -> myAdapter!!.deleteData(position)
                }
            }
        })
        helper.attachToRecyclerView(recyclerView)
    }

}