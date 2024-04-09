package com.example.projectmanager.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmanager.Adapter.ArchiveAdapter
import com.example.projectmanager.Adapter.MyTeamAdapter
import com.example.projectmanager.Fragment.FileFragment
import com.example.projectmanager.R
import com.example.projectmanager.ViewModel.ProfileViewModel
import com.example.projectmanager.databinding.ActivityMainBinding
import com.example.projectmanager.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    val profileViewModel: ProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val myteamAdapter by lazy { MyTeamAdapter(profileViewModel.loadDataMyteam()) }
            viewTeam.apply {
                adapter = myteamAdapter
                layoutManager = LinearLayoutManager(
                    this@ProfileActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }

            val archiveAdapter by lazy { ArchiveAdapter(profileViewModel.loadDataArchive()) }
            viewArchive.apply {
                adapter = archiveAdapter
                layoutManager = LinearLayoutManager(
                    this@ProfileActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            }

        }

        val file = getExternalFilesDir(null)!!
        val path = file.path

        binding.imageView2.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.frame_main_container, FileFragment(path))
            transaction.commit()
        }

    }
}