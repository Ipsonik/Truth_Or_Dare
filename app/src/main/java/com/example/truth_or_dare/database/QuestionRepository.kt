package com.example.truth_or_dare.database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class QuestionRepository(private val questionDao: QuestionDao) {


    val allQuestions : Flow<List<Question>> =  questionDao.getAllQuestions()

    suspend fun insertQuestion(question: Question) = questionDao.insertQuestion(question)

    suspend fun deleteQuestion(question: Question) = questionDao.deleteQuestion(question)

}