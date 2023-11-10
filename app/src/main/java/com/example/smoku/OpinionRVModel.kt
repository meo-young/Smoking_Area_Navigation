package com.example.smoku

import com.google.firebase.database.DataSnapshot
import java.time.LocalDate

data class OpinionRVModel(
    var anonymous_user_count: Int =  0,
    var textArea: String = "",
    var passed_days: String = ""
)