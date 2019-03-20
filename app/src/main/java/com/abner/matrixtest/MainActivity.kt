package com.abner.matrixtest

import android.graphics.Matrix
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    var count =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var matrix: Matrix = Matrix()
//        var floatArray = floatArrayOf(100f,200f,300f,400f,500f,600f)
////        matrix.setValues(floatArray)
//        Log.i("abner",matrix.toShortString())
//        matrix.setScale(0.5f,0.3f)
//        Log.i("abner",matrix.toShortString())
//        matrix.mapPoints(floatArray)
//
//        Log.i("abner",Arrays.toString(floatArray))
        btn_add.setOnClickListener {
//            iv_matrix.setPointCount(count++%5)
        }
    }
}
