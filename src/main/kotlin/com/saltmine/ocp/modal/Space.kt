package com.saltmine.ocp.modal

data class Space(
    val id: String,
    val name: String,
    val type: String,
    val capacity: Int,
    val parentId: String
)