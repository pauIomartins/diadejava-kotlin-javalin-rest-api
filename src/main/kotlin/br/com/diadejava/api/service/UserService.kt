package br.com.diadejava.api.service

import br.com.diadejava.api.model.User
import org.joda.time.LocalDate

interface UserService {
    fun create(firstName: String, middleName: String, lastName: String, birthDate: LocalDate): User
    fun getAll(): List<User>
}