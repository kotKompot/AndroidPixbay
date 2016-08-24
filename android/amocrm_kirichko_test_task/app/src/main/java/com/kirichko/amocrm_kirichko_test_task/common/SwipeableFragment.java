package com.kirichko.amocrm_kirichko_test_task.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;


/**
 * Created by Киричко on 21.08.2016.
 * Фрагмент предназначен быть предком для других фрагментов,
 * которые хотят взаимодействовать с MainActivity
 */
public class SwipeableFragment extends Fragment {
    public static int PIXBAY_HITS_PER_FRAGMENT = 40;
    protected static int COUNT_OF_NEXT_REQUESTED_ELEMENTS = 10;
    protected int fragmentNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setFragmentNumber(int fragmentNumber) {
        this.fragmentNumber = fragmentNumber;
    }
}
