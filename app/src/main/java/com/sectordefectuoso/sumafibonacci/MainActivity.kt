package com.sectordefectuoso.sumafibonacci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.BigInteger
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var f = ArrayList<BigInteger>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        f.add(0, 0.toBigInteger())
        f.add(1, 1.toBigInteger())

        var resultado:BigInteger = 0.toBigInteger()
        btnCalcular.setOnClickListener {
            if (etCantidad.text.isNotEmpty()) {
                tvResultado.text = "..."
                tvCaracteres.text = "..."
                btnCalcular.isEnabled = false
                btnCalcular.isClickable = false
                Thread(Runnable {
                    resultado = sumaFibonacci(etCantidad.text.toString().toInt())
                    this@MainActivity.runOnUiThread {
                        tvResultado.text = NumberFormat.getNumberInstance(Locale.US).format(resultado)
                        tvCaracteres.text = "" + resultado.toString().length
                        btnCalcular.isEnabled = true
                        btnCalcular.isClickable = true
                    }

                }).start()
            } else{
                Toast.makeText(this, "Campo vacio", Toast.LENGTH_SHORT).show()
            }

        }
        tvResultado.setOnLongClickListener{
            Toast.makeText(this, "Numero de caracteres: "+resultado.toString().length, Toast.LENGTH_LONG).show()
            true
        }
    }

    tailrec fun sumaFibonacci(n:Int, suma:BigInteger = BigInteger.ZERO):BigInteger {
        if (n == 0) return suma
        return sumaFibonacci(n-1, suma + fibonacci(n))
    }

    fun fibonacci(n: Int):BigInteger{
        if (f.size > n) return f[n-1]
        for (i in f.size until n){
            f.add(f[i-2].add(f[i-1]))
        }
        return f[n-1]
    }
    //max 94277
}
