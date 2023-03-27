package com.example.truth_or_dare.viewmodels

import androidx.lifecycle.*
import com.example.truth_or_dare.database.Question
import com.example.truth_or_dare.database.QuestionDao
import com.example.truth_or_dare.database.QuestionRepository
import com.example.truth_or_dare.models.Player
import kotlinx.coroutines.launch

class QuestionViewModel(private val questionRepository: QuestionRepository) : ViewModel() {
    
    private val editLine = null
    private val _players: MutableLiveData<List<Player>> = MutableLiveData(listOf())
    val players: LiveData<List<Player>> = _players

    private var _player = MutableLiveData<Player>()
    val player: LiveData<Player> = _player

    private var _askedPlayer: MutableLiveData<String> = MutableLiveData(String())
    val askedPlayer: LiveData<String> = _askedPlayer

    private var _question: MutableLiveData<String> = MutableLiveData(String())
    val question: LiveData<String> = _question

    private var _endGame: MutableLiveData<Boolean> = MutableLiveData(false)
    val endGame: LiveData<Boolean> = _endGame

    private var _askedQuestions: MutableLiveData<List<String>> = MutableLiveData(listOf())
    private var _askedPlayers: MutableLiveData<List<String>> = MutableLiveData(listOf())

    val allQuestions: LiveData<List<Question>> = questionRepository.allQuestions.asLiveData()
    var editTextQuestion: MutableLiveData<String> = MutableLiveData("")


    private fun insertQuestion(question: Question) {
        viewModelScope.launch {
            questionRepository.insertQuestion(question)
        }
    }

    fun deleteQuestion(question: Question) {
        viewModelScope.launch {
            questionRepository.deleteQuestion(question)
        }
    }

    private fun getNewQuestion(questionContent: String): Question {
        return Question(
            content = questionContent
        )
    }

    fun addQuestion(questionContent: String) {
        val question = getNewQuestion(questionContent)
        insertQuestion(question)
        editTextQuestion.value = ""
    }

    fun addPlayer() {
        if (_player.value?.name?.isNotEmpty() == true) {
            if (_players.value?.contains(_player.value) == false) {
                _players.value =
                    ((_players.value?.plus(_player.value!!)
                        ?: listOf(_player.value)) as List<Player>?)
            }
        }
        _player.value = Player("")
    }

    fun updateCurrentPlayer(name: String) {
        _player.value = Player(name)
    }


    private fun getNewPlayer(): String {
        return _players.value?.shuffled()?.random()?.name.toString()
    }

    private fun getNewQuestionRound(): String {
        return allQuestions.value?.shuffled()?.random()?.content.toString()
    }

    fun roundHandling() {
        _endGame.value = false
        _askedPlayer.value = getNewPlayer()
        _question.value = getNewQuestionRound()
        while (_askedPlayers.value?.contains(_askedPlayer.value) == true) {
            if (_askedPlayers.value?.size!! == _players.value?.size!!) {
                _askedPlayers.value = listOf()
            }
            _askedPlayer.value = getNewPlayer()

        }
        while (_askedQuestions.value?.contains(_question.value.toString()) == true) {

            // end of the game condition below
            if (_askedQuestions.value?.size!! == (allQuestions.value?.size!!) - 1) {
                _endGame.value = true
                _question.value = "Koniec gry, naciśnij by zagrać ponownie"
                _askedPlayer.value = String()
                _players.value = listOf()
                _askedQuestions.value = listOf()
                _askedPlayers.value = listOf()
            } else {
                _question.value = getNewQuestionRound()

            }
        }
        if (_endGame.value == false) {
            _askedPlayers.value = _askedPlayers.value?.plus(_askedPlayer.value.toString())
            _askedQuestions.value = _askedQuestions.value?.plus(_question.value.toString())
        }
    }


    class QuestionViewModelFactory(private val questionRepository: QuestionRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(QuestionViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return QuestionViewModel(questionRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
