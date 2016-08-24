package com.kirichko.amocrm_kirichko_test_task.common;

import com.kirichko.amocrm_kirichko_test_task.view.PixbayHitsFragment;

/**
 * Created by Киричко on 21.08.2016.
 * Конкретная фабрика для создания конкретного потомка SwipeableFragment
 */
public class PixbayHitsFragmentFactory implements AbstractSwipeableFragmentFactory {
    @Override
    public SwipeableFragment createFragment(int fragmentNumber) {
        return PixbayHitsFragment.newInstance(fragmentNumber);
    }
}
