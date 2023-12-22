package com.rian.bodyfittest.customview

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.rian.bodyfittest.R
import java.util.regex.Pattern

class EditTextForEmail : AppCompatEditText {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Menambahkan hint pada editText

        // Menambahkan text aligmnet pada editText
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val text = s.toString()
                if (!isValidEmail(text)) {
                    setError (resources.getString(R.string.invalid_email), null)
                } else {
                    error = null
                }
            }

            override fun afterTextChanged(editable: Editable) {
                // Do nothing.
            }
        })
        val filter = InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!isValidCharacter(source[i])) {
                    return@InputFilter ""
                }
            }
            null
        }
        filters = arrayOf(filter)
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        return emailPattern.matcher(email).matches()
    }

    private fun isValidCharacter(char: Char): Boolean {
        // Batas karakter yang bisa diinput
        val allowedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.@-_"
        return allowedCharacters.contains(char)
    }
}