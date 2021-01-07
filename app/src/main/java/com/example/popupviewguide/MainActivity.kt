package com.example.popupviewguide

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import com.example.popupviewguide.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var bindind: ActivityMainBinding

    private var popupView = PopupView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        bindind.btnTopLeft.setOnClickListener { view -> clickedTopLeft(view) }
        bindind.btnTopRight.setOnClickListener { view -> clickedTopRight(view) }
        bindind.btnRight.setOnClickListener { view -> clickedRight(view) }
        bindind.btnBottomRight.setOnClickListener { view -> clickedBottomRight(view) }
        bindind.btnBottomLeft.setOnClickListener { view -> clickedBottomLeft(view) }
        bindind.btnLeft.setOnClickListener { view -> clickedLeft(view) }
        bindind.btnCenter.setOnClickListener { view -> clickedCenter(view) }

    }

    private fun clickedTopLeft(view: View) {
        popupView.showPopupWindow(view, windowManager, PopupView.PositionPopup.BOTTOM_RIGHT)
        popupView.setOnClickListener(object : PopupView.OnClickListener {
            override fun onPrevius(popupWindow: PopupWindow) {
                popupWindow.dismiss()
                Log.e("clickedTopLeft", "onPrevius: ")
            }

            override fun onNext(popupWindow: PopupWindow) {
                popupWindow.dismiss()
                bindind.btnTopRight.callOnClick()
                Log.e("clickedTopLeft", "onNext: ")
            }
        })
    }

    private fun clickedTopRight(view: View) {
        popupView.showPopupWindow(view, windowManager, PopupView.PositionPopup.BOTTOM_LEFT)
        popupView.setOnClickListener(object : PopupView.OnClickListener {
            override fun onPrevius(popupWindow: PopupWindow) {
                popupWindow.dismiss()
                bindind.btnTopLeft.callOnClick()
                Log.e("clickedTopRight", "onPrevius: ")
            }

            override fun onNext(popupWindow: PopupWindow) {
                popupWindow.dismiss()
                bindind.btnRight.callOnClick()
                Log.e("clickedTopRight", "onNext: ")
            }
        })
    }

    private fun clickedRight(view: View) {
        popupView.showPopupWindow(view, windowManager, PopupView.PositionPopup.LEFT)
        popupView.setOnClickListener(object : PopupView.OnClickListener {
            override fun onPrevius(popupWindow: PopupWindow) {
                popupWindow.dismiss()
                bindind.btnTopRight.callOnClick()
                Log.e("clickedTopRight", "onPrevius: ")
            }

            override fun onNext(popupWindow: PopupWindow) {
                popupWindow.dismiss()
                bindind.btnBottomRight.callOnClick()
                Log.e("clickedTopRight", "onNext: ")
            }
        })
    }

    private fun clickedBottomRight(view: View) {
        popupView.showPopupWindow(view, windowManager, PopupView.PositionPopup.TOP_LEFT)
        popupView.setOnClickListener(object : PopupView.OnClickListener {
            override fun onPrevius(popupWindow: PopupWindow) {
                popupWindow.dismiss()
                bindind.btnRight.callOnClick()
                Log.e("clickedBottomRight", "onPrevius: ")
            }

            override fun onNext(popupWindow: PopupWindow) {
                popupWindow.dismiss()
                bindind.btnBottomLeft.callOnClick()
                Log.e("clickedBottomRight", "onNext: ")
            }
        })
    }

    private fun clickedBottomLeft(view: View) {
        popupView.showPopupWindow(view, windowManager, PopupView.PositionPopup.TOP_RIGHT)
        popupView.setOnClickListener(object : PopupView.OnClickListener {
            override fun onPrevius(popupWindow: PopupWindow) {
                popupWindow.dismiss()
                bindind.btnBottomRight.callOnClick()
                Log.e("clickedBottomLeft", "onPrevius: ")
            }

            override fun onNext(popupWindow: PopupWindow) {
                popupWindow.dismiss()
                bindind.btnLeft.callOnClick()
                Log.e("clickedBottomLeft", "onNext: ")
            }
        })
    }

    private fun clickedLeft(view: View) {
        popupView.showPopupWindow(view, windowManager, PopupView.PositionPopup.RIGHT)
        popupView.setOnClickListener(object : PopupView.OnClickListener {
            override fun onPrevius(popupWindow: PopupWindow) {
                popupWindow.dismiss()
                bindind.btnBottomLeft.callOnClick()
                Log.e("clickedBottomLeft", "onPrevius: ")
            }

            override fun onNext(popupWindow: PopupWindow) {
                popupWindow.dismiss()
                bindind.btnCenter.callOnClick()
                Log.e("clickedBottomLeft", "onNext: ")
            }
        })
    }

    private fun clickedCenter(view: View) {
        popupView.showPopupWindow(view, windowManager, PopupView.PositionPopup.BOTTOM_LEFT)
        popupView.setOnClickListener(object : PopupView.OnClickListener {
            override fun onPrevius(popupWindow: PopupWindow) {
                popupWindow.dismiss()
                bindind.btnLeft.callOnClick()
                Log.e("clickedBottomLeft", "onPrevius: ")
            }

            override fun onNext(popupWindow: PopupWindow) {
                popupWindow.dismiss()
                Log.e("clickedBottomLeft", "onNext: ")
            }
        })
    }

}