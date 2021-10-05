package com.example.kotlinapplication.forFragment

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.MainActivity
import com.example.kotlinapplication.R
import com.example.kotlinapplication.databinding.FragmentFirstBinding
import com.example.kotlinapplication.forAdapter.MyAdapter
import com.example.kotlinapplication.forAdapter.RecyclerAdapter
import com.example.kotlinapplication.forInterface.ApiService
import com.example.kotlinapplication.forInterface.GitHubService
import com.example.kotlinapplication.textClass.GithubData
import com.example.kotlinapplication.textClass.Posts
import kotlinx.coroutines.InternalCoroutinesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

@InternalCoroutinesApi
class FragmentFirst : Fragment() {

    lateinit var binding:FragmentFirstBinding
    private  var layoutManager:RecyclerView.LayoutManager?=null
    var recyclerAdapter: RecyclerAdapter? = null
    //private  var adapter:RecyclerView.Adapter<RecyclerView.ViewHolder>?=null

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
        //inflater.inflate(R.layout.fragment_first, container, false)
        binding = FragmentFirstBinding.inflate(inflater,container,false)
        val view = binding.root
        val context1: Context? = context;
        executeAPI(context1)
        layoutManager = LinearLayoutManager(context)
        //binding.recyclerView.layoutManager = layoutManager
        binding.close.setOnClickListener(View.OnClickListener {
            (context as MainActivity).supportFragmentManager.popBackStackImmediate("FragmentModeSelect",0)
        })
        binding.query.setOnClickListener {
            recyclerAdapter?.QueryData()
        }
        return view
    }


    fun executeAPI(context1: Context?){
        val mTestData = ArrayList<GithubData>()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //https://api.github.com/
        //https://jsonplaceholder.typicode.com/
        val service = retrofit.create(GitHubService::class.java)
            service.listUsers().enqueue(object :Callback<List<GithubData>>{
                override fun onResponse(
                    call: Call<List<GithubData>>,response: Response<List<GithubData>>) {
                    Log.i(TAG, "onResponse: " +response.code())
                    val list = response.body()
                    for (p in list!!) {
                        mTestData.add(p)
                    }
                    binding.recyclerView.layoutManager = layoutManager
                    val adapter = context1?.let { RecyclerAdapter(mTestData, it) }
                    binding.recyclerView.adapter = adapter
                    recyclerAdapter = adapter
                    binding.query.visibility = View.VISIBLE

                }
                override fun onFailure(call: Call<List<GithubData>>, t: Throwable) {
                    Log.d(TAG, "onFailure@@:"+call.isCanceled)
                    Log.d(TAG, "onFailure##:"+ t.message ) }

            })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentFirst().apply {
                arguments = Bundle().apply {

                }
            }
    }


}



