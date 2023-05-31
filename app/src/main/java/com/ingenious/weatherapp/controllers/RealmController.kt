package com.ingenious.weatherapp.controllers

import com.ingenious.weatherapp.application.CrudApplication.Companion.realmInst
import com.ingenious.weatherapp.models.User
import io.realm.kotlin.ext.query

object RealmController {

    fun createUser(user:User){
        realmInst.writeBlocking {
            copyToRealm(user)
        }
    }

    fun getUser(id: String, password: String) : User? {
       return realmInst
           .query<User>("id == $0 AND password == $1", id, password).first().find()
    }
}