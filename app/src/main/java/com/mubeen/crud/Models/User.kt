package com.mubeen.crud.Models

import io.realm.kotlin.types.RealmObject

class User : RealmObject {

    var id: String = ""
    var name: String = ""
    var password: String = ""

}