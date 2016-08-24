package com.kirichko.amocrm_kirichko_test_task.di.modules;

import com.kirichko.amocrm_kirichko_test_task.presenter.IHostViewPresenter;
import com.kirichko.amocrm_kirichko_test_task.presenter.MainActivityPresenter;
import com.kirichko.amocrm_kirichko_test_task.view.IHostView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Киричко on 24.08.2016.
 *
 * Dagger модуль для создания MainActivityPresenter
 */
@Module
public class HostViewModule {

    IHostView hostView;

    public HostViewModule(IHostView hostView) {
        this.hostView = hostView;
    }

    @Provides
    public IHostView provideHostView() {
        return hostView;
    }

    @Provides
    IHostViewPresenter provideHostViewPresenter(IHostView hostView) {
        return new MainActivityPresenter(hostView);
    }
}
