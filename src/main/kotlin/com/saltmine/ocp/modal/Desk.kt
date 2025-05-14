package com.saltmine.ocp.modal


import java.time.ZonedDateTime

data class Desk(
    val id: String,
    val type: DeskType,
    val areaId: String,
    val vergeSenseAreaId: String,
    val floor: Int,
    val zone: String,
    val locationDescription: String,
    val features: List<String>,
    val status: DeskStatus,
    val lastUsed: ZonedDateTime
)
