package com.example.projectmanager.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectmanager.R
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmanager.databinding.ItemFileLinearBinding
import java.io.File

class FileAdapter (val data:ArrayList<File>):RecyclerView.Adapter<FileAdapter.FileViewHolder>(){
    lateinit var binding : ItemFileLinearBinding

    inner class FileViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        fun bindViews(file:File){
            binding.textView.text = file.name
            binding.imageView.setImageResource( R.drawable.ic_folder )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val layoutInflater = LayoutInflater.from( parent.context )
        binding = ItemFileLinearBinding.inflate( layoutInflater , parent , false )
        return FileViewHolder( binding.root )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bindViews( data[position] )
    }

}