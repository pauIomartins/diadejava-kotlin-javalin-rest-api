package br.com.diadejava.api.repository.persistence

import org.jetbrains.exposed.sql.Table

private const val ID_LENGTH = 26
private const val NAME_LENGTH = 50

object UserTable : Table("USER") {
    val id = varchar("ID", ID_LENGTH).primaryKey()
    val firstName = varchar("FIRST_NAME", NAME_LENGTH)
    val middleName = varchar("MIDDLE_NAME", NAME_LENGTH)
    val lastName = varchar("LAST_NAME", NAME_LENGTH)
    val birthDate = date("BIRTH_DATE")
}