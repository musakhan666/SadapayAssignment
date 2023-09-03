package com.sadapay.assignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sadapay.assignment.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao

}