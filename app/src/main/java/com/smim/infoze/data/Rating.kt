package com.smim.infoze.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ratings")
data class Rating(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val materialId: String,
    val userEmail: String,
    val score: Float // 0.0–5.0
)
