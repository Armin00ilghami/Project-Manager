package com.example.projectmanager.ViewModel

import androidx.lifecycle.ViewModel
import com.example.projectmanager.Repository.ProfileRepository

class ProfileViewModel(val repository: ProfileRepository):ViewModel() {

    constructor():this(ProfileRepository())

    fun loadDataMyteam() = repository.myteamItems
    fun loadDataArchive() = repository.archiveItems

}