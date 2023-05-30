package com.mubeen.crud.Controllers

import com.mubeen.crud.Application.CrudApplication.Companion.realmInst
import com.mubeen.crud.Models.User
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

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