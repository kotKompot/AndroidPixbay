package com.kirichko.amocrm_kirichko_test_task.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Киричко on 23.08.2016.
 *
 * Фабрика для инициализации сервиса
 */
public class PixbayServiceFactory {
   public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {

       HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
       logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
       OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
       httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endPoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        T service = retrofit.create(clazz);

        return service;
    }
}
