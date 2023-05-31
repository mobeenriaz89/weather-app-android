package com.ingenious.weatherapp.application

import android.app.Application
import com.ingenious.weatherapp.models.User
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.log.LogLevel

class CrudApplication: Application() {

    companion object {
        lateinit var realmInst: Realm
    }

    override fun onCreate() {
        super.onCreate()

        val config = RealmConfiguration
            .Builder(schema = setOf(User::class))
            .name(Realm.DEFAULT_FILE_NAME)
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .log(LogLevel.ALL)
            .build()

        realmInst = Realm.open(config)
    }


}