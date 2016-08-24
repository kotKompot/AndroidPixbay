package com.kirichko.amocrm_kirichko_test_task.presenter;

/**
 * Created by Киричко on 21.08.2016.
 *
 * Интерфейс Presenter для управления одним из View, которые служат для отображения порции элементов
 */
public interface IPixbayHitsPresenter {

    /**
     * Метод запроса очередной порции элементов
     * @param startPosition начиная с какой позиции из глобального списка элементов нужны элементы
     * @param count число запрашиваемых элементов
     */
    void requestPixbayHits(int startPosition, int count);
}
