package com.example.android.customfancontroller

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.Collections.min
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

/*
RIASSUNTO DEI COMPITI

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
*/


private enum class FanSpeed(val label: Int) {
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high);
}

private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35

class DialView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var radius = 0.0f                   // Radius of the circle.
    private var fanSpeed = FanSpeed.OFF  //The active selection.
    //position variable which will be used to draw label and indicator circle position
    private val pointPosition: PointF = PointF(0.0f, 0.0f)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create( "", Typeface.BOLD)
    }

    private fun PointF.computeXYForSpeed(pos: FanSpeed, radius: Float) {
        // Angles are in radians.
        val startAngle = Math.PI * (9 / 8.0)
        val angle = startAngle + pos.ordinal * (Math.PI / 4)
        x = (radius * cos(angle)).toFloat() + width / 2
        y = (radius * sin(angle)).toFloat() + height / 2
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Set dial background color to green if selection not off.
        paint.color = if (fanSpeed == FanSpeed.OFF) Color.GRAY else Color.GREEN

        // Draw the dial.
        canvas!!.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)

        // Draw the indicator circle.
        val markerRadius = radius + RADIUS_OFFSET_INDICATOR
        pointPosition.computeXYForSpeed(fanSpeed, markerRadius)
        paint.color = Color.BLACK
        canvas.drawCircle(pointPosition.x, pointPosition.y, radius/12, paint)

        val labelRadius = radius + RADIUS_OFFSET_LABEL
        for (i in FanSpeed.values()) {
            pointPosition.computeXYForSpeed(i, labelRadius)
            val label = resources.getString(i.label)
            canvas.drawText(label, pointPosition.x, pointPosition.y, paint)
        }

    }
}