package com.cyh.simple.app.presenter;

import com.cyh.simple.app.framework.ApiCallback;
import com.cyh.simple.app.framework.BasePresenter;
import com.cyh.simple.app.framework.BaseView;
import com.cyh.simple.app.model.bean.Weather;

/**
 * @author youhai.cai
 *         create by 2018/1/9 14:22.
 */

public class WeatherPresenter extends BasePresenter<Weather>{


    public WeatherPresenter(BaseView<Weather> mvpView) {
        super(mvpView);
    }

    public void getWeatherInfo(String cityId){
        mvpView.onStartRequest();
        addSubcription(apiStores.getWeatherInfo(cityId), new ApiCallback<Weather>() {
            @Override
            public void onSuccess(Weather model) {
                mvpView.onResponseSucceed(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.onResponseFaild(code,msg);
            }

            @Override
            public void onFinish() {
                mvpView.onFinishRequest();
            }
        });
    }


}
