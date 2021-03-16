package com.example.alarstudios.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.util.Base64
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import java.io.ByteArrayOutputStream

fun Bitmap.encodeImage(): String {
  val baos = ByteArrayOutputStream()
  compress(Bitmap.CompressFormat.JPEG, 100, baos)
  val b: ByteArray = baos.toByteArray()
  return Base64.encodeToString(b, Base64.DEFAULT)
}

fun Context.getBitmapFromVectorDrawable(drawableId: Int): Bitmap? {
  var drawable = ContextCompat.getDrawable(this, drawableId) ?: return null

  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
    drawable = DrawableCompat.wrap(drawable).mutate()
  }

  val bitmap = Bitmap.createBitmap(
    drawable.intrinsicWidth,
    drawable.intrinsicHeight,
    Bitmap.Config.ARGB_8888) ?: return null
  val canvas = Canvas(bitmap)
  drawable.setBounds(0, 0, canvas.width, canvas.height)
  drawable.draw(canvas)

  return bitmap
}

