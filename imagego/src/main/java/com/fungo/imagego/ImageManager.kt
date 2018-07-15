package com.fungo.imagego

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import com.fungo.imagego.create.ImageGoStrategy
import com.fungo.imagego.glide.GlideImageGoStrategy
import com.fungo.imagego.listener.OnImageListener
import com.fungo.imagego.listener.OnImageSaveListener
import com.fungo.imagego.listener.OnProgressListener
import com.fungo.imagego.progress.ProgressEngine
import com.fungo.imagego.utils.ImageGoConstant
import com.fungo.imagego.utils.ImageGoUtils

/**
 * @author Pinger
 * @since 3/28/18 10:50 AM
 */
class ImageManager {

    /**
     * 单利对象
     */
    companion object {
        @JvmStatic
        val instance: ImageManager = ImageManager()
    }

    /***
     * 图片的加载策略，抽取常用的图片加载方法到基类里，由具体的去实现，使用时只需要基类接口
     */
    private var mImageStrategy: ImageGoStrategy? = null


    /**
     * 初始化加载策略，在Application中设置
     * 目前可提供选择的策略有#GlideImageGoFactory和#PicassoImageGoFactory两种
     */
    fun setImageGoStrategy(strategy: ImageGoStrategy) {
        mImageStrategy = strategy
    }

    /**
     * 普通图片，默认的监听对象为null
     */
    fun loadImage(url: String?, imageView: ImageView?) {
        loadImage(url, imageView, null)
    }

    /**
     * 普通图片，没有加载的渐变动画
     */
    fun loadImageNoFade(url: String?, imageView: ImageView?) {
        checkStrategy()
        mImageStrategy!!.loadImageNoFade(url, imageView)
    }

    fun loadBitmap(context: Context?, url: String?, listener: OnImageListener?) {
        checkStrategy()
        return mImageStrategy!!.loadBitmapImage(context, url, listener)
    }

    /**
     * 加载图片，根据图片后缀是否以.GIF结尾，如果是则加载GIF图片
     */
    fun loadImage(url: String?, imageView: ImageView?, listener: OnImageListener?) {
        checkStrategy()
        if (ImageGoUtils.isGif(url)) {
            mImageStrategy!!.loadGifImage(url, imageView, listener)
        } else {
            mImageStrategy!!.loadImage(url, imageView, listener)
        }
    }

    /**
     * 加载图片，带进度条
     */
    fun loadImageWithProgress(url: String?, imageView: ImageView?, listener: OnProgressListener) {
        ProgressEngine.addProgressListener(listener)
        loadImage(url, imageView, null)
    }

    /**
     * 保存图片到本地
     */
    fun saveImage(context: Context?, url: String?, listener: OnImageSaveListener?) {
        checkStrategy()
        mImageStrategy?.saveImage(context, url, listener)
    }

    /**
     * 清除图片缓存
     */
    fun clearImageCache(context: Context?) {
        checkStrategy()
        mImageStrategy!!.clearImageCache(context)
    }

    /**
     * 图片缓存大小
     */
    fun getImageCacheSize(context: Context?): String {
        checkStrategy()
        return mImageStrategy!!.getCacheSize(context)
    }


    /**
     * 检查当前的策略是不是空的，如果是空的则默认使用Glide加载
     */
    private fun checkStrategy() {
        if (mImageStrategy == null) {
            ImageGoUtils.log(ImageGoConstant.ERROR_STRATEGY_NULL)
            mImageStrategy = GlideImageGoStrategy()
        }
    }
}