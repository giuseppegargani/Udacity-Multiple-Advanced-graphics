package com.example.android.customfancontroller

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import java.util.Collections.min
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

//RIASSUNTO DEI TASKS
//Si può passare chiudere i vari step e passare il cursore per un suggerimento veloce

/* TODO - 1 Crea un file "attrs.xml" di attributi custom per la customView (DialView) di tre colori diversi per le tre posizioni ventilatore
    <?xml version="1.0" encoding="utf-8"?>
    <resources>
       <declare-styleable name="DialView">
           <attr name="fanColor1" format="color" />
           <attr name="fanColor2" format="color" />
           <attr name="fanColor3" format="color" />
       </declare-styleable>
    </resources>
 */

/* TODO - 2 Aggiungi gli attributi nella View DialView (in ViewGroup di Activity_Main)
    app:fanColor1="#FFEB3B"
    app:fanColor2="#CDDC39"
    app:fanColor3="#009688"
 */

/* TODO - 3 Dichiara ed inizializza le variabili (fanSpeedLowColor, fanSpeedMediumColor, ...) in DialView.
    private var fanSpeedLowColor = 0
    private var fanSpeedMediumColor = 0
    private var fanSeedMaxColor = 0
    ____________________________________
    Sono private (interne alla classe)
    ____________________________________
 */

/* TODO - 4 Assegna gli attributi colore alle variabili precedenti
    Si deve fare dentro il blocco init e con la funzione di estensione withStyledAttributes
    context.withStyledAttributes(attrs, R.styleable.DialView) {
    fanSpeedLowColor = getColor(R.styleable.DialView_fanColor1, 0)
    fanSpeedMediumColor = getColor(R.styleable.DialView_fanColor2, 0)
    fanSeedMaxColor = getColor(R.styleable.DialView_fanColor3, 0)
    }
    withStyledAttributes appartiene alla libreria android-ktx
*/

/* TODO - 5 In onDraw() attribuisci il colore sulla base della velocità
    paint.color = when (fanSpeed) {
    FanSpeed.OFF -> Color.GRAY
    FanSpeed.LOW -> fanSpeedLowColor
    FanSpeed.MEDIUM -> fanSpeedMediumColor
    FanSpeed.HIGH -> fanSeedMaxColor
    } as Int
 */

private enum class FanSpeed(val label: Int) {
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high);

    fun next() = when(this){
        OFF -> LOW
        LOW -> MEDIUM
        MEDIUM -> HIGH
        HIGH -> OFF
    }
}

private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35

class DialView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var fanSpeedLowColor = 0
    private var fanSpeedMediumColor = 0
    private var fanSeedMaxColor = 0

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

    init {
        isClickable = true

        context.withStyledAttributes(attrs, R.styleable.DialView) {
            fanSpeedLowColor = getColor(R.styleable.DialView_fanColor1, 0)
            fanSpeedMediumColor = getColor(R.styleable.DialView_fanColor2, 0)
            fanSeedMaxColor = getColor(R.styleable.DialView_fanColor3, 0)
        }
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true
        fanSpeed = fanSpeed.next()
        contentDescription = resources.getString(fanSpeed.label)
        invalidate()
        return true
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
        //paint.color = if (fanSpeed == FanSpeed.OFF) Color.GRAY else Color.GREEN
        paint.color = when (fanSpeed) {
            FanSpeed.OFF -> Color.GRAY
            FanSpeed.LOW -> fanSpeedLowColor
            FanSpeed.MEDIUM -> fanSpeedMediumColor
            FanSpeed.HIGH -> fanSeedMaxColor
        } as Int

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