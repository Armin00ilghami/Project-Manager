package com.example.projectmanager.model.Repository

import com.example.projectmanager.Domain.TeamDomain

class ProfileRepository {

    val myteamItems:MutableList<TeamDomain> = mutableListOf(
        TeamDomain("Crypto App Application" , "Project in Progress"),
        TeamDomain("AI Chat GPT" , "Completed"),
        TeamDomain("Wikipedia App Backend" , "Project in Progress"),
        TeamDomain("Kotlin Developers" , "Completed")
    )

    val archiveItems : MutableList<String> = mutableListOf(
        "UI/UX ScreenShot",
        "Kotlin Source Code",
        "Solutions and Source"
    )
}