package com.example.popupviewguide

import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import com.example.popupviewguide.databinding.ActivityMainBinding
import com.example.popupviewguide.databinding.FbPopupLayoutBinding


class MainActivity : AppCompatActivity() {

    private lateinit var bindind: ActivityMainBinding
    private lateinit var popupWindow: PopupWindow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        bindind.btnTopLeft.setOnClickListener { view ->
            onShowPopup(view, PositionPopup.BOTTOM_RIGHT)
        }

        bindind.btnTopRight.setOnClickListener { view ->
            onShowPopup(view, PositionPopup.BOTTOM_LEFT)
        }

        bindind.btnBottomLeft.setOnClickListener { view ->
            onShowPopup(view, PositionPopup.TOP_RIGHT)
        }

        bindind.btnBottomRight.setOnClickListener { view ->
            onShowPopup(view, PositionPopup.TOP_LEFT)
        }

        bindind.btnLeft.setOnClickListener { view ->
            onShowPopup(view, PositionPopup.RIGHT)
        }

        bindind.btnRight.setOnClickListener { view ->
            onShowPopup(view, PositionPopup.LEFT)
        }

        bindind.btnCenter.setOnClickListener { view ->
            onShowPopup(view, PositionPopup.TOP_LEFT)
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onShowPopup(
        view: View,
        position: PositionPopup = PositionPopup.DEFAULT,
        screenRatioSize: FloatArray = floatArrayOf(0.66F, 0.2F)
    ) {

        //Create a View object yourself through inflater
        val layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        // inflate the custom popup layout
        val inflatedView = FbPopupLayoutBinding.inflate(layoutInflater, null, false)

        // Get position from relative to inflate
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        val point = Point(location[0], location[1])


        // get device size
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        var offsetWidth = 50
        var width = (size.x * screenRatioSize[0]).toInt()
        var height = (size.y * screenRatioSize[1]).toInt()
//        var height = LinearLayout.LayoutParams.WRAP_CONTENT

        //Create a window with our parameters
        popupWindow = PopupWindow(inflatedView.root, width, height, true)
        popupWindow.isOutsideTouchable = true

        //Handler for clicking on the inactive zone of the window
        inflatedView.root.setOnTouchListener { v, event -> //Close the window when clicked
            popupWindow.dismiss()
            true
        }

        Log.e("TAG", "onShowPopup: ${popupWindow.height} : ${popupWindow.width}")

        //Set the location of the window on the screen
        when (position) {

            PositionPopup.BOTTOM_RIGHT -> {
                popupWindow.showAtLocation(
                    view, Gravity.NO_GRAVITY,
                    point.x,
                    point.y + height / 3
                )
            }

            PositionPopup.BOTTOM_LEFT -> {
                popupWindow.showAtLocation(
                    view, Gravity.NO_GRAVITY,
                        point.x - width/2,
                        point.y + height / 3
                )
            }

            PositionPopup.TOP_RIGHT -> {
                popupWindow.showAtLocation(
                    view, Gravity.NO_GRAVITY,
                    point.x,
                    point.y - height
                )
            }

            PositionPopup.TOP_LEFT -> {
                popupWindow.showAtLocation(
                    view, Gravity.NO_GRAVITY,
                        point.x - width*2/3,
                    point.y - height
                )
            }

            PositionPopup.LEFT -> {
                popupWindow.showAtLocation(
                    view, Gravity.NO_GRAVITY,
                    point.x - (width + offsetWidth),
                    point.y - height / 3
                )
            }

            PositionPopup.RIGHT -> {
                popupWindow.showAtLocation(
                    view, Gravity.NO_GRAVITY,
                    point.x + view.width + offsetWidth,
                    point.y - height / 3
                )
            }

            else -> {
                //Set the location of the window on the screen
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            }
        }


    }

    enum class PositionPopup {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        LEFT,
        RIGHT,
        DEFAULT
    }

}