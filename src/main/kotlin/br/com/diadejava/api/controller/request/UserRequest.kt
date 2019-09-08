package br.com.diadejava.api.controller.request

import org.joda.time.LocalDate

data class UserRequest(
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val birthDate: LocalDate
)