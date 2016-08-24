package com.kirichko.amocrm_kirichko_test_task.di.components;

import com.kirichko.amocrm_kirichko_test_task.di.modules.ActivityScope;
import com.kirichko.amocrm_kirichko_test_task.di.modules.HostViewModule;
import com.kirichko.amocrm_kirichko_test_task.view.MainActivity;

import dagger.Component;

/**
 * Created by Киричко on 24.08.2016.
 */
@ActivityScope
@Component(
        dependencies = IAppComponent.class,
        modules = HostViewModule.class
)
public interface IHostViewComponent {
    void inject(MainActivity mainActivity);
}
