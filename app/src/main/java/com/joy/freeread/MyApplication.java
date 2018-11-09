package com.joy.freeread;

import android.app.Application;
import android.content.Context;

import com.joy.freeread.net.Tls12SocketFactory;
import com.joy.freeread.utils.HttpsUtils;

import java.io.InputStream;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {

    private static Context context;
    private static OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initOkHttpClient();
    }

    private void initOkHttpClient() {
        InputStream bksFile = context.getResources().openRawResource(R.raw.gank);
        InputStream[] certificates = {bksFile};
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(certificates, bksFile, "123456");
        okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(new Tls12SocketFactory(sslParams.sSLSocketFactory), sslParams.trustManager)
                .build();
    }

    public static Context getContext() {
        return context;
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

}
