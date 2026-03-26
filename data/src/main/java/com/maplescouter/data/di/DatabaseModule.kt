package com.maplescouter.data.di

import android.content.Context
import androidx.room.Room
import com.maplescouter.data.local.dao.CharacterDao
import com.maplescouter.data.local.database.MapleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMapleDatabase(
        @ApplicationContext context: Context
    ): MapleDatabase {
        return Room.databaseBuilder(
            context,
            MapleDatabase::class.java,
            "maple_scouter.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(database: MapleDatabase): CharacterDao {
        return database.characterDao()
    }
}
