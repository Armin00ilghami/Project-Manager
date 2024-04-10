package com.example.projectmanager.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectmanager.R
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmanager.databinding.ItemFileLinearBinding
import java.io.File
import java.net.URLConnection

class FileAdapter ( val data:ArrayList<File> , val fileEvent: FileEvent ):RecyclerView.Adapter<FileAdapter.FileViewHolder>(){
    lateinit var binding : ItemFileLinearBinding

    inner class FileViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        fun bindViews(file:File){
            var fileType = ""
            binding.textView.text = file.name

            if ( file.isDirectory ){
                binding.imageView.setImageResource( R.drawable.ic_folder)
            } else{

                when {
                    isImage( file.path ) -> {
                        binding.imageView.setImageResource( R.drawable.ic_image )
                        fileType = "image/*"
                    }
                    isVideo( file.path ) -> {
                        binding.imageView.setImageResource( R.drawable.ic_video )
                        fileType = "video/*"
                    }
                    isZip( file.path ) -> {
                        binding.imageView.setImageResource( R.drawable.ic_zip )
                        fileType = "application/zip"
                    }
                    else -> {
                        binding.imageView.setImageResource( R.drawable.ic_file )
                        fileType = "text/plain"
                    }
                }
            }
            itemView.setOnClickListener {
                if ( file.isDirectory ){
                    fileEvent.onFolderClicked( file.path )
                } else {
                    fileEvent.onFileClicked( file , fileType )
                }
            }
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

    fun isImage(path: String): Boolean {
        val mimeType: String = URLConnection.guessContentTypeFromName(path)
        return mimeType.startsWith("image")
    }
    fun isVideo(path: String): Boolean {
        val mimeType = URLConnection.guessContentTypeFromName(path)
        return mimeType.startsWith("video")
    }
    fun isZip(name: String): Boolean {
        return name.contains(".zip") || name.contains(".rar")
    }

    fun addNewFile( newFile : File ){

        data.add( 0, newFile )
        notifyItemInserted( 0 )

    }
    interface FileEvent{
        fun onFileClicked( file : File , type : String )
        fun onFolderClicked( path : String )
    }

}