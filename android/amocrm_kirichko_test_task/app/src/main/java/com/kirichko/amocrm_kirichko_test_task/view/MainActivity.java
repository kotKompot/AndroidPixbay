package com.kirichko.amocrm_kirichko_test_task.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.kirichko.amocrm_kirichko_test_task.R;
import com.kirichko.amocrm_kirichko_test_task.app.TestTaskApp;
import com.kirichko.amocrm_kirichko_test_task.common.SwipeableFragment;
import com.kirichko.amocrm_kirichko_test_task.di.components.DaggerIHostViewComponent;
import com.kirichko.amocrm_kirichko_test_task.di.components.IAppComponent;
import com.kirichko.amocrm_kirichko_test_task.di.components.IHostViewComponent;
import com.kirichko.amocrm_kirichko_test_task.di.modules.HostViewModule;
import com.kirichko.amocrm_kirichko_test_task.presenter.IHostViewPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Главная активность служащая в качестве HostView.
 * Получает число доступных элементов от hostViewPresenter и задает число фрагментов в
 * ViewPager
 */
public class MainActivity extends FragmentActivity implements IHostView {

    private static final String LOG_TAG = MainActivity.class.getName();
    private static int maxSwipeableFragmentsCount = 1;

    private static List<SwipeableFragment> fragments = new ArrayList<>();

    private SwipeableFragmentsPagerAdapter mSwipeableFragmentsPagerAdapter;
    private ViewPager mViewPager;
    private ProgressBar progressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private IHostViewComponent hostViewComponent;


    @Inject
    public IHostViewPresenter hostViewPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupComponent(TestTaskApp.get(this).getAppComponent());

        mSwipeableFragmentsPagerAdapter =
                new SwipeableFragmentsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSwipeableFragmentsPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setVisibility(View.GONE);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        hostViewPresenter.requestTotalPixbayHits();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                hostViewPresenter.requestTotalPixbayHits();
            }
        });
    }

    @Override
    public void showProgress() {
        if(progressBar!=null)
            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        if(progressBar!=null)
            progressBar.setVisibility(View.GONE);
    }


    /**
     * Получаем общее число доступных элементов, если их 0, то это следствие какой-то ошибки, поэтому
     * включаем swipetorefresh для повторных попыток запроса, иначе выключаем его, чтобы он не
     * мешался, так как у Fragments есть свой такой
     * @param totalPixbayHits
     */
    @Override
    public void onTotalPixbayHitsReceived(int totalPixbayHits) {
       maxSwipeableFragmentsCount = totalPixbayHits/SwipeableFragment.PIXBAY_HITS_PER_FRAGMENT + 1;
        mSwipeableFragmentsPagerAdapter.notifyDataSetChanged();
        mViewPager.setVisibility(View.VISIBLE);
        if(mSwipeRefreshLayout!=null)
        {
            mSwipeRefreshLayout.setRefreshing(false);
            if(totalPixbayHits>0)
            {
                mSwipeRefreshLayout.setEnabled(false);
            } else
            {
                mSwipeRefreshLayout.setEnabled(true);
            }
        }
    }

    @Override
    public void showError(int error) {
        if(mSwipeRefreshLayout!=null)
        {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * Создание компонента dagger для получения ссылки на IHostViewPresenter
     * @param appComponent
     */
    protected void setupComponent(IAppComponent appComponent) {
        hostViewComponent = DaggerIHostViewComponent.builder()
                .iAppComponent(appComponent)
                .hostViewModule(new HostViewModule(this))
                .build();
        hostViewComponent.inject(this);
    }

    /**
     * Адаптер для добавления фрагментов в PageView
     */
    public class SwipeableFragmentsPagerAdapter extends FragmentStatePagerAdapter {
        public SwipeableFragmentsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Возвращает фрагменты из списка или создает, добавляет в список и все равно возвращает.
         * Фрагменты создаются фабрикой и они могут быть любыми, если наследуют SwipeableFragment
         */
        @Override
        public Fragment getItem(int i) {
            if(i>fragments.size()-1) {
                fragments.add(i, TestTaskApp.swipeableFragmentFactory.createFragment(i));
            }
            if(i>fragments.size()-1)
            {
                Log.e(LOG_TAG,"Невозможно отобразить фрагмент с номером:" +i);
            }
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return maxSwipeableFragmentsCount;
        }
    }
}
