package com.saltmine.ocp.modal

data class EmployeePreference(
    val employeeId: String,
    val name: String,
    val team: String,
    val deskPreferences: List<String>,
    val equipmentNeeds: List<String>,
    val preferredDays: List<String>,
    val preferredLocation: String,
    val accessibilityNeeds: String?,
    val adjacencyPreferences: List<String>
)