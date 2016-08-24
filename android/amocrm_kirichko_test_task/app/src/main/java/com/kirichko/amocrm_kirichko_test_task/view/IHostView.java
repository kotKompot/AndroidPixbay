package com.kirichko.amocrm_kirichko_test_task.view;

/**
 * Created by Киричко on 24.08.2016.
 *
 * Интерфейс главного view которое может получить число доступных элементов для правильной организации, а также
 * команды по отображению
 * остальных view.
 */
public interface IHostView {

    /** команда отобразить наличие длительного процесса пользователю*/
    void showProgress();

    /** команда прекратить отображать наличие длительного процесса пользователю*/
    void hideProgress();

    /** метод для возвращения числа доступных элементов*/
    void onTotalPixbayHitsReceived(int totalPixbayHits);

    /** команда отобразить ошибку*/
    void showError(int error);
}
