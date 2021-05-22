package com.example.funnybutton

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.funnybutton.ToastUtil.show


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private val btn: Button by lazy { findViewById(R.id.btn) }
    private val layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
//    private val words0 = listOf(
//        "想知道我是谁吗？", "接着点我就告诉你嘻嘻(●'◡'●)", "再多点三下吧", "俩下", "一下",
//        "哈哈哈，我后悔了，毕竟我这么神秘是吧~", "要不你再点几次", "你毅力真好", "真佩服你的毅力",
//        "啊这，我累了，叫其它人来和你说话吧~", "与吐司交谈"
//    )

    private val words0 = listOf(
        "想知道我是谁吗？", "与吐司交谈"
    )
    private val words1 = listOf("hello，你好啊，我是吐司~~","很高兴与你交谈~")

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
            if (i < words0.size-1) {
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
            } else if (i == words0.size-1) {
                btn.text = words0[i++]
                layoutParams.setMargins(
                    (width - it.width) / 2,
                    (height - it.height) / 2, 0, 0
                )
                btn.layoutParams = layoutParams
            } else if (i < words0.size+words1.size-1) {
                words1[i++-words0.size].show(this)
            } else if (i == words0.size+words1.size) {

            }

        }

    }

    //获取View宽高的一种方式
//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//        if (hasFocus) {
//            Log.d(TAG, "onWindowFocusChanged:${btn.height} ${btn.width} ")
//        }
//    }
}