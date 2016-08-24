package com.kirichko.amocrm_kirichko_test_task.di.components;

import com.kirichko.amocrm_kirichko_test_task.app.TestTaskApp;
import com.kirichko.amocrm_kirichko_test_task.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Киричко on 22.08.2016.
 */

@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface IAppComponent {
    void inject(TestTaskApp app);
}