package com.example.projectmanager.Adapter

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectmanager.Domain.OngoingDomain
import com.example.projectmanager.Domain.TeamDomain
import com.example.projectmanager.R

class MyTeamAdapter(private val items: List<TeamDomain>): RecyclerView.Adapter<MyTeamAdapter.Viewholder>() {
    inner class Viewholder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.titleTxt)
        val status : TextView = itemView.findViewById(R.id.statusTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_team , parent , false)
        return Viewholder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.status.text = item.status
    }
}