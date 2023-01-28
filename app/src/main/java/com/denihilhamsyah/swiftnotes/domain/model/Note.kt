package com.denihilhamsyah.swiftnotes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String? = null
)
