package hr.ferit.lloina.lv4_zad1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import hr.ferit.lloina.lv4_zad1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewModel : BirdViewModel
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = BirdViewModel(this.applicationContext)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.btnCrna.setOnClickListener { handleButtonClick(Color.rgb(0,0,0), Color.rgb(255,255,255))}
        binding.btnBijela.setOnClickListener { handleButtonClick(Color.rgb(255,255,255))}
        binding.btnPlava.setOnClickListener { handleButtonClick(Color.rgb(0,0,255)) }
        binding.btnCrvena.setOnClickListener { handleButtonClick(Color.rgb(255,0,0))}
        binding.btnZuta.setOnClickListener { handleButtonClick(Color.rgb(255,255,0)) }
        binding.btnZelena.setOnClickListener { handleButtonClick(Color.rgb(0,255,0))}
        binding.btnReset.setOnClickListener { viewModel.reset() }

    }

    private fun handleButtonClick(color : Int, textColor : Int = Color.rgb(0,0,0)){
        viewModel.incrementCounter()
        viewModel.setColors(color, textColor)
    }
}