package com.saltmine.ocp.modal.request

data class SpaceRequest(
    val buildingRefId: String?,
    val floorRefId: String?,
    val spaceRefId: String?,
    val spaceType: String?
)