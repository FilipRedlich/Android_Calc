package com.sema.my2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlin.math.pow

class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {
    private var currentOperation: String = " "
    var result: Float = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner : Spinner = findViewById(R.id.operationSpinner)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val op1Button = findViewById<Button>(R.id.firstOperationButton)
                val op2Button = findViewById<Button>(R.id.secondOperationButton)
                val fab = findViewById<FloatingActionButton>(R.id.clearButton)
                val clrHist = findViewById<Button>(R.id.clrHistory)
                fab.setOnClickListener {
                    clearCalculator()
                }
                clrHist.setOnClickListener {
                    clearHistory()
                }
                // postion -> p2
                when (p2) {
                    0 -> {
                        op1Button.text = "+"
                        op2Button.text = "-"
                    }
                    1 ->  {
                        op1Button.text = "*"
                        op2Button.text = "/"
                    }
                    2 ->  {
                        op1Button.text = "^"
                        op2Button.text = "-/"
                    }
                    else -> {
                        op1Button.text = "+"
                        op2Button.text = "-"
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        val calcButton: Button = findViewById(R.id.calcButton)
        calcButton.setOnClickListener(this)
        calcButton.setOnLongClickListener(this)

    }

    private fun clearHistory() {
        findViewById<TextView>(R.id.calc1).text = ""
        findViewById<TextView>(R.id.calc2).text = ""
        findViewById<TextView>(R.id.calc3).text = ""
        findViewById<TextView>(R.id.calc4).text = ""
        findViewById<TextView>(R.id.calc5).text = ""
        findViewById<TextView>(R.id.calc6).text = ""
        findViewById<TextView>(R.id.calc7).text = ""
        findViewById<TextView>(R.id.calc8).text = ""
        findViewById<TextView>(R.id.calc9).text = ""
        findViewById<TextView>(R.id.calc10).text = ""
        Snackbar.make(
            findViewById(R.id.clearButton),
            "Cleared History",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun clearCalculator() {
        currentOperation = " "
        result = 0f
        updateOperation()
        updateResult(getString(R.string.calculate))
        findViewById<EditText>(R.id.firstNumber).text.clear()
        findViewById<EditText>(R.id.secondNumber).text.clear()
        Snackbar.make(
            findViewById(R.id.clearButton),
            getString(R.string.clear_msg),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    fun selectOperation(view: View) {
        currentOperation = (view as Button).text.toString()
        updateOperation()
    }

    private fun updateOperation() {
        val operationTxt = findViewById<TextView>(R.id.operatorSymbol)
        operationTxt.text = currentOperation
    }


    override fun onClick(p0: View?) {
        val firstNum =
            (findViewById<EditText>(R.id.firstNumber).text).toString().toFloatOrNull() ?: 0f
        val secondNum =
            (findViewById<EditText>(R.id.secondNumber).text).toString().toFloatOrNull() ?: 0f
        val resultStr: String = getResult(firstNum,secondNum)
        updateResult(resultStr)
        //return true
    }

    override fun onLongClick(p0: View?): Boolean {
        val firstNum =
            (findViewById<EditText>(R.id.firstNumber).text).toString().toFloatOrNull() ?: 0f
        val secondNum =
            (findViewById<EditText>(R.id.secondNumber).text).toString().toFloatOrNull() ?: 0f
        val resultStr: String = getResult(firstNum,secondNum, updateResult = true)
        updateResult(resultStr)
        return true
    }
    private fun updateResult(resultStr: String) {
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        resultTextView.text = resultStr
        val calc1 = findViewById<TextView>(R.id.calc1)
        val calc2 = findViewById<TextView>(R.id.calc2)
        val calc3 = findViewById<TextView>(R.id.calc3)
        val calc4 = findViewById<TextView>(R.id.calc4)
        val calc5 = findViewById<TextView>(R.id.calc5)
        val calc6 = findViewById<TextView>(R.id.calc6)
        val calc7 = findViewById<TextView>(R.id.calc7)
        val calc8 = findViewById<TextView>(R.id.calc8)
        val calc9 = findViewById<TextView>(R.id.calc9)
        val calc10 = findViewById<TextView>(R.id.calc10)
        if(resultStr != getString(R.string.calculate))  {
            if(calc1.text=="")   {
                calc1.text = resultStr
            }else   {
                if(calc2.text==""){
                    calc2.text=calc1.text
                    calc1.text=resultStr
                }else{
                    if(calc3.text==""){
                        calc3.text=calc2.text
                        calc2.text=calc1.text
                        calc1.text=resultStr
                    }else{
                        if(calc4.text==""){
                            calc4.text=calc3.text
                            calc3.text=calc2.text
                            calc2.text=calc1.text
                            calc1.text=resultStr
                        }else   {
                            if(calc5.text==""){
                                calc5.text=calc4.text
                                calc4.text=calc3.text
                                calc3.text=calc2.text
                                calc2.text=calc1.text
                                calc1.text=resultStr
                            }else   {
                                if(calc6.text==""){
                                    calc6.text=calc5.text
                                    calc5.text=calc4.text
                                    calc4.text=calc3.text
                                    calc3.text=calc2.text
                                    calc2.text=calc1.text
                                    calc1.text=resultStr
                                }else  {
                                    if(calc7.text==""){
                                        calc7.text=calc6.text
                                        calc6.text=calc5.text
                                        calc5.text=calc4.text
                                        calc4.text=calc3.text
                                        calc3.text=calc2.text
                                        calc2.text=calc1.text
                                        calc1.text=resultStr
                                    }else{
                                        if(calc8.text==""){
                                            calc8.text=calc7.text
                                            calc7.text=calc6.text
                                            calc6.text=calc5.text
                                            calc5.text=calc4.text
                                            calc4.text=calc3.text
                                            calc3.text=calc2.text
                                            calc2.text=calc1.text
                                            calc1.text=resultStr
                                        }else   {
                                            if(calc9.text==""){
                                                calc9.text=calc8.text
                                                calc8.text=calc7.text
                                                calc7.text=calc6.text
                                                calc6.text=calc5.text
                                                calc5.text=calc4.text
                                                calc4.text=calc3.text
                                                calc3.text=calc2.text
                                                calc2.text=calc1.text
                                                calc1.text=resultStr
                                            }else{
                                                calc10.text=calc9.text
                                                calc9.text=calc8.text
                                                calc8.text=calc7.text
                                                calc7.text=calc6.text
                                                calc6.text=calc5.text
                                                calc5.text=calc4.text
                                                calc4.text=calc3.text
                                                calc3.text=calc2.text
                                                calc2.text=calc1.text
                                                calc1.text=resultStr
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    private fun getResult(firstNum: Float, secondNum: Float, updateResult: Boolean = false): String {
        val prevResult: Float = if (updateResult)  {
            result
        } else {
            0f
        }
        result = when (currentOperation) {
            "+" -> prevResult + firstNum + secondNum
            "-" -> {
                if (prevResult != 0f)
                    prevResult - (firstNum - secondNum)
                else{
                    firstNum - secondNum
                }
            }
            "*" -> {
                if (prevResult != 0f)
                    if(secondNum != 0f)
                        prevResult * firstNum * secondNum
                    else{
                        prevResult * firstNum
                    }
                else{
                    firstNum * secondNum
                }
            }
            "/" -> {
                if (prevResult != 0f)
                    if(secondNum != 0f)
                        prevResult / firstNum / secondNum
                    else {
                        prevResult / firstNum
                    }
                else{
                    firstNum / secondNum
                }
            }
            "^" -> {
                if (prevResult != 0f)
                    if(secondNum != 0f)
                        prevResult.pow(firstNum.pow(secondNum))
                    else {
                        prevResult.pow(firstNum)
                    }
                else{
                    firstNum.pow(secondNum)
                }
            }
            "-/" -> {
                if (prevResult != 0f)
                    if(secondNum != 0f)
                        prevResult.pow(1/firstNum.pow(1/secondNum))
                    else {
                        prevResult.pow(1/firstNum)
                    }
                else{
                    firstNum.pow(1/secondNum)
                }
            }
            else -> 0f
        }
        //return "${getString(R.string.calculate)} $result"
        val zero = 0.0
        return if (updateResult)  {
            if(currentOperation == "/" && firstNum == zero.toFloat() || currentOperation == "/" && secondNum == zero.toFloat())   {
                "Can't /0"
            }else {
                "$prevResult $currentOperation $firstNum $currentOperation $secondNum = $result"
            }
        }else   {
            if(currentOperation == "/" && secondNum == zero.toFloat())  {
                "Can't /0"
            }else   {
                "$firstNum $currentOperation $secondNum = $result"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settingsItem -> startSettingsActivity()
            R.id.shareItem -> shareResult()
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private var format: String = "Float"
    private val launchSettingsActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result -> if(result.resultCode == RESULT_OK) {
        format = result.data?.getStringExtra(getString(R.string.numberFormatKey)) ?: "Float"
        /*
        Snackbar.make(
            findViewById(R.id.applyButton),
            format,
            Snackbar.LENGTH_SHORT
        ).show()
         */
        }
    }
    private fun startSettingsActivity() {
        val intent: Intent = Intent(this,SettingsActivity::class.java)
        intent.putExtra(getString(R.string.numberFormatKey),format)
        launchSettingsActivity.launch(intent)
    }

    private fun shareResult() {
        val shareIntent: Intent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT,getExpressionResult())
        if(shareIntent.resolveActivity(packageManager)!=null){
            startActivity(shareIntent)
        }
    }

    private fun getExpressionResult(): String? {
        val firstNum = (findViewById<EditText>(R.id.firstNumber).text).toString().toFloatOrNull() ?: 0f
        val secondNum = (findViewById<EditText>(R.id.secondNumber).text).toString().toFloatOrNull() ?: 0f
        val equation = "${firstNum} $currentOperation ${secondNum} = "
        return when (currentOperation) {
            "+" -> "$equation ${firstNum+secondNum}"
            "-" -> "$equation ${firstNum-secondNum}"
            "*" -> "$equation ${firstNum*secondNum}"
            "/" -> "$equation ${firstNum/secondNum}"
            else -> "error"
        }
    }

}