package com.example.kotlinapplication.forFragment

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.bumptech.glide.Glide
import com.example.kotlinapplication.MainActivity
import com.example.kotlinapplication.R
import com.example.kotlinapplication.WebViewActivity
import com.example.kotlinapplication.databinding.FragmentSecondBinding
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.InternalCoroutinesApi
import java.lang.Exception

@InternalCoroutinesApi
class FragmentSecond : Fragment() {

    lateinit var bindingTwo:FragmentSecondBinding
    lateinit var jsonObject:JsonObject

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
        bindingTwo = FragmentSecondBinding.inflate(inflater, container, false)
        val view = bindingTwo.root
        var args = this.arguments
        val data:String = args?.get("Json").toString()

        val jsonParser:JsonParser = JsonParser()
        var jsonObject:JsonObject = jsonParser.parse(data).asJsonObject
        Log.i(ContentValues.TAG, jsonObject.toString())
        var name = jsonObject.get("name").asString
        var login = jsonObject.get("login").asString
        var bio  = setBioValue(jsonObject)
        var site_admin = jsonObject.get("site_admin").asString
        var blog = jsonObject.get("blog").asString
        Log.i(ContentValues.TAG, "blog: $blog")
        var location = setLocation(jsonObject)
        var avatar_url = jsonObject.get("avatar_url").asString

        bindingTwo.exit.setOnClickListener(View.OnClickListener {
            Log.i(ContentValues.TAG, "data: " + "click")
            (context as MainActivity).supportFragmentManager.popBackStackImmediate("FragmentFirst",0)
        })

        bindingTwo.name.text = name
        bindingTwo.bio.text = bio
        bindingTwo.textviewUser.text = login
        bindingTwo.textViewBadge.text = site_admin
        bindingTwo.textviewLocation.text = location
        bindingTwo.textviewBlog.text = blog
        context?.let { Glide.with(it).load(avatar_url).into(bindingTwo.avatar) }

        bindingTwo.openWebViewLayout.setOnClickListener(View.OnClickListener {
            val intent:Intent = Intent(context,WebViewActivity::class.java).apply {
                putExtra("blog",blog)
            }
            startActivity(intent)
        })

        return view

    }

    private fun setBioValue(jsonObject: JsonObject):String{
        var bio = ""
        jsonObject.get("bio")?.let {
            Log.i(ContentValues.TAG, "???= "+it)

            bio = if(it.asString.equals("no")){
                "showNothing"
            }
            else{
                it?.asString
            }
        }
        return bio
    }

    private fun setLocation(jsonObject: JsonObject):String{
        var location = ""
        Log.i(ContentValues.TAG, "is null = "+ jsonObject.get("location").isJsonNull)

        location = if(jsonObject.get("location").isJsonNull){
            "No Location"
        }
        else{
            jsonObject.get("location").asString
        }
        return location
    }


}