package com.info.app.net

import com.info.app.net.douban_entity.DoubanMomentContent
import com.info.app.net.douban_entity.DoubanMomentNews
import com.info.app.net.guokr_entity.GuokrHandpickContent
import com.info.app.net.guokr_entity.GuokrHandpickNews
import com.info.app.net.zhihu_entity.ZhihuDailyContent
import com.info.app.net.zhihu_entity.ZhihuDailyNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by zhangxiaolong on 18/3/10.
 */
interface ContentApi {
    companion object {
        val ZHIHU_DAILY_BASE = "https://news-at.zhihu.com/api/4/news/"

        val DOUBAN_MOMENT_BASE = "https://moment.douban.com/api/"

        val GUOKR_HANDPICK_BASE = "http://apis.guokr.com/minisite/"
    }


    interface ZhihuDailyService {

        @GET("before/{date}")
        fun getZhihuList(@Path("date") date: String): Call<ZhihuDailyNews>

        @GET("{id}")
        fun getZhihuContent(@Path("id") id: Int): Call<ZhihuDailyContent>

    }

    interface DoubanMomentService {

        @GET("stream/date/{date}")
        fun getDoubanList(@Path("date") date: String): Call<DoubanMomentNews>

        @GET("post/{id}")
        fun getDoubanContent(@Path("id") id: Int): Call<DoubanMomentContent>

    }

    interface GuokrHandpickService {

        @GET("article.json?retrieve_type=by_minisite")
        fun getGuokrHandpick(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<GuokrHandpickNews>

        @GET("article/{id}.json")
        fun getGuokrContent(@Path("id") id: Int): Call<GuokrHandpickContent>

    }
}