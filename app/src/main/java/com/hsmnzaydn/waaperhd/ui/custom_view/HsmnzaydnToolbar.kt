package com.hsmnzaydn.waaperhd.ui.custom_view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.hsmnzaydn.waaperhd.R
import com.hsmnzaydn.waaperhd.ui.FragmentView

class HsmnzaydnToolbar(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    lateinit var backIcon: AppCompatImageView
    lateinit var searchIcon: AppCompatImageView
    lateinit var searchEditText: EditText
    lateinit var closeSearchEditText: AppCompatImageView
    init {
        inflate(context, R.layout.toolbar, this)

        backIcon = findViewById(R.id.toolbar_back_icon_imageView)
        val titleTextView: AppCompatTextView = findViewById(R.id.toolbar_title_textView)
        searchIcon = findViewById(R.id.toolbar_search_imageView)
        searchEditText = findViewById(R.id.toolbar_search_editText)
        closeSearchEditText = findViewById(R.id.toolbar_close_imageView)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ToolbarView)
        R.styleable.ToolbarView_backIcon
        backIcon.setImageDrawable(attributes.getDrawable(R.styleable.ToolbarView_backIcon))
        titleTextView.text = attributes.getString(R.styleable.ToolbarView_text)
        var isShowsearchIcon = attributes.getBoolean(R.styleable.ToolbarView_searchIconVisibility,false)

        if(isShowsearchIcon){
            searchIcon.run {
                visibility = View.VISIBLE
            }
        }



        attributes.recycle()
        searchIcon.setOnClickListener {
            searchEditText.run {
                visibility = View.VISIBLE
                searchIcon.visibility = View.GONE
                titleTextView.visibility = View.GONE
                closeSearchEditText.visibility = View.VISIBLE
            }
        }

        closeSearchEditText.setOnClickListener {
            closeSearchEditText.visibility = View.GONE
            searchEditText.visibility = View.GONE
            searchIcon.visibility = View.VISIBLE
            titleTextView.visibility = View.VISIBLE
        }
    }


    fun listenerSearchEdittext(searchListener:(text:String) -> Unit){
        searchEditText.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                searchListener(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    fun onClickBackIcon(controller: FragmentView) {
        backIcon.setOnClickListener {
            controller.navigateUp()
        }
        setOnClickListener { }
    }
}