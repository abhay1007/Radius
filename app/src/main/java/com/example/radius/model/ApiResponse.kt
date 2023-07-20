package com.example.radius.model

data class ApiResponse(
    val facilities: List<Facility>,
    val exclusions: List<List<Exclusion>>
)

data class Facility(
    val facilityId: String,
    val name: String,
    val options: List<FacilityOption>
)

data class FacilityOption(
    val name: String,
    val icon: String,
    val id: String
)

data class Exclusion(
    val facilityId: String,
    val optionsId: String
)
