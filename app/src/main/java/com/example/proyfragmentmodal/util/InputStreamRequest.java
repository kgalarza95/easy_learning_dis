package com.example.proyfragmentmodal.util;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InputStreamRequest extends Request<InputStream> {
    private final Response.Listener<InputStream> mListener;

    public InputStreamRequest(int method, String url, Response.Listener<InputStream> listener,
                              Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }

    @Override
    protected Response<InputStream> parseNetworkResponse(NetworkResponse response) {
        InputStream inputStream = new ByteArrayInputStream(response.data);
        return Response.success(inputStream, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(InputStream response) {
        mListener.onResponse(response);
    }
}

