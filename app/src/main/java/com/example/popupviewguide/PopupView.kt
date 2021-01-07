package com.example.popupviewguide

import android.annotation.SuppressLint
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.graphics.Point
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import com.example.popupviewguide.databinding.FbPopupLayoutBinding


class PopupView {

    private lateinit var popupWindow: PopupWindow
    private lateinit var mOnClickListener: OnClickListener

    interface OnClickListener {
        fun onPrevius(popupWindow: PopupWindow)
        fun onNext(popupWindow: PopupWindow)
    }

    //PopupWindow display method
    @SuppressLint("ClickableViewAccessibility")
    fun showPopupWindow(view: View,
                        windowManager: WindowManager,
                        position: PositionPopup = PositionPopup.DEFAULT,
                        screenRatioSize: FloatArray = floatArrayOf(0.66F, 0.2F)) {

        //Create a View object yourself through inflater
        val layoutInflater = view.context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
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
        /*inflatedView.root.setOnTouchListener { v, event -> //Close the window when clicked
            popupWindow.dismiss()
            true
        }*/

        Log.e("TAG", "onShowPopup: ${popupWindow.height} : ${popupWindow.width}")

        inflatedView.btnPrevius.setOnClickListener {
            if (mOnClickListener != null)
                mOnClickListener.onPrevius(popupWindow)
        }

        inflatedView.btnNext.setOnClickListener {
            if (mOnClickListener != null)
                mOnClickListener.onNext(popupWindow)
        }

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
                        point.x - width / 2,
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
                        point.x - width * 2 / 3,
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

    fun setOnClickListener(onClickListener: OnClickListener) {
        mOnClickListener = onClickListener
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