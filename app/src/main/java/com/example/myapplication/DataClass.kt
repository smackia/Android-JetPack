package com.example.myapplication

import java.io.Serializable

data class getData(
    val code:Int,
    val data:List<Data>
) : Serializable
