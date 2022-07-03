package com.rivaldofez.core.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.rivaldofez.core.R
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object ViewHelper {
    fun generateChipItem(text: String, context: Context): Chip {
        val chipItem = Chip(context, null )
        chipItem.setChipDrawable(ChipDrawable.createFromAttributes(context,null,0, R.style.ChipItem))
        chipItem.isClickable = false
        chipItem.setTextColor(ContextCompat.getColor(context,R.color.white))
        chipItem.text = text

        return chipItem
    }

    fun formatRuntime(duration: Int): String{
        val hours = duration / 60
        val minutes = duration % 60
        return ("Duration: ${hours}h ${minutes}m")
    }

    fun formatCurrency(value: Int): String{
        return (NumberFormat.getCurrencyInstance(Locale.US).format(value).toString())
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun formatDate(value: String): String{
        return try {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(value)
            SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(date)

        } catch (e: ParseException) {
            print("Error while parse date" + e.message.toString())
            "-"
        }
    }
}