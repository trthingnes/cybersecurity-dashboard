package edu.ntnu.tobiasth.haos.dto

data class ResultDto<T>(
    val result: String,
    val data: T,
)