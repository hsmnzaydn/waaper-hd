package com.hsmnzaydn.waaperhd.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.hsmnzaydn.waaperhd.R
import com.hsmnzaydn.waaperhd.ui.FragmentView

class HsmnzaydnToolbar(context: Context, attrs: AttributeSet):LinearLayout(context, attrs) {

    lateinit var backIcon:AppCompatImageView
    init {
        inflate(context, R.layout.toolbar, this)

         backIcon = findViewById(R.id.toolbar_back_icon_imageView)
        val titleTextView: AppCompatTextView = findViewById(R.id.toolbar_title_textView)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ToolbarView)
        R.styleable.ToolbarView_backIcon
        backIcon.setImageDrawable(attributes.getDrawable(R.styleable.ToolbarView_backIcon))
        titleTextView.text = attributes.getString(R.styleable.ToolbarView_text)
        attributes.recycle()
    }

    fun onClickBackIcon(controller:FragmentView){
        backIcon.setOnClickListener {
            controller.navigateUp()
        }
        setOnClickListener {  }
    }
}