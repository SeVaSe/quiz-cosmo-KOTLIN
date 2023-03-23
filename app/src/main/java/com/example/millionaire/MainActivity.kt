package com.example.millionaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvValue: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    private var currentRound = 0
    private val rounds = mutableListOf<Round>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getString(R.string.app_name)
        fillRounds()
        updateUI()

        tvQuestion = findViewById(R.id.tvQuestion)
        tvQuestion.text = "Тут будет первый вопрос"
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)





        button1.setOnClickListener{
            processRound(1)
        }

        button2.setOnClickListener{
            processRound(2)
        }

        button3.setOnClickListener{
            processRound(3)
        }

        button4.setOnClickListener{
            processRound(4)
        }

    }

    //задает вопросы
    private fun fillRounds(){
        rounds.run {
            add(Round("Что такое Луна?", "Звезда", "Планета", "Спутник", "Круг сыра", 3, 100))
            add(Round("В каком году запущен первый спутник?", "1957", "1961", "1965", "1969", 1, 1_000))
            add(Round("Сколько спутников у Марса?", "0","1", "2", "4", 3, 10_000))
            add(Round("Как называется спутник Плутона?", "Нет спутников", "Харон", "Энцелад", "Ио", 2, 100_000))
            add(Round("Какой крупнейший спутник у Юпитера?","Европа", "Калисто", "Титан", "Ганимед", 4, 1_000_000))
        }
    }

    //выводит вопрос на экран
    private fun updateUI(){
        tvQuestion.text = rounds[currentRound].question
        tvValue.text = rounds[currentRound].value.toString()
        button1.text = rounds[currentRound].answer1
        button2.text = rounds[currentRound].answer2
        button3.text = rounds[currentRound].answer3
        button4.text = rounds[currentRound].answer4

    }

    //проверка ответа
    private fun checkAnswer(givenId: Int) =
        (givenId == rounds[currentRound].rightId)

    //переход на другой раунд
    private fun goNextRound():Boolean {

        if (currentRound >= rounds.size - 1) return false
        currentRound++
        updateUI()
        return true
    }

    //переход к след уровню или сообщение о проигрыше
    private fun processRound(givenId: Int){

        if (checkAnswer(givenId)){
            if (!goNextRound()){
                Toast.makeText(this, getString(R.string.wintext), Toast.LENGTH_SHORT).show()
                finish()
            }
        } else{
            Toast.makeText(this, getString(R.string.loosetext), Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    fun buttonClick(view: View) {
        try {
            val id = view.tag.toString().toInt()
            processRound(id)
        } catch (e: java.lang.Exception){
            e.printStackTrace()
        }
    }

    override fun onClick(v: View?) {
        v?.let{
            when (it.id){
                R.id.button1 -> processRound(1)
                R.id.button2 -> processRound(2)
                R.id.button3 -> processRound(3)
                R.id.button4 -> processRound(4)
                else -> return
            }
        }
    }

}