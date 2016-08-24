package com.kirichko.amocrm_kirichko_test_task.di.components;

import com.kirichko.amocrm_kirichko_test_task.di.modules.ActivityScope;
import com.kirichko.amocrm_kirichko_test_task.di.modules.PixbayHitsModule;
import com.kirichko.amocrm_kirichko_test_task.view.PixbayHitsFragment;

import dagger.Component;

/**
 * Created by Киричко on 22.08.2016.
 */
@ActivityScope
@Component(
        dependencies = IAppComponent.class,
        modules = PixbayHitsModule.class
)
public interface IPixbayHitComponent {
    void inject(PixbayHitsFragment pixbayHitsFragment);
}
