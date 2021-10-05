package com.example.kotlinapplication.forAdapter

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinapplication.R
import com.example.kotlinapplication.forData.FavoriteUser
import com.example.kotlinapplication.forData.FavoriteUserDataBase
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.function.Consumer
import kotlin.math.log
@InternalCoroutinesApi
class MyAdapter(val activity: Activity) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var arrayList:MutableList<FavoriteUser> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = arrayList.get(position).login
        holder.itemSiteAdmin.text = "user"
        Glide.with(activity).load(arrayList.get(position).avatar_url).into(holder.itemImage)

    }

    override fun getItemCount(): Int {
        Log.i(ContentValues.TAG, "arrayList.size = "+arrayList.size)
        return arrayList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemSiteAdmin: TextView

        init {
            itemImage = itemView.findViewById(R.id.imageView)
            itemTitle = itemView.findViewById(R.id.textViewLoginName) //textViewLoginName
            itemSiteAdmin = itemView.findViewById(R.id.textView_site_admin) //textView_site_admin
        }
    }

    @InternalCoroutinesApi
    fun insertData(myData: FavoriteUser){
        Thread{
            FavoriteUserDataBase.getDataBase(activity)!!.newFavoriteUserDao().addUser(myData)
            activity.runOnUiThread(Runnable {
                showData()
            })
        }.start()
    }

    @InternalCoroutinesApi
    fun showData(){
        Thread{
            arrayList =  FavoriteUserDataBase.getDataBase(activity)!!.newFavoriteUserDao().readAllData()
            activity.runOnUiThread {
                notifyDataSetChanged()
            }
        }.start()
    }

    fun deleteData(position: Int){
        Thread{
            FavoriteUserDataBase.getDataBase(activity)!!.newFavoriteUserDao().deleteNewPost(arrayList[position])
            activity.runOnUiThread {
                showData()
            }
        }.start()
    }
}