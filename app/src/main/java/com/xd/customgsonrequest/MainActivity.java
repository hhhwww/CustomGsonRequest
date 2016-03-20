package com.xd.customgsonrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.xd.customgsonrequest.bean.Weather;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;

    public static final String URL = "http://www.weather.com.cn/adat/sk/101010100.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestQueue = Volley.newRequestQueue(this);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataByGsonRequest();
            }
        });
    }

    private void getDataByGsonRequest() {
        GsonRequest<Weather> gsonRequest = new GsonRequest<Weather>(Request.Method.GET,
                URL, Weather.class, new Response.Listener<Weather>() {
            @Override
            public void onResponse(Weather weather) {
                Log.d("XD", "city:" + weather.getWeatherinfo().getCity());
                Log.d("XD", "temp:" + weather.getWeatherinfo().getTemp());
                Log.d("XD", "tiem:" + weather.getWeatherinfo().getTime());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mRequestQueue.add(gsonRequest);
    }
}
