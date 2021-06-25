package com.example.android.customfancontroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//TODO 1 - Costruisci una classe ("DialView") che estende View e di conseguenza importa l'elemento View
//TODO 2 - Aggiungi il costruttore della View usando '@JvmOverloads'
//Approfondimento qui: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/
//Altro approfondimento: https://developer.android.com/training/custom-views/index.html
//TODO 3 - Aggiungi un Enum Class (subito sotto le importazioni) e come una altra classe
/*
private enum class FanSpeed(val label: Int) {
   OFF(R.string.fan_off),
   LOW(R.string.fan_low),
   MEDIUM(R.string.fan_medium),
   HIGH(R.string.fan_high);
}
 */
//TODO 4 - Sotto Enum Aggiungi queste costanti:
/*
private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35
 */
//TODO 5 - Dentro DialView Class importa queste costanti
/*
private var radius = 0.0f                   // Radius of the circle.
private var fanSpeed = FanSpeed.OFF  //The active selection.
//position variable which will be used to draw label and indicator circle position
private val pointPosition: PointF = PointF(0.0f, 0.0f)
 */

//TODO 6 - DENTRO DIALVIEW INIZIALIZZA UN OGGETTO PAINT (con importazioni)
/*
private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
   style = Paint.Style.FILL
   textAlign = Paint.Align.CENTER
   textSize = 55.0f
   typeface = Typeface.create( "", Typeface.BOLD)
}
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}