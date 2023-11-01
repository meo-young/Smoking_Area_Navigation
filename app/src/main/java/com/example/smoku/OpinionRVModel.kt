package com.example.smoku

import com.google.firebase.database.DataSnapshot
import java.io.Serializable
import java.time.LocalDate

data class OpinionRVModel(
    var anonymous_user_count: Int,
    var textArea: String,
    var passed_days: Int
):Serializable