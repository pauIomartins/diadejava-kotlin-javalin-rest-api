package br.com.diadejava.api.service.impl

import br.com.diadejava.api.model.User
import br.com.diadejava.api.repository.UserRepository
import br.com.diadejava.api.service.UserService
import io.azam.ulidj.ULID
import org.apache.logging.log4j.LogManager
import org.joda.time.LocalDate

class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    private val logger = LogManager.getLogger(UserServiceImpl::class.java)

    override fun create(firstName: String, middleName: String, lastName: String, birthDate: LocalDate): User {
        val user = User(ULID.random(), firstName, middleName, lastName, birthDate)
        try {
            userRepository.create(user)
            return user
        } catch (ex: Exception) {
            logger.error("Error creating user. Exception: $ex")
            throw ex
        }
    }

    override fun getAll(): List<User> {
        try {
            return userRepository.getAll()
        } catch (e: Exception) {
            logger.error("Error getting all users. Exception: $e ")
            throw e
        }
    }
}