package com.example.truth_or_dare.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Question::class], version = 1, exportSchema = true)
abstract class QuestionDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: QuestionDatabase? = null
        fun getDatabase(context: Context): QuestionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        QuestionDatabase::class.java,
                        "questions.db"
                    )
                        .createFromAsset("database/questions.db")
                        .fallbackToDestructiveMigration()
                        .setJournalMode(JournalMode.TRUNCATE)
                        .build()
                INSTANCE = instance
                return instance
            }

        }
    }
}