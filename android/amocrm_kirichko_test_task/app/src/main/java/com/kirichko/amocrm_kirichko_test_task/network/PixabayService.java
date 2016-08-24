package com.kirichko.amocrm_kirichko_test_task.network;

import com.kirichko.amocrm_kirichko_test_task.network.model.PixbayApiResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Киричко on 23.08.2016.
 *
 * Сервис для работы с api pixabay.com
 */
public interface PixabayService {
    String SERVICE_ENDPOINT = "https://pixabay.com/";
    String PIX_BAY_API_KEY = "3154308-7f6fe22d1adcb1e2e4c34f5f5";

    /**
     * Функция запроса пачки элементов
     * @param key уникальный ключ приложения выданый https://pixabay.com/
     * @param per_page Число элементов в пачке
     * @param page Номер пачки
     * @return
     */
    @GET("/api")
    Observable<PixbayApiResponse> getHits(
            @Query("key") String key,
            @Query("per_page") int per_page,
            @Query("page") int page
    );
}