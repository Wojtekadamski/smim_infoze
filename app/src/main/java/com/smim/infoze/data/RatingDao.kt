package com.smim.infoze.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RatingDao {
    @Query("SELECT AVG(score) FROM ratings WHERE materialId = :materialId")
    suspend fun getAverageRating(materialId: String): Float?

    @Query("SELECT COUNT(*) FROM ratings WHERE materialId = :materialId")
    suspend fun getRatingCount(materialId: String): Int

    @Query("SELECT score FROM ratings WHERE materialId = :materialId AND userEmail = :email")
    suspend fun getUserRating(materialId: String, email: String): Float?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRating(rating: Rating)
}
