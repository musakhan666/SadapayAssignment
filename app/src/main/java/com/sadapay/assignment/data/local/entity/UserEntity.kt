package com.sadapay.assignment.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Long = 0,
    val dob: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val phoneNumber: String? = null,
    val profilePicture: String? = null,
    val country: String? = null,
    val province: String? = null
)