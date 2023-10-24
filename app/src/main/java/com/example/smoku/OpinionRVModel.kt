package com.example.smoku

import java.io.Serializable

data class OpinionRVModel(
    var anonymous_user_count : Int,
    var textArea : String,
    var passed_days : Int
):Serializable