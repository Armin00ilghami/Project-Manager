package com.example.projectmanager.Repository

import  com.example.projectmanager.Domain.OngoingDomain
class MainRepository {
    val items = listOf(
        OngoingDomain("Crypto App", "18 April 2024", 70 ,"ongoing1"),
        OngoingDomain("Invest App", "23 June 2023", 85 ,"ongoing2"),
        OngoingDomain("Education App", "9 September 2024", 20 ,"ongoing3"),
        OngoingDomain("VPN App", "2 March 2024", 30 ,"ongoing4")
    )
}