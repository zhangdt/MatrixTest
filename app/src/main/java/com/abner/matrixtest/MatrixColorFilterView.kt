package com.abner.matrixtest

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 *
 *
 * @author zhangduntai
 * @date 2019/3/20
 */
class MatrixColorFilterView : View {

    private lateinit var mBitmap: Bitmap
    lateinit var mFilter: ColorMatrixColorFilter
    lateinit var mColorMatrix:ColorMatrix
    var mMatrix: FloatArray = floatArrayOf(
        2f, 0f, 0f, 0f, 0f,
        0f, 2f, 0f, 0f, 0f,
        0f, 0f, 2f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )
    lateinit var mPaint: Paint

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        mBitmap = Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(
                resources,
                R.mipmap.test
            ), 600, 400, false
        )
        mPaint = Paint()


        mColorMatrix = ColorMatrix()
        mColorMatrix.setSaturation(0f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(mBitmap, 100f, 100f, mPaint)
        mFilter = ColorMatrixColorFilter(mMatrix)
        mPaint.colorFilter = mFilter
        canvas?.drawBitmap(mBitmap, 100f, 500f, mPaint)
        mPaint.colorFilter = ColorMatrixColorFilter(mColorMatrix)
        canvas?.drawBitmap(mBitmap,100f,900f,mPaint)
    }
}