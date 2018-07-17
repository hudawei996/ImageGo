package com.fungo.imagego.strategy

import android.graphics.drawable.Drawable

/**
 * @author Pinger
 * @since 3/28/18 3:23 PM
 *
 * 图片加载库的配置，封装原始加载配置属性，进行转换
 */
class ImageConfig private constructor() {

    /**
     * 加载占位图资源ID
     */
    var placeHolderResId = 0

    /**
     * 加载占位图资源Drawable对象
     */
    var placeHolderDrawable: Drawable? = null

    /**
     * 错误占位图的资源ID
     */
    var errorResId = 0

    /**
     * 是否渐隐加载
     */
    var isCrossFade = true

    /**
     * 是否是GIF图片
     */
    var asGif: Boolean = false


    /**
     * 是否跳过内存缓存
     */
    var skipMemoryCache: Boolean = false


    /**
     * 磁盘缓存
     */
    var diskCacheStrategy = DiskCache.AUTOMATIC

    /**
     * 加载优先级
     */
    var priority = LoadPriority.NORMAL

    /**
     * 加载缩略图
     */
    var thumbnail: Float = 0f

    /**
     * 缩略图链接
     */
    var thumbnailUrl: String? = null

    /**
     * 图片的Tag
     */
    var tag: String? = null

    /**
     * 图片的尺寸
     */
    var size: OverrideSize? = null


    /**
     * 圆形图片
     */
    var isCircle: Boolean = false

    /**
     * 圆形是否带边框
     */
    var isCircleBorder: Boolean = false

    /**
     * 圆形边框的颜色
     */
    var circleBorderColor: Int = 0

    /**
     * 模糊特效
     */
    var isBlur: Boolean = false


    /**
     * 内部类，生成图片的基本配置
     */
    class Builder {
        private val config = ImageConfig()

        fun setPlaceHolderResId(placeHolderResId: Int): Builder {
            config.placeHolderResId = placeHolderResId
            return this
        }

        fun setPlaceHolderDrawable(placeHolderDrawable: Drawable): Builder {
            config.placeHolderDrawable = placeHolderDrawable
            return this
        }

        fun setErrorResId(errorResId: Int): Builder {
            config.errorResId = errorResId
            return this
        }

        fun setCrossFade(isCrossFade: Boolean): Builder {
            config.isCrossFade = isCrossFade
            return this
        }

        fun setAsGif(asGif: Boolean): Builder {
            config.asGif = asGif
            return this
        }

        fun setSkipMemoryCache(skipMemoryCache: Boolean): Builder {
            config.skipMemoryCache = skipMemoryCache
            return this
        }

        fun setDiskCacheStrategy(diskCacheStrategy: DiskCache): Builder {
            config.diskCacheStrategy = diskCacheStrategy
            return this
        }

        fun setTag(tag: String?): Builder {
            config.tag = tag
            return this
        }

        fun setPriority(priority: LoadPriority): Builder {
            config.priority = priority
            return this
        }

        fun setThumbnail(thumbnail: Float): Builder {
            config.thumbnail = thumbnail
            return this
        }

        fun setThumbnailUrl(thumbnailUrl: String): Builder {
            config.thumbnailUrl = thumbnailUrl
            return this
        }

        fun setOverideSize(width: Int, height: Int) {
            config.size = OverrideSize(width, height)
        }

        fun build(): ImageConfig {
            return config
        }
    }


    /**
     * 指定加载图片的大小
     * @param width 宽
     * @param height 高
     */
    class OverrideSize(val width: Int, val height: Int)

    /**
     * 硬盘缓存策略
     */
    enum class DiskCache(val strategy: Int) {
        /**
         * 没有缓存
         */
        NONE(1),

        /**
         * 根据原始图片数据和资源编码策略来自动选择磁盘缓存策略，需要写入权限
         */
        AUTOMATIC(2),

        /**
         * 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源
         */
        RESOURCE(3),

        /**
         * 在资源解码前就将原始数据写入磁盘缓存，需要写入权限
         */
        DATA(4),

        /**
         * 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据，需要写入权限
         */
        ALL(5)
    }

    /**
     * 加载优先级策略
     * 指定了图片加载的优先级后，加载时会按照图片的优先级进行顺序加载
     * IMMEDIATE优先级时会直接加载，不需要等待
     */
    enum class LoadPriority(val priority: Int) {
        /**
         * 低优先级
         */
        LOW(1),
        /**
         * 普通优先级
         */
        NORMAL(2),
        /**
         * 高优先级
         */
        HIGH(3),

        /**
         * 立即加载，无需等待
         */
        IMMEDIATE(4)
    }
}