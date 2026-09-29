package com.faisal.myapplication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform