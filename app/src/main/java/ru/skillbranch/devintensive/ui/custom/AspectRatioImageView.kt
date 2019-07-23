package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import ru.skillbranch.devintensive.R

/**
 * Custom view
 * JvmOverloads for compilation
 */
class AspectRatioImageView @JvmOverloads constructor(
        context:Context,                                // Context
        attrs:AttributeSet?=null,                       // Attribute set
        defStyleAttr: Int = 0                           // Default styles
): ImageView(context, attrs, defStyleAttr) {
    companion object{
        private const val DEFAULT_ASPECT_RATIO = 1.78f
    }

    private var aspectRatio = DEFAULT_ASPECT_RATIO

    init {
        if(attrs!=null){
            val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView)
            aspectRatio = a.getFloat(R.styleable.AspectRatioImageView_aspectRatio, DEFAULT_ASPECT_RATIO)
            a.recycle() // release of resources
        }
    }

    /**
     *
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val newHeight = (measuredHeight/aspectRatio).toInt()
        setMeasuredDimension(measuredWidth, newHeight)
    }
}