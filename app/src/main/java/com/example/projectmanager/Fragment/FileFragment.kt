package com.example.projectmanager.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import com.example.projectmanager.R
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmanager.Adapter.FileAdapter
import com.example.projectmanager.databinding.FragmentFileBinding
import java.io.File

class FileFragment (val path : String): Fragment() , FileAdapter.FileEvent {
   lateinit var binding : FragmentFileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFileBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ourFile = File(path)
        binding.txtPath.text = ourFile.name + ">"
        if (ourFile.isDirectory){

            val listOfFiles = arrayListOf<File>()
            listOfFiles.addAll( ourFile.listFiles()!! )
            listOfFiles.sort()

            if ( listOfFiles.size > 0 ) {

                binding.recyclerMain.visibility = View.VISIBLE
                binding.imgNoData.visibility = View.GONE

                val myAdapter = FileAdapter( listOfFiles , this )
                binding.recyclerMain.adapter = myAdapter
                binding.recyclerMain.layoutManager = LinearLayoutManager( context )

            } else {

                binding.recyclerMain.visibility = View.GONE
                binding.imgNoData.visibility = View.VISIBLE

            }

        }

    }

    override fun onFileClicked(file: File, type: String) {

        val intent = Intent( Intent.ACTION_VIEW )

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ){

            val fileProvider = FileProvider.getUriForFile(
                requireContext(),
                requireActivity().packageName + "provider" ,
                file
            )
            intent.setDataAndType( fileProvider , type )

        } else {
            intent.setDataAndType( Uri.fromFile( file ), type )
        }

        intent.addFlags( Intent.FLAG_GRANT_READ_URI_PERMISSION )
        startActivity( intent )

    }

    override fun onFolderClicked(path: String) {

        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace( R.id.frame_main_container , FileFragment( path ) )
        transaction.addToBackStack(null)
        transaction.commit()

    }


}