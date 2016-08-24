package com.kirichko.amocrm_kirichko_test_task.view;

import com.kirichko.amocrm_kirichko_test_task.network.model.PixbayHit;

import java.util.ArrayList;


/**
 * Created by Киричко on 21.08.2016.
 *
 * Интерфейс одного из view, предназначенных для отображения части элементов
 */
public interface IPixbayHitsView {

    /** команда отобразить наличие длительного процесса пользователю*/
    void showProgress();

    /** команда прекратить отображать наличие длительного процесса пользователю*/
    void hideProgress();

    /** метод для возвращения массива запрошенных элементов*/
    void showData(ArrayList<PixbayHit> pixbayHit);

    /** команда отобразить ошибку*/
    void showError(int error);
}
