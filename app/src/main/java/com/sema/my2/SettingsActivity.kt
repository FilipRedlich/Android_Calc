package com.sema.my2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.iterator
import com.google.android.material.snackbar.Snackbar

class SettingsActivity : AppCompatActivity() {
    lateinit var numberFormat: String
    private var format: String = "Float"
    private val launchSettingsActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result -> if(result.resultCode == RESULT_OK) {
            format = result.data?.getStringExtra(getString(R.string.numberFormatKey)) ?: "Float"
            Snackbar.make(
                findViewById(R.id.applyButton),
                format,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        numberFormat = intent.getStringExtra(getString(R.string.numberFormatKey)) ?: "Format"
        when(numberFormat){
            "Float" -> findViewById<RadioButton>(R.id.floatSelect).isChecked = true
            "Integer" -> findViewById<RadioButton>(R.id.integerSelect).isChecked = true
            else -> findViewById<RadioButton>(R.id.floatSelect).isChecked = true
        }
        val switch = findViewById<Switch>(R.id.enableSwitch)
        switch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, isChecked -> toggleEnable(isChecked) })
        val applyButton: Button = findViewById(R.id.applyButton)
        applyButton.setOnClickListener{
            applyChanges()
            finish()
        }
    }

    private fun toggleEnable(checked: Boolean) {
        val radioGroup = findViewById<RadioGroup>(R.id.optionsGroup)
        for(item in radioGroup){
            item.isEnabled = checked
        }
        radioGroup.setOnCheckedChangeListener{
            radioGroup,id -> numberFormat = findViewById<RadioButton>(id).text.toString()
        }
    }

    private fun applyChanges() {
        val returnIntent = Intent()
        returnIntent.putExtra(getString(R.string.numberFormatKey),numberFormat)
        setResult(RESULT_OK,returnIntent)
    }
    /*
    private fun startSettingsActivity() {
        val intent: Intent = Intent(this,SettingsActivity::class.java)
        intent.putExtra(getString(R.string.numberFormatKey),format)
        launchSettingsActivity.launch(intent)
    }
     */
}