package hr.ferit.lloina.lv4_zad1

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BirdViewModel(val context: Context) : ViewModel() {
    private val _birdCount : MutableLiveData<Int> = MutableLiveData()
    private val _color : MutableLiveData<Int> = MutableLiveData()
    private val _textColor : MutableLiveData<Int> = MutableLiveData()

    init{
        val prefs = context.getSharedPreferences("birds", AppCompatActivity.MODE_PRIVATE)
        _birdCount.value = prefs.getInt("count", DEFAULT_COUNTER)
        _color.value = prefs.getInt("color", DEFAULT_COLOR)
        _textColor.value = prefs.getInt("textColor", DEFAULT_TEXT_COLOR)
    }

    val birdCount = _birdCount as LiveData<Int>
    val color = _color as LiveData<Int>
    val textColor = _textColor as LiveData<Int>

    fun incrementCounter(){
        val count = _birdCount.value ?: DEFAULT_COUNTER
        _birdCount.value = count + 1
        val prefs = context.getSharedPreferences("birds", AppCompatActivity.MODE_PRIVATE)
        with(prefs.edit()){
            putInt("count", count)
            apply()
        }
    }

    fun setColors(color : Int, textColor : Int = DEFAULT_TEXT_COLOR){
        _color.value = color
        _textColor.value = textColor

        val prefs = context.getSharedPreferences("birds", AppCompatActivity.MODE_PRIVATE)
        with(prefs.edit()){
            putInt("color", color)
            putInt("textColor", textColor)
            apply()
        }
    }

    fun reset(){
        _birdCount.value = DEFAULT_COUNTER;
        _color.value = DEFAULT_COLOR
        _textColor.value = DEFAULT_TEXT_COLOR

        val prefs = context.getSharedPreferences("birds", AppCompatActivity.MODE_PRIVATE)
        with(prefs.edit()){
            putInt("count", DEFAULT_COUNTER)
            putInt("color", DEFAULT_COLOR)
            putInt("textColor", DEFAULT_TEXT_COLOR)
            apply()
        }
    }

    companion object{
        private val DEFAULT_COUNTER = 0
        private val DEFAULT_COLOR = Color.rgb(255,255,255)
        private val DEFAULT_TEXT_COLOR = Color.rgb(0,0,0)
    }
}