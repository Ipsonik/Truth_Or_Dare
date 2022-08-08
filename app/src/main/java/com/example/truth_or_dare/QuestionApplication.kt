package com.example.truth_or_dare

import android.app.Application
import com.example.truth_or_dare.database.QuestionDatabase
import com.example.truth_or_dare.database.QuestionRepository

class QuestionApplication: Application() {
    val database: QuestionDatabase by lazy { QuestionDatabase.getDatabase(this) }
    val repository: QuestionRepository by lazy {QuestionRepository(database.questionDao()) }
}