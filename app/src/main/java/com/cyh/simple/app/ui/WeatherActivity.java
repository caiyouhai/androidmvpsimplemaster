package com.cyh.simple.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cyh.simple.R;
import com.cyh.simple.app.framework.BaseActivity;
import com.cyh.simple.app.framework.BaseView;
import com.cyh.simple.app.model.bean.Weather;
import com.cyh.simple.app.model.bean.WeatherInfo;
import com.cyh.simple.app.presenter.WeatherPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherActivity extends BaseActivity<WeatherPresenter> implements BaseView<Weather> {


    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.button0)
    Button button0;
    @BindView(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.button0)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button0:
                String str = editText.getText().toString();
                presenter.getWeatherInfo(str);
                break;
        }
    }

    @Override
    protected WeatherPresenter createPresenter() {
        return new WeatherPresenter(this);
    }

    @Override
    public void onResponseSucceed(Weather model) {
        dataSuccess(model);
    }

    private void dataSuccess(Weather model) {
        WeatherInfo weatherinfo = model.getWeatherinfo();
        String showData = getResources().getString(R.string.city) + weatherinfo.getCity()
                + getResources().getString(R.string.wd) + weatherinfo.getWD()
                + getResources().getString(R.string.ws) + weatherinfo.getWS()
                + getResources().getString(R.string.time) + weatherinfo.getTime();
        text.setText(showData);
    }

    @Override
    public void onResponseFaild(int code, String msg) {
        toastShow(msg);
    }

    @Override
    public void onStartRequest() {

        showProgressDialog();
    }

    @Override
    public void onFinishRequest() {
        dismissProgressDialog();
    }
}
