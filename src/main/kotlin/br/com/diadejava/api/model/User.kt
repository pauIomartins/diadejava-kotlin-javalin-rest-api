package br.com.diadejava.api.model

import org.joda.time.LocalDate

class User(
    val id: String,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val birthDate: LocalDate
)