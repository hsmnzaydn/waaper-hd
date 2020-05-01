package com.basefy.core_utility

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.ImageEngine
import com.zhihu.matisse.internal.entity.CaptureStrategy

class Picasso4Engine : ImageEngine {

    override fun loadThumbnail(
        context: Context,
        resize: Int,
        placeholder: Drawable,
        imageView: ImageView,
        uri: Uri
    ) {
        //Picasso büyük resimleri yan çevirdiği için Glide çevrildi

        /*  Glide.with(context).load(uri).placeholder(placeholder)
              .override(resize, resize)
              .centerCrop()
              .into(imageView)*/

        Picasso.get().load(uri).placeholder(placeholder)
            .resize(resize, resize)
            .centerCrop()
            .into(imageView)
    }

    override fun loadGifThumbnail(
        context: Context, resize: Int, placeholder: Drawable, imageView: ImageView,
        uri: Uri
    ) {
        loadThumbnail(context, resize, placeholder, imageView, uri)
    }

    override fun loadImage(
        context: Context,
        resizeX: Int,
        resizeY: Int,
        imageView: ImageView,
        uri: Uri
    ) {
        //Picasso büyük resimleri yan çevirdiği için Glide çevrildi
        Picasso.get().load(uri).resize(resizeX, resizeY).priority(Picasso.Priority.HIGH)
            .centerInside().into(imageView)

        /*  Glide.with(context).load(uri).override(resizeX, resizeY).priority(Priority.HIGH)
              .centerInside().into(imageView)*/
    }

    override fun loadGifImage(
        context: Context,
        resizeX: Int,
        resizeY: Int,
        imageView: ImageView,
        uri: Uri
    ) {
        loadImage(context, resizeX, resizeY, imageView, uri)
    }

    override fun supportAnimatedGif(): Boolean {
        return true
    }

    object CommonMatisse {
        fun openMatisse(
            activity: Activity,
            CODE: Int,
            photoRestcount: Int,
            camera: Boolean = false
        ) {
            try {
                Matisse.from(activity)
                    .choose(MimeType.of(MimeType.MP4, MimeType.JPEG, MimeType.PNG))
                    .capture(camera)
                    .captureStrategy(CaptureStrategy(false, "com.basefy.bitarif.fileprovider"))
                    .countable(false)
                    .maxSelectable(photoRestcount)
                    .gridExpectedSize(activity.resources.getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    .thumbnailScale(0.85f)
                    .imageEngine(Picasso4Engine())
                    .showSingleMediaType(true)
                    .forResult(CODE)
            } catch (e: RuntimeException) {
                e.printStackTrace()
            }
        }
    }

}
