package com.abner.matrixtest

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.util.Log
import android.view.MotionEvent
import javax.security.auth.login.LoginException


/**
 *
 *
 * @author zhangduntai
 * @date 2019/3/19
 */
class MatrixSetPolyToPolyView : View {

    private lateinit var mBitmap: Bitmap
    private lateinit var mMatrix: Matrix
    private var mPointCount: Int = 0
    private lateinit var mPaint: Paint

    private var src: FloatArray = FloatArray(8)  //源数据
    private var dst: FloatArray = FloatArray(8)  //目标数据


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun setPointCount(count: Int) {
        Log.i("abner", count.toString())
        mPointCount = count
        resetPolyToPoly()

    }

    private fun init() {
        mBitmap =Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(
            resources,
            R.mipmap.test
        ),600,400,false)

        mMatrix = Matrix()

        mPaint = Paint()
        mPaint.color = Color.GRAY
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = 50f
        mPaint.strokeCap = Paint.Cap.ROUND

        src = floatArrayOf(
            0f, 0f, // 左上
            mBitmap.width.toFloat(), 0f, // 右上
            mBitmap.width.toFloat(), mBitmap.height.toFloat(), // 右下
            0f, mBitmap.height.toFloat()
        )                        // 左下
        dst = src.clone()
        mMatrix.postTranslate(100f, 100f)

    }
    private fun resetPolyToPoly(){
        mMatrix.reset()
        mMatrix.setPolyToPoly(src,0,dst,0,mPointCount)
        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(mBitmap, mMatrix, null)
        mMatrix.mapPoints(dst,src)
        // 画图片角的点
        for (i in 0..3) {
            canvas?.drawPoint(dst[i*2], dst[i*2 + 1], mPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var x = event?.x
        var y = event?.y
        when(event?.action){
            MotionEvent.ACTION_MOVE->{
                for (i in 0..3)
                {
                    Log.i("abner",
                        (Math.sqrt(Math.pow((dst[i*2]- x!!).toDouble(), 2.0)+Math.pow((dst[i*2+1]- y!!).toDouble(),2.0))<150).toString()
                    )
                    if (Math.sqrt(Math.pow((dst[i*2]- x!!).toDouble(), 2.0)+Math.pow((dst[i*2+1]- y!!).toDouble(),2.0))<150 )
                    {
                        dst[i*2] = x
                        dst[i*2+1] = y
                        resetPolyToPoly()
                        break
                    }
                }
            }

        }
        return true
    }
}