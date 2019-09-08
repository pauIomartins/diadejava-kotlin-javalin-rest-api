package br.com.diadejava.api.repository

import br.com.diadejava.api.model.User
import br.com.diadejava.api.repository.persistence.UserTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.LocalTime

class UserRepository {

    fun create(user: User): String {
        return transaction {
            UserTable.insert {
                it[id] = user.id
                it[firstName] = user.firstName
                it[middleName] = user.middleName
                it[lastName] = user.lastName
                it[birthDate] = user.birthDate.toDateTime(LocalTime())
            } get UserTable.id
        }
    }

    fun getAll(): List<User> {
        return transaction {
            UserTable.selectAll().map { it.toUser() }
        }
    }

    private fun ResultRow.toUser() = User(
        id = this[UserTable.id],
        firstName = this[UserTable.firstName],
        middleName = this[UserTable.middleName],
        lastName = this[UserTable.lastName],
        birthDate = this[UserTable.birthDate].toLocalDate()
    )
}