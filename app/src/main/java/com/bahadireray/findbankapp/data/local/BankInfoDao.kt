package com.bahadireray.findbankapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bahadireray.findbankapp.data.local.entity.BankInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BankInfoDao {

  @Update
  suspend fun updateBankInfo(bankInfo: BankInfoEntity)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun addAllBankInfo(bankInfo: List<BankInfoEntity>)

  @Query("SELECT * FROM bank_info")
  fun readAllBankInfoData(): Flow<List<BankInfoEntity>>

  @Query("DELETE FROM bank_info")
  suspend fun deleteAllBankInfo()
}
