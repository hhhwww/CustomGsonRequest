package com.xd.customgsonrequest;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by hhhhwei on 16/3/20.
 * 自定义GsonRequest，用法参考StringRequest和此类中的构造函数
 */
public class GsonRequest<T> extends Request<T> {

    private final Response.Listener<T> mListener;

    private Class<T> mClass;

    private Gson mGson;

    public GsonRequest(int method, String url, Class<T> clazz, Response.Listener<T> mListener, Response.ErrorListener listener) {
        super(method, url, listener);
        this.mListener = mListener;
        this.mClass = clazz;
        mGson = new Gson();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String jsonResult = new String(networkResponse.data,
                    HttpHeaderParser.parseCharset(networkResponse.headers));
            return Response.success(mGson.fromJson(jsonResult, mClass),
                    HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T t) {
        mListener.onResponse(t);
    }
}
