package com.thepparat.unitconverterapplication.di

import android.app.Application
import androidx.room.Room
import com.thepparat.unitconverterapplication.data.ConverterDatabase
import com.thepparat.unitconverterapplication.data.ConverterRepository
import com.thepparat.unitconverterapplication.data.ConverterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideConverterDatabase(application: Application): ConverterDatabase {
        return Room.databaseBuilder(
            application,
            ConverterDatabase::class.java,
            "converter_app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideConverterRepository(database: ConverterDatabase): ConverterRepository {
        return ConverterRepositoryImpl(database.converterDAO)
    }

}