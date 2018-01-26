package com.cyh.simple.app.model;

import com.cyh.simple.app.model.bean.Weather;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * retrofit框架的网络接口
 * @author youhai.cai
 *         create by 2018/1/9 11:52.
 */
public interface ApiStores {

    String API_SERVER_URL = "http://www.weather.com.cn/";

    //加载天气
    @GET("adat/sk/{cityId}.html")
    Observable<Weather> getWeatherInfo(@Path("cityId") String cityId);

}
