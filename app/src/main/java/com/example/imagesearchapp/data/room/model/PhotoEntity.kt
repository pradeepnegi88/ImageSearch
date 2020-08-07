package com.example.imagesearchapp.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "comment") val comment: String?
)