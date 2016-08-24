package com.kirichko.amocrm_kirichko_test_task.di.modules;

import com.kirichko.amocrm_kirichko_test_task.presenter.PixbayHitsPresenter;
import com.kirichko.amocrm_kirichko_test_task.presenter.IPixbayHitsPresenter;
import com.kirichko.amocrm_kirichko_test_task.view.IPixbayHitsView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Киричко on 22.08.2016.
 *  Dagger модуль для создания PixbayHitsPresenter
 */

@Module
public class PixbayHitsModule {

    IPixbayHitsView mPixbayHitsView;

    public PixbayHitsModule(IPixbayHitsView pixbayHitsView) {
        mPixbayHitsView = pixbayHitsView;
    }

    @Provides
    public IPixbayHitsView provideView() {
        return mPixbayHitsView;
    }

    @Provides
    IPixbayHitsPresenter providePixbayHitsPresenter(IPixbayHitsView dealsView) {
        return new PixbayHitsPresenter(dealsView);
    }
}
