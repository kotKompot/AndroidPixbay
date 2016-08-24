package com.kirichko.amocrm_kirichko_test_task.presenter;

import android.util.Log;

import com.kirichko.amocrm_kirichko_test_task.R;
import com.kirichko.amocrm_kirichko_test_task.network.PixabayService;
import com.kirichko.amocrm_kirichko_test_task.network.PixbayServiceFactory;
import com.kirichko.amocrm_kirichko_test_task.network.model.PixbayApiResponse;
import com.kirichko.amocrm_kirichko_test_task.view.IHostView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Киричко on 24.08.2016.
 *
 * Реализация IHostViewPresenter для управления главным View контейнером
 */
public class MainActivityPresenter implements IHostViewPresenter {

    /**
     * Ссылка на управляймый элемент
     */
    private IHostView hostView;

    /**
     * Создание экземпляра сервиса для работы с pixabay.com
     */
    private static PixabayService service = PixbayServiceFactory.createRetrofitService(PixabayService.class, PixabayService.SERVICE_ENDPOINT);

    public MainActivityPresenter(IHostView hostView) {
        this.hostView = hostView;
    }

    /**
     * Метод создает и отправляет запрос на pixabay.com для получения общего числа доступных элементов
     */
    @Override
    public void requestTotalPixbayHits() {
        if(hostView == null) { return; }
            hostView.showProgress();
            service.getHits(PixabayService.PIX_BAY_API_KEY, 3, 0)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<PixbayApiResponse>() {
                                   @Override
                                   public final void onCompleted() {
                                       hostView.hideProgress();
                                   }

                                   @Override
                                   public final void onError(Throwable e) {
                                       Log.e("Pixbay", e.getMessage());
                                       hostView.hideProgress();
                                       hostView.onTotalPixbayHitsReceived(0);
                                       hostView.showError(R.string.network_error);
                                   }

                                   @Override
                                   public final void onNext(PixbayApiResponse response) {
                                       hostView.onTotalPixbayHitsReceived(response.totalHits);
                                   }
                               }
                    );
    }
}

