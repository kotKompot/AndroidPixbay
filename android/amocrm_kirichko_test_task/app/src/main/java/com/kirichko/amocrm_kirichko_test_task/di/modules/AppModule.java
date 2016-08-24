package com.kirichko.amocrm_kirichko_test_task.di.modules;

import android.app.Application;

import com.kirichko.amocrm_kirichko_test_task.app.TestTaskApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Киричко on 22.08.2016.
 */

@Module
public class AppModule {

    private final TestTaskApp app;

    public AppModule(TestTaskApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }
}