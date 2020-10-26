package com.example.sunmultipleworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*

const val INPUT_DATA_KEY = "input_data_key"
const val WORK_A_NAME = "Work A"
const val WORK_B_NAME = "Work B"
const val OUT_DATA_KEY = "out_data_key"
class MainActivity : AppCompatActivity() {
    /**
     * WorkManager  第一步
     * 获取一个WorkManager的引用
     */
    private val sunworkManager = WorkManager.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener {
            /**
             * WorkManager  第二步
             * 创建第一个工作请求
             * 请求分为两种，一种是单次请求，二种是周期性请求
             */
            val sunworkRequest1 : OneTimeWorkRequest = OneTimeWorkRequestBuilder<SunMultipleWork>()
                    .setInputData(workDataOf(INPUT_DATA_KEY to WORK_A_NAME)) //这里的 workDataOf 参数key value 用to 连接，向任务里面传递参数值
                    .build()  //单次请求
            /**
             * WorkManager  第三步
             * 创建第二个工作请求
             * 请求分为两种，一种是单次请求，二种是周期性请求
             */
            val sunworkRequest2 : OneTimeWorkRequest = OneTimeWorkRequestBuilder<SunMultipleWork>()
                    .setInputData(workDataOf(INPUT_DATA_KEY to WORK_B_NAME)) //这里的 workDataOf 参数key value 用to 连接，向任务里面传递参数值
                    .build()  //单次请求
            /**
             * WorkManager  第四步
             * 实现，执行完任务一，再执行任务二
             */
            sunworkManager.beginWith(sunworkRequest1)
                    .then(sunworkRequest2)
                    .enqueue()

        }
    }
}