package com.kirichko.amocrm_kirichko_test_task.presenter;

/**
 * Created by Киричко on 24.08.2016.
 *
 * Интерфейс Presenter для управления главным View контейнером
 */
public interface IHostViewPresenter {

    /**
     * Метод запроса числа доступных элементов для загрузки, для последующего расчета отображения
     * (числа фрагменов в ViewPager например)
     */
    void requestTotalPixbayHits();
}
