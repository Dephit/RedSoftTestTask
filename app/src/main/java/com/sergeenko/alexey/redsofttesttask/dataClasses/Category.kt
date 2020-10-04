package com.sergeenko.alexey.redsofttesttask.dataClasses

data class Category(
    var id: Int = 0,
    var title: String? = "",
    var parent_id: Int = 0
)