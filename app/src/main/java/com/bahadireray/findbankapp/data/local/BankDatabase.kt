package com.bahadireray.findbankapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bahadireray.findbankapp.data.local.entity.BankInfoEntity

@Database(entities = [BankInfoEntity::class], version = 1, exportSchema = false)
abstract class BankDatabase : RoomDatabase() {

  abstract fun bankInfoDao(): BankInfoDao

}
