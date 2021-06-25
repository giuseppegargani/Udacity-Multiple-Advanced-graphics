package com.example.android.customfancontroller

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.Collections.min
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

//RIASSUNTO DEI TASKS
//Si può passare chiudere i vari step e passare il cursore per un suggerimento veloce

/*TODO - 1 Costruisci una funzione che quando invocata cambia il valore presente del controller al successivo
   Dentro alla classe di enumerazione aggiungi una funzione di estensione Next()
    fun next() = when (this) {
       OFF -> LOW
       LOW -> MEDIUM
       MEDIUM -> HIGH
       HIGH -> OFF
   }
*/

/* TODO - 2 Abilita quella View ad accettare input utente all'avvio (momento della inizializzazione)
    Prima di onSizeChanged() aggiungi un blocco Init{} in modo da abilitare quella view ad accettare input utente
    init {
    isClickable = true
    }
 */

/* TODO - 3 Invoca onClickListener della View
    Si fa override di PerformClick():
    override fun performClick(): Boolean {
    if (super.performClick()) return true
        fanSpeed = fanSpeed.next()
        contentDescription = resources.getString(fanSpeed.label)
        invalidate()
        return true
        }
 */

/*TODO - A che cosa serve Invalidate()?
    Costringe a rilanciare la View (la renderizzazione della View)
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
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = (min(width, height) / 2.0 * 0.8).toFloat()
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