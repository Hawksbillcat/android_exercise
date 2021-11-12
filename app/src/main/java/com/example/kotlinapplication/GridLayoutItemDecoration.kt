package com.example.kotlinapplication

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridLayoutItemDecoration(private val space:Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
         val position:Int = parent.getChildAdapterPosition(view)
         val gridLayoutManager: GridLayoutManager = (parent.layoutManager) as GridLayoutManager

        if( position < gridLayoutManager.spanCount ){
            outRect.top = space
        }
        if( position % 2 != 0){
            outRect.right = space
        } 

        outRect.left = space
        outRect.bottom = space
    }

}