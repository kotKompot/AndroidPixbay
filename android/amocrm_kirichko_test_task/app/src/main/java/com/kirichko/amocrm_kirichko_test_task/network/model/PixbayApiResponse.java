package com.kirichko.amocrm_kirichko_test_task.network.model;

import java.util.ArrayList;

/**
 * Created by Киричко on 23.08.2016.
 * Класс соответствующй формату json объекта который приходит от серввера на запрос
 * получения PixbayHits
 */
public class PixbayApiResponse {
    public int totalHits;
    public ArrayList<PixbayHit> hits;
}
