package com.example.projectmanager.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmanager.Adapter.FileAdapter
import com.example.projectmanager.databinding.FragmentFileBinding
import java.io.File

class FileFragment (val path : String): Fragment() {
   lateinit var binding : FragmentFileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ourFile = File(path)
        binding.txtPath.text = ourFile.name + ">"
        if (ourFile.isDirectory){

            val listOfFiles = arrayListOf<File>()
            listOfFiles.addAll( ourFile.listFiles()!! )

            if ( listOfFiles.size > 0 ) {

                binding.recyclerMain.visibility = View.VISIBLE
                binding.imgNoData.visibility = View.GONE

                val myAdapter = FileAdapter( listOfFiles )
                binding.recyclerMain.adapter = myAdapter
                binding.recyclerMain.layoutManager = LinearLayoutManager( context )

            } else {

                binding.recyclerMain.visibility = View.GONE
                binding.imgNoData.visibility = View.VISIBLE

            }

        }

    }


}