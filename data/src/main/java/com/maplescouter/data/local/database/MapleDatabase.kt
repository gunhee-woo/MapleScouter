package com.maplescouter.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maplescouter.data.local.dao.CharacterDao
import com.maplescouter.data.local.entity.CharacterBasicEntity
import com.maplescouter.data.local.entity.CharacterInfoEntity

@Database(
    entities = [
        CharacterInfoEntity::class,
        CharacterBasicEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MapleDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
