package com.example.kotlinapplication.forFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.MainActivity
import com.example.kotlinapplication.R
import com.example.kotlinapplication.databinding.FragmentModeSelectBinding
import kotlinx.coroutines.InternalCoroutinesApi
import javax.security.auth.callback.Callback

@InternalCoroutinesApi
class FragmentModeSelect : Fragment() {

    private lateinit var binding:FragmentModeSelectBinding

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentModeSelectBinding.inflate(inflater, container, false)
        binding.userList.setOnClickListener(View.OnClickListener {
            addFragment(FragmentFirst(),"FragmentFirst")
        })
        binding.favoriteList.setOnClickListener(View.OnClickListener {
            addFragment(FragmentFavorite(),"FragmentFavorite")
        })
        return binding.root
    }


    private fun addFragment(fragment: Fragment, name:String){
        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment).addToBackStack(name).commit()
    }




}