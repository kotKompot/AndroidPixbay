package com.kirichko.amocrm_kirichko_test_task.app;

import android.app.Application;
import android.content.Context;

import com.kirichko.amocrm_kirichko_test_task.common.AbstractSwipeableFragmentFactory;
import com.kirichko.amocrm_kirichko_test_task.common.PixbayHitsFragmentFactory;
import com.kirichko.amocrm_kirichko_test_task.di.components.DaggerIAppComponent;
import com.kirichko.amocrm_kirichko_test_task.di.components.IAppComponent;
import com.kirichko.amocrm_kirichko_test_task.di.modules.AppModule;

/**
 * Created by Киричко on 21.08.2016.
 */
public class TestTaskApp extends Application {
    /**
      *  Инициализация фабрики для доступа из любой части приложения
      */
    public static AbstractSwipeableFragmentFactory swipeableFragmentFactory
            = new PixbayHitsFragmentFactory();
    private IAppComponent appComponent;
    private static TestTaskApp instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        buildGraphAndInject();
    }

    public IAppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * Инициализация DI для предоставления Application
     */
    public void buildGraphAndInject() {
        appComponent = DaggerIAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }
    public static TestTaskApp get(Context context) {
        return (TestTaskApp) context.getApplicationContext();
    }
}