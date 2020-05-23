package com.basefy.core_utility

import android.app.Activity
import android.content.res.Resources
import android.net.Uri
import android.view.View
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danikula.videocache.HttpProxyCacheServer
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.exoplayer2.video.VideoListener
import com.google.android.material.appbar.AppBarLayout


/**
 * Recylerview'ı adapterı initialize etmek için kullanılır
 * @param recyclerView: Initialize etmek istediğimiz recylerviewın referansı
 * @param layoutManager: Recylerview dizilimi için verilen Layout Manager. Örn: Horizontal, Vertical ve Grid. Default verticaldır
 * @param dividerItemDecoration: Seperator olarak verilen itemdir
 */
@JvmOverloads
fun <A : RecyclerView.Adapter<*>> A.onInit(
    recyclerView: RecyclerView,
    layoutManager: RecyclerView.LayoutManager? = null,
    dividerItemDecoration: Int? = DividerItemDecoration.VERTICAL,
    @DrawableRes separatorDrawable: Int? = null
): A {
    recyclerView.run {
        this.layoutManager = layoutManager ?: LinearLayoutManager(context)
        adapter = this@onInit
        separatorDrawable?.let {
            addItemDecoration(
                DividerItemDecoration(context, dividerItemDecoration!!).apply {
                    setDrawable(
                        ContextCompat.getDrawable(context, it)!!
                    )
                }
            )
        }
    }
    return this
}


@JvmOverloads
fun <A : RecyclerView.Adapter<*>> A.onInitGrid(
    recyclerView: RecyclerView,
    layoutManager: RecyclerView.LayoutManager? = null,
    dividerItemDecoration: Int? = DividerItemDecoration.VERTICAL,
    column: Int? = 2,
    @DrawableRes separatorDrawable: Int? = null
): A {
    recyclerView.run {
        this.layoutManager = layoutManager ?: NpaGridLayoutManager(context, column!!)
        adapter = this@onInitGrid
        separatorDrawable?.let {
            addItemDecoration(
                DividerItemDecoration(context, dividerItemDecoration!!).apply {
                    setDrawable(
                        ContextCompat.getDrawable(context, it)!!
                    )
                }
            )
        }
    }
    return this
}


fun RecyclerView.pagenation(pagenated:() -> Unit){

    this.addOnScrollListener(object : EndlessOnScrollListener() {
        override fun onScrolledToEnd() {
            pagenated()
        }
    })
}

/**
 * Bir imageviewın rengini değiştirmek için kullanılır
 * @param activity: İconun rengini değiştirmek istediğin activity
 * @param color: Image vermek istenen tint color. Default red'dir
 */
fun AppCompatImageView.changeColor(activity: Activity, color: Int? = R.color.red) {
    this.setColorFilter(
        ContextCompat.getColor(
            activity,
            color!!
        ), android.graphics.PorterDuff.Mode.SRC_IN
    )
}

/**
 * Dp değerini px e çevirmek için kullanılır
 * @param activity: Dp değerini Px'e çevirmek için kullanılan activity
 * @param dp: Değiştirilmek istenen dp değeri
 */
fun dpToPx(activity: Activity, dp: Int): Int {
    val density = activity.resources
        .displayMetrics
        .density
    return Math.round(dp.toFloat() * density)
}


/**
 * CollapsingBar durumu dinlemek için kullanılır
 * @param collapse: Appbarlayout collpase olduğu zaman tetiklenir
 * @param expanded: Appbarlayout expanded olduğu zaman tetiklenir
 * */
fun AppBarLayout.collapseListener(collapse: () -> Unit, expanded: () -> Unit) {
    this.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
        if (kotlin.math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
            collapse()
        } else {
            expanded()
        }
    })
}

/**
 * View'ın oluşturulmasını dinler
 * @param listener: View oluşturulduğunda tetiklenir
 * */
fun View.viewTreeObserverListener(listener: (view: View) -> Unit) {
    val view = this
    val viewTreeObserver = view.viewTreeObserver
    if (viewTreeObserver.isAlive) {
        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                listener(view)
            }
        })
    }
}

/**
 * Bir viewın boyutunu değiştirmek için kullanılır
 * @param activity: View'ın boyutunude değiştirmek için kullanılan activity
 * @param widthSize: View'a verilmek istenen genişlik değeri
 * @param heightSize: View'a verilmek istenen yükseklik değeri
 */
fun View.resize(activity: Activity, widthSize: Int, heightSize: Int) {
    val layout = LinearLayout(activity)
    val layoutParams = layout.layoutParams as LinearLayout.LayoutParams
    layoutParams.height = dpToPx(activity, widthSize).toInt()
    layoutParams.width = dpToPx(activity, 250).toInt()
    layout.layoutParams = layoutParams

}

/**
 * ExoPlayerı kullanmak için kullanılır. Otomatik cacheleme özelliği mevcuttur
 * @param activity: ExoPlayer'ın açıldığı activity referansıdır
 * @param progressBar: ExoPlayer viewında ortada dönen progressbardır
 * @param playPauseButton: Ortada çıkan play butonu için kullanılır
 * @param videoUrl: Oynatılması istenen video url
 */
fun PlayerView.ExoPlayerExtension(
    activity: Activity,
    progressBar: ProgressBar? = null,
    playPauseButton: AppCompatImageButton? = null,
    videoUrl: String
): SimpleExoPlayer {

    var player: SimpleExoPlayer? = null
    lateinit var bandwidthMeter: BandwidthMeter
    lateinit var mediaSource: ExtractorMediaSource
    lateinit var mediaDataSourceFactory: DataSource.Factory
    lateinit var trackSelector: DefaultTrackSelector



    bandwidthMeter = DefaultBandwidthMeter()

    val userAgent = Util.getUserAgent(activity, activity.applicationInfo.name)
    mediaDataSourceFactory = DefaultDataSourceFactory(
        activity,
        userAgent,
        bandwidthMeter
    )

    val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)

    trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

    player = ExoPlayerFactory.newSimpleInstance(activity, trackSelector)


    val proxyServer = HttpProxyCacheServer.Builder(context).maxCacheSize(1024 * 1024 * 1024).build()

    val proxyUURL = proxyServer.getProxyUrl(videoUrl)




    mediaSource = if (!false && videoUrl.contains(".mp4")) {
        ExtractorMediaSource.Factory(mediaDataSourceFactory)
            .createMediaSource(Uri.parse(proxyUURL))
    } else {
        ExtractorMediaSource.Factory(mediaDataSourceFactory)
            .createMediaSource(Uri.parse(videoUrl))
    }



    player.prepare(mediaSource, true, true)


    player.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT

    this.requestFocus()

    this.player = player

    player!!.playWhenReady = true

    progressBar?.visibility = View.GONE

    player.addVideoListener(object : VideoListener {
        override fun onVideoSizeChanged(
            width: Int,
            height: Int,
            unappliedRotationDegrees: Int,
            pixelWidthHeightRatio: Float
        ) {

        }

        override fun onRenderedFirstFrame() {
            this@ExoPlayerExtension.hideController()
        }
    })

    player.addListener(object : Player.EventListener {
        override fun onTimelineChanged(timeline: Timeline, manifest: Any?, reason: Int) {

        }

        override fun onTracksChanged(
            trackGroups: TrackGroupArray,
            trackSelections: TrackSelectionArray
        ) {

        }

        override fun onLoadingChanged(isLoading: Boolean) {


        }

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {

        }

        override fun onRepeatModeChanged(repeatMode: Int) {

        }

        override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {

        }

        override fun onPlayerError(error: ExoPlaybackException) {
        }

        override fun onPositionDiscontinuity(reason: Int) {

        }

        override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {

        }

        override fun onSeekProcessed() {}
    })
    return player
}

/**
 * Değerini dp e çevirmek için kullanılır
 * Örn:88dp
 */
val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
/**
 * Değerini dp e çevirmek için kullanılır
 * Örn:88px
 */
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()