package br.com.diadejava.api.controller.response

import org.joda.time.LocalDate

data class UserResponse(
    val id: String,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val birthDate: LocalDate
)
