package com.kirichko.amocrm_kirichko_test_task.presenter;

import android.util.Log;

import com.kirichko.amocrm_kirichko_test_task.R;
import com.kirichko.amocrm_kirichko_test_task.network.PixabayService;
import com.kirichko.amocrm_kirichko_test_task.network.model.PixbayApiResponse;
import com.kirichko.amocrm_kirichko_test_task.network.PixbayServiceFactory;
import com.kirichko.amocrm_kirichko_test_task.view.IPixbayHitsView;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Киричко on 21.08.2016.
 *
 * Реализация IPixbayHitsPresenter для управления одним из View, которые служат для отображения
 * порции элементов
 */
public class PixbayHitsPresenter implements IPixbayHitsPresenter {

    /** Ссылка на управляймый элемент */
    private IPixbayHitsView pixbayHitsView;

    /** Число элементов для единовременной */
    private static int PACK_SIZE = 5;

    /** Создание экземпляра сервиса для работы с pixabay.com */
    private static PixabayService service =
            PixbayServiceFactory.createRetrofitService(PixabayService.class,
                    PixabayService.SERVICE_ENDPOINT);

    public PixbayHitsPresenter(IPixbayHitsView pixbayHitsView)
    {
        this.pixbayHitsView = pixbayHitsView;
    }


    /**
     * Метод запрашивает очередную порцию элементов
     * @param startPosition начиная с какой позиции из глобального списка элементов нужны элементы
     * @param count число запрашиваемых элементов
     */
    @Override
    public void requestPixbayHits(final int startPosition, int count) {
        if(pixbayHitsView == null) { return; }

        for(int i = 0; i<(count/PACK_SIZE ); i++) {
            pixbayHitsView.showProgress();
            service.getHits(PixabayService.PIX_BAY_API_KEY, PACK_SIZE, startPosition/PACK_SIZE + i)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<PixbayApiResponse>() {
                                   @Override
                                   public final void onCompleted() {
                                       pixbayHitsView.hideProgress();
                                   }

                                   @Override
                                   public final void onError(Throwable e) {
                                       Log.e("Pixbay", e.getMessage());
                                       pixbayHitsView.hideProgress();
                                       if(e instanceof HttpException) {
                                           HttpException he = (HttpException) e;
                                           if(((HttpException) e).code() == 429) {
                                               pixbayHitsView.showError(R.string.network_error);
                                           }
                                       } else
                                       {
                                           pixbayHitsView.showError(R.string.network_error);
                                       }
                                   }

                                   @Override
                                   public final void onNext(PixbayApiResponse response) {
                                       if (response.hits.size() > 0 && pixbayHitsView != null) {
                                           pixbayHitsView.showData(response.hits);
                                       }
                                   }
                               }
                    );
        }
    }

}
