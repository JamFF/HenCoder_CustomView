package com.hencoder.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.hencoder.view.ex.dp2px

private val RADIUS = 50f.dp2px
private const val TAG = "TestView"

/**
 * Description:
 * Author: 傅健
 * CreateDate: 2022/10/10 14:45
 */
class TestView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Paint.ANTI_ALIAS_FLAG 抗锯齿
    // Paint.Style.STROKE 空心
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
//        style = Paint.Style.STROKE
    }

    private val path = Path()

    private lateinit var pathMeasure: PathMeasure

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        path.reset()

        // 画圆
        path.addCircle(
            width / 4f,
            height / 4f,
            RADIUS,
            Path.Direction.CW
        )
        // Path.Direction.CW 顺时针，Path.Direction.CCW 逆时针，
        // 该参数会影响Path.FillType.WINDING和Path.FillType.EVEN_ODD的效果

        // 画矩形
        path.addRect(
            width / 4f - RADIUS,
            height / 4f,
            width / 4f + RADIUS,
            height / 4f + 2 * RADIUS,
            Path.Direction.CW
        )

        // 创建PathMeasure，forceClosed设置true，path不闭合时，会自动闭合
        pathMeasure = PathMeasure(path, false)

        Log.d(TAG, "onSizeChanged: RADIUS = ${RADIUS}px")
        Log.d(TAG, "onSizeChanged: length = ${pathMeasure.length}px")
        // pathMeasure.getPosTan()

        // 默认 Path.FillType.WINDING。Path.FillType.EVEN_ODD可做相交镂空
        path.fillType = Path.FillType.EVEN_ODD
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawLine(100f, 100f, 200f, 200f, paint)
        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)
        canvas.drawPath(path, paint)
    }
}