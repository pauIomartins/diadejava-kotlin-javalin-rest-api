package br.com.diadejava.api.koin

import br.com.diadejava.api.controller.UserController
import br.com.diadejava.api.repository.UserRepository
import br.com.diadejava.api.service.UserService
import br.com.diadejava.api.service.impl.UserServiceImpl
import org.koin.dsl.module

var userModule = module {
    single { UserController(get()) }
    single { UserServiceImpl(get()) as UserService }
    single { UserRepository() }
}