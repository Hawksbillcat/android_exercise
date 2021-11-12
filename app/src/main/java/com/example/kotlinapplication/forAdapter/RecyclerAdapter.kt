package com.example.kotlinapplication.forAdapter

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import com.bumptech.glide.Glide
import com.example.kotlinapplication.MainActivity
import com.example.kotlinapplication.R
import com.example.kotlinapplication.forData.FavoriteUser
import com.example.kotlinapplication.forData.FavoriteUserDataBase
import com.example.kotlinapplication.forFragment.FragmentSecond
import com.example.kotlinapplication.forInterface.ApiService
import com.example.kotlinapplication.forInterface.GitHubService
import com.example.kotlinapplication.forInterface.UserDataInterFace
import com.example.kotlinapplication.textClass.GithubData
import com.example.kotlinapplication.textClass.Repo
import com.example.kotlinapplication.textClass.UserData
import com.google.gson.JsonObject
import kotlinx.coroutines.InternalCoroutinesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InternalCoroutinesApi
class RecyclerAdapter(private val mData: List<GithubData>, private val context:Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var title = arrayOf("bear","yumi","Jim")
    private var siteArr = arrayOf("user","user","staff")
    private val imageArr = intArrayOf(R.drawable.bee,R.drawable.bee,R.drawable.bee)
    private var imageArrIcon:MutableList<Int> = ArrayList()
    private var imageArrIconBorder:MutableList<Int> = ArrayList()
    private var arrayList:MutableList<FavoriteUser> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = mData.get(position).login
        holder.itemSiteAdmin.text = isAdmin(mData.get(position).site_admin)
        Glide.with(context).load(mData.get(position).avatar_url).into(holder.itemImage)

        for(i in mData.indices){
            imageArrIcon.add(i,R.drawable.ic_baseline_favorite_24)
            imageArrIconBorder.add(i,R.drawable.ic_baseline_favorite_border_24)
            //checkData(holder, position)
            holder.favoriteIcon.setImageResource(imageArrIconBorder[i])
        }



        //checkData(holder,position)

        //holder.itemTitle.text =  title[position]
        //holder.itemSiteAdmin.text = siteArr[position]
        //holder.itemImage.setImageResource(imageArr[position])

    }

    override fun getItemCount(): Int {
        Log.i(ContentValues.TAG, "mData.size =  " + mData.size )
        return mData.size
        //return  title.size
    }


    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemImage:ImageView
        var itemTitle:TextView
        var itemSiteAdmin:TextView
        var favoriteIcon:ImageView

        init {
            itemImage = itemView.findViewById(R.id.imageView)
            itemTitle = itemView.findViewById(R.id.textViewLoginName) //textViewLoginName
            itemSiteAdmin = itemView.findViewById(R.id.textView_site_admin) //textView_site_admin
            favoriteIcon = itemView.findViewById(R.id.favorite)

            favoriteIcon.setOnClickListener(View.OnClickListener {
                QueryData()
            })

            itemView.setOnClickListener(View.OnClickListener {
                val position:Int = absoluteAdapterPosition
                //Toast.makeText(itemView.context,"clicked ${mData.get(position).login}", Toast.LENGTH_SHORT).show()
                executeAPI(itemView.context, mData[position].login)
            })

        }
    }

    fun isAdmin(boolean: Boolean):String{
        var res:String =""
        res = if(!boolean){
            "User"
        } else{
            "Staff"
        }
        return res
    }

    fun executeAPI(context1: Context?, name:String){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(UserDataInterFace::class.java)
       /* val service = retrofit.create(ApiService::class.java)
        service.listRepos("Jintin").enqueue(object :Callback<List<Repo>>{
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                Log.i(ContentValues.TAG, "onResponse: " +response.code())
                val list = response.body()
                for (p in list!!) {
                    Log.i(ContentValues.TAG, "p = : " + p.name)
                }
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                Log.i(ContentValues.TAG, "onFailure: " + t.message)
            }
        })*/
        service.info(name).enqueue(object :Callback<UserData>{
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                Log.i(ContentValues.TAG, "onResponse: " +response.code())
                Log.i(ContentValues.TAG, "onResponse_login: " + response.body()?.login)
                Log.i(ContentValues.TAG, "onResponse_name: " + response.body()?.name)
                Log.i(ContentValues.TAG, "onResponse_location: " + response.body()?.location)
                Log.i(ContentValues.TAG, "bio: " + response.body()?.bio)
                var ifnull = ""
                ifnull = if(response.body()?.bio==null){
                    "no"
                } else{
                    response.body()?.bio!!
                }
                var s = response.body()?.login +" is " +response.body()?.name +" in " +response.body()?.location
                //Toast.makeText(context,"clicked "+ s , Toast.LENGTH_SHORT).show()
                var jsonObject:JsonObject = JsonObject()
                var lo = response.body()?.login
                jsonObject.addProperty("login",response.body()?.login)
                jsonObject.addProperty("name",response.body()?.name)
                jsonObject.addProperty("avatar_url",response.body()?.avatar_url)
                jsonObject.addProperty("bio",ifnull)
                jsonObject.addProperty("location",response.body()?.location)
                jsonObject.addProperty("blog",response.body()?.blog)
                jsonObject.addProperty("site_admin", response.body()?.site_admin?.let { isAdmin(it) })
                replaceFragment(FragmentSecond(),jsonObject)
                //Toast.makeText(context,"clicked $lo", Toast.LENGTH_SHORT).show()
                val data = lo?.let { FavoriteUser(0,it,response.body()?.avatar_url.toString(),false,) }
                if (data != null) {
                    insertData(data)
                }
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
                Log.i(ContentValues.TAG, "onFailure: " + t.message)
            }
        })
    }

    private fun replaceFragment(fragment: Fragment,jsonObject:JsonObject){
        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()
        val bundle:Bundle = Bundle()
        bundle.putString("Json",jsonObject.toString())
        fragment.arguments = bundle
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack("FragmentSecond")
        transaction.commit()
    }

    @InternalCoroutinesApi
    fun insertData(myData: FavoriteUser){
        Thread{
            FavoriteUserDataBase.getDataBase(context)!!.newFavoriteUserDao().addUser(myData)
            (context as Activity).runOnUiThread(Runnable {
                //showData()
            })
        }.start()
    }

    fun QueryData(){
        Thread{
            arrayList = FavoriteUserDataBase.getDataBase(context)!!.newFavoriteUserDao().readAllData()
            Log.i(ContentValues.TAG, "arrayListQuery = ${arrayList.size}" )
            for(i in 0 until arrayList.size){
                for(j in mData.indices){
                    Log.i(ContentValues.TAG, "i = $i ")
                       if(arrayList[i].login == mData[j].login){
                            Log.i(ContentValues.TAG, "the_login_name_in $i and $j is_same")

                       }
                }
            }
            (context as Activity).runOnUiThread(Runnable {
                //showData()

            })
        }.start()
    }

    fun checkData(holder: RecyclerAdapter.ViewHolder, position: Int){
        Thread{
            arrayList = FavoriteUserDataBase.getDataBase(context)!!.newFavoriteUserDao().readAllData()
            Log.i(ContentValues.TAG, "arrayListQuery = ${arrayList.size}" )
            for(i in 0 until arrayList.size){
                for(j in mData.indices){
                    Log.i(ContentValues.TAG, "i = $i ")
                    if(arrayList[i].login == mData[j].login){
                        Log.i(ContentValues.TAG, "the_login_name_in $i and $j is_same")
                        holder.favoriteIcon.setImageResource(imageArrIcon[i])
                    }
                    else{
                        holder.favoriteIcon.setImageResource(imageArrIconBorder[i])
                    }
                }
            }
            (context as Activity).runOnUiThread(Runnable {
                //showData()

            })
        }.start()
    }


}