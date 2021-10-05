package com.example.kotlinapplication

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlinapplication.databinding.ActivityMainBinding
import com.example.kotlinapplication.forAdapter.MyAdapter
import com.example.kotlinapplication.forFragment.FragmentFirst
import com.example.kotlinapplication.forFragment.FragmentModeSelect
import com.example.kotlinapplication.forFragment.FragmentSecond
import com.example.kotlinapplication.textClass.MainViewModel
import com.example.kotlinapplication.textClass.ThirdsData
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding;

    private val fragmentFirst:FragmentFirst = FragmentFirst()
    private val fragmentSecond:FragmentSecond = FragmentSecond()



//    private val viewModel:MainViewModel by lazy {
//        ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(MainViewModel::class.java)
//    }

    lateinit var thirdsModel:ThirdsData

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater);
        super.onCreate(savedInstanceState)
        setContentView(binding.root);
        //binding.lifecycleOwner = this;
        //binding.first.text = "123456"
        //setContentView(R.layout.activity_main);
        //Toast.makeText(this,"",Toast.LENGTH_SHORT);
        thirdsModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(ThirdsData::class.java)

        //binding.viewModelx = viewModel

        thirdsModel.curNum.observe(this, Observer {

        })

        thirdsModel.curBoolean.observe(this, Observer {

        })

        increase()
        //addFragment(fragmentFirst)
        addFragment(FragmentModeSelect())


    }

    private fun increase(){
        /*binding.button.setOnClickListener(View.OnClickListener {
            thirdsModel.curNum.value = ++thirdsModel.num
            thirdsModel.curBoolean.value = thirdsModel.num % 2 == 0
        })*/
    }

    private fun addFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        //transaction.add(R.id.fragmentContainer, fragment).addToBackStack("FragmentFirst").commit()
        transaction.add(R.id.fragmentContainer, fragment).addToBackStack("FragmentModeSelect").commit()
    }

    private fun replaceFragment(fragment:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment).commit()
    }



}