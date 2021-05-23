package com.example.funnybutton

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.funnybutton.ToastUtil.show
import java.util.*


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private val btn: Button by lazy { findViewById(R.id.btn) }
    private val layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )

    private lateinit var vibrator: Vibrator

    var name = ""


    private val words0 = listOf(
        "想知道我是谁吗？", "接着点我就告诉你嘻嘻(●'◡'●)", "再多点三下吧", "俩下", "一下",
        "哈哈哈，我后悔了，毕竟我这么神秘是吧~", "要不你再点几次", "你毅力真好", "真佩服你的毅力", "我就这样静静的看着你点", "给你讲个故事吧",
        "从前有座山，山里有座庙",
        "庙里有一个老和尚和一个小和尚", "有一天老和尚给小和尚讲诗句",
        "从前有座山，山上有座庙", "师傅松下言，徒儿松上玩", "言者不知烦，玩者不觉厌",
        "待过百十年，沧海成桑田", "师傅已白骨，徒儿变鹤颜", "白云若苍狗，物是人非前", "我与松犹在，长者却长眠", "松下常驻足，耳边似有言", "从前有座山，山上有座庙",
        "啊这，我累了，叫其它人来和你说话吧~", "与吐司交谈"
    )


    private val words1 = mutableListOf(
        "hello，你好啊，我是吐司~~", "很高兴与你交谈~", "还不知道你叫什么名字呢", "你好啊!nice to meet you~",
        "初次见面，告诉你一个小秘密吧~"
    )

    private val words2 = listOf(
        "其实吐司就是我", "哈哈哈，有没有感觉我很闲", "一个人确实很无聊", "好嘛，再跟你玩些好玩的就告诉你我是谁", "我能让你的手机震动哦",
        "我还能给你狂发通知~(下滑状态栏能看)",
        "我还能变色哈哈O(∩_∩)O", "感觉我好厉害啊(手动自恋)", "好啦好啦，说正事", "来点仪式感，倒数10秒"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vibrator = this.getSystemService(VIBRATOR_SERVICE) as Vibrator
//        layoutParams.setMargins(300, 300, 300, 300)
//        layoutParams.gravity = Gravity.CENTER
//        val toAddBtn = Button(this)
//        toAddBtn.text = "test"
//        toAddBtn.setBackgroundResource(R.drawable.shape_btn_bg)
//        toAddBtn.setPadding(10, 10, 10, 10)
//        toAddBtn.layoutParams = layoutParams
//        btn.animate()
//            .translationX(1000f).duration=4000
//
//        btn.setOnClickListener {
//            ll.removeAllViews()
//            toAddBtn.text = "test" + (++i)
//            layoutParams.setMargins(350, 350, 350, 350)
//            toAddBtn.layoutParams = layoutParams
//            ll.addView(toAddBtn, layoutParams)
//        }

//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager()
//            .getDefaultDisplay().getMetrics(metrics);
//        width = metrics.widthPixels;
//        height = metrics.heightPixels;
//
//        LogUtils.e("width" + width);
//        LogUtils.e("height" + height);
        var i = 0
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val width = metrics.widthPixels
        val height = metrics.heightPixels
        Log.d(TAG, "onCreate:  width->$width height->${height}")
        //my device: width->1080 height->2322


        //After a single click,the btn change its position and text
        btn.setOnClickListener {
            if (i < words0.size - 1) {
                btn.text = words0[i++]
                Log.d(
                    TAG,
                    "onCreate: width-->$width it.width-->${it.width} width-it.width-->${width - it.width}"
                )
                layoutParams.setMargins(
                    (Math.random() * (width - it.width)).toInt(),
                    (Math.random() * (height - it.height)).toInt(), 0, 0
                )
                btn.layoutParams = layoutParams
            } else if (i == words0.size - 1) {
                btn.text = words0[i++]
                layoutParams.setMargins(
                    (width - it.width) / 2,
                    (height - it.height) / 2, 0, 0
                )
                btn.layoutParams = layoutParams
            } else if (i < words0.size + words1.size) {
                val toast = Toast.makeText(this, words1[i++ - words0.size], Toast.LENGTH_LONG)
                showMyToast(toast, 1000)
                if (i - words0.size == 3) {
                    val nameSetter = AlertDialog.Builder(this)
                    nameSetter.setTitle("请告诉我你的名字吧~")
                    val name = EditText(this)
                    val layout = LinearLayout(this)
                    layout.orientation = LinearLayout.VERTICAL
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layoutParams.setMargins(70, 0, 70, 0)
                    layout.addView(name, layoutParams)
                    nameSetter.setView(layout)
                    nameSetter.setPositiveButton("确认") { dialogInterface: DialogInterface?, i: Int? ->
                        this.name = name.text.toString()
                        words1[3] = "你好啊 " + name.text.toString() + ",nice to meet you~"
                    }
                    nameSetter.show()
                }
            } else {
                if (i - (words0.size + words1.size) == 4) {
                    val patter = longArrayOf(0, 500)
                    vibrator.vibrate(patter, 0)
                }
                if (i - (words0.size + words1.size) == 5) {
                    val manager =
                        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val manager1 =
                        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val manager2 =
                        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val channel = NotificationChannel(
                            "FunnyBtn",
                            "FunnyBtn",
                            NotificationManager.IMPORTANCE_HIGH
                        )
                        manager.createNotificationChannel(channel)
                    }
                    val intent = Intent(this, MainActivity::class.java)
                    val pi = PendingIntent.getActivity(this, 0, intent, 0)
                    val notification = NotificationCompat.Builder(this, "FunnyBtn")
                        .setContentTitle("你好啊")
                        .setContentText("我是 露替非K·show ")
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                resources,
                                R.drawable.ic_launcher_background
                            )
                        )
                        .build()
                    val notification1 = NotificationCompat.Builder(this, "FunnyBtn")
                        .setContentTitle("我的英文名是notification")
                        .setContentText("很高兴遇见你 ")
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                resources,
                                R.drawable.ic_launcher_background
                            )
                        )
                        .build()
                    val notification2 = NotificationCompat.Builder(this, "FunnyBtn")
                        .setContentTitle("没猜错的话，你的名字是叫")
                        .setContentText(name + " 是吧？")
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                resources,
                                R.drawable.ic_launcher_background
                            )
                        )
                        .build()
                    var i = 0
                    repeat(1) {
                        manager2.notify(i++, notification2)
                    }
                    repeat(2) {
                        manager1.notify(i++, notification1)
                    }
                    repeat(5) {
                        manager.notify(i++, notification)
                    }

                }
                if (i - (words0.size + words1.size) == 6) {
                    startChangeColor()
                }
                btn.text = words2[i++ - (words0.size + words1.size)]
                layoutParams.setMargins(
                    (Math.random() * (width - it.width)).toInt(),
                    (Math.random() * (height - it.height)).toInt(), 0, 0
                )
                btn.layoutParams = layoutParams
                if (i - (words0.size + words1.size) == 10) {
                    btn.isEnabled = false
                    layoutParams.setMargins(
                        (width - it.width) / 2,
                        (height - it.height) / 2, 0, 0
                    )
                    btn.layoutParams = layoutParams
                    startToEnd()
                }
            }
        }

    }

    private fun showMyToast(toast: Toast, cnt: Long) {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                toast.show()
            }

        }, 0, 3000)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                toast.cancel()
                timer.cancel()
            }
        }, cnt)
    }

    private fun startChangeColor() {
        Log.d(TAG, "startChangeColor: ")
        val color = listOf(
            Color.BLUE,
            Color.CYAN,
            Color.DKGRAY,
            Color.GRAY,
            Color.GREEN,
            Color.LTGRAY,
            Color.YELLOW
        )
        Thread {
            while (true) {
                runOnUiThread {
                    btn.setBackgroundColor(color[((Math.random() * (color.size - 1)).toInt())])
                }
                Thread.sleep(150)
            }
        }.start()
    }

    private fun startToEnd() {
        Log.d(TAG, "startToEnd: ")
        Thread {
            Thread.sleep(1000)
            var i = 10
            while (true) {
                runOnUiThread {
                    btn.text = i.toString()
                }
                Thread.sleep(1000)
                if (--i == 0) break
            }
            runOnUiThread {
                btn.text = "点火发射!!"
            }
            Thread.sleep(1000)
            val intent = Intent(this, AboutAuthorActivity::class.java)
            startActivity(intent)
        }.start()

    }


    //获取View宽高的一种方式
//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//        if (hasFocus) {
//            Log.d(TAG, "onWindowFocusChanged:${btn.height} ${btn.width} ")
//        }
//    }
}