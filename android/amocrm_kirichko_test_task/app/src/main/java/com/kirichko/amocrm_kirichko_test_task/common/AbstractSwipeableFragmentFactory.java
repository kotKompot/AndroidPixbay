package com.kirichko.amocrm_kirichko_test_task.common;

/**
 * Created by Киричко on 21.08.2016.
 *
 * Абстрактная фабрика, предназначена быть предком конкретных фабрик, которые будут
 * создвать фрагменты наследующих SwipeableFragment
 */
public interface AbstractSwipeableFragmentFactory {
    SwipeableFragment createFragment(int fragmentNumber);
}
