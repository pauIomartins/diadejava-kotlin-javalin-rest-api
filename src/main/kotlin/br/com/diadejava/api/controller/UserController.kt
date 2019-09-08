package br.com.diadejava.api.controller

import br.com.diadejava.api.controller.request.UserRequest
import br.com.diadejava.api.controller.response.UserResponse
import br.com.diadejava.api.service.UserService
import io.javalin.http.Context
import org.apache.logging.log4j.LogManager

class UserController(private val userService: UserService) {

    private val logger = LogManager.getLogger(UserController::class.java)

    fun getAllUsers(context: Context) {
        logger.info("Getting all users")
        context.json(userService.getAll())

    }

    fun create(context: Context) {
        logger.info("Creating user")
        val userRequest = context.body<UserRequest>()
        val user = userService.create(
            userRequest.firstName,
            userRequest.middleName,
            userRequest.lastName,
            userRequest.birthDate
        )
        context.status(201);
        context.json(UserResponse(user.id, user.firstName, user.middleName, user.lastName, user.birthDate))
    }
}