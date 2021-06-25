package com.example.android.customfancontroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//TODO - 1 Override del metodo OnSizeChanged
/*
radius = (min(width, height) / 2.0 * 0.8).toFloat()
 */
//TODO - 2 Aggiungi la seguente funzione che compie dei calcoli
/*
private fun PointF.computeXYForSpeed(pos: FanSpeed, radius: Float) {
   // Angles are in radians.
   val startAngle = Math.PI * (9 / 8.0)
   val angle = startAngle + pos.ordinal * (Math.PI / 4)
   x = (radius * cos(angle)).toFloat() + width / 2
   y = (radius * sin(angle)).toFloat() + height / 2
}
 */
//TODO - 3 Fai override del metodo onDraw per disegnare            super.onDraw(canvas)
//TODO - 4 Dentro il metodo onDraw aggiungi questa riga per modificare il colore sulla base dei valori  (DOPO super)!!!!!!
/*
// Set dial background color to green if selection not off.
paint.color = if (fanSpeed == FanSpeed.OFF) Color.GRAY else Color.GREEN
 */

//TODO - 5 Disegna un cerchio (dentro onDraw)
/*
// Draw the dial.
canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)
 */
//TODO - 6 DISEGNA (dentro onDraw) un cerchio più piccolo
/*
// Draw the indicator circle.
val markerRadius = radius + RADIUS_OFFSET_INDICATOR
pointPosition.computeXYForSpeed(fanSpeed, markerRadius)
paint.color = Color.BLACK
canvas.drawCircle(pointPosition.x, pointPosition.y, radius/12, paint)
 */
//TODO - 7 Disegna le annotazioni
/*
// Draw the text labels.
val labelRadius = radius + RADIUS_OFFSET_LABEL
for (i in FanSpeed.values()) {
   pointPosition.computeXYForSpeed(i, labelRadius)
   val label = resources.getString(i.label)
   canvas.drawText(label, pointPosition.x, pointPosition.y, paint)
}
 */
//TODO - 8 in Activity_main.xml  sostituisci ImageView con DialView e cancella attributo android:background
//TODO - 9 DIALVIEW ha le seguenti impostazioni:
/*
<com.example.android.customfancontroller.DialView
       android:id="@+id/dialView"
       android:layout_width="@dimen/fan_dimen"
       android:layout_height="@dimen/fan_dimen"
       app:layout_constraintTop_toBottomOf="@+id/customViewLabel"
       app:layout_constraintLeft_toLeftOf="parent"
       app:layout_constraintRight_toRightOf="parent"
       android:layout_marginLeft="@dimen/default_margin"
       android:layout_marginRight="@dimen/default_margin"
       android:layout_marginTop="@dimen/default_margin" />
 */


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}