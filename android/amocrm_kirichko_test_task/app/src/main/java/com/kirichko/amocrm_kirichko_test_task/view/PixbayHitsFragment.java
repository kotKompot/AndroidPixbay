package com.kirichko.amocrm_kirichko_test_task.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kirichko.amocrm_kirichko_test_task.R;
import com.kirichko.amocrm_kirichko_test_task.app.TestTaskApp;
import com.kirichko.amocrm_kirichko_test_task.common.SwipeableFragment;
import com.kirichko.amocrm_kirichko_test_task.di.components.DaggerIPixbayHitComponent;
import com.kirichko.amocrm_kirichko_test_task.di.components.IAppComponent;
import com.kirichko.amocrm_kirichko_test_task.di.components.IPixbayHitComponent;
import com.kirichko.amocrm_kirichko_test_task.di.modules.PixbayHitsModule;
import com.kirichko.amocrm_kirichko_test_task.network.model.PixbayHit;
import com.kirichko.amocrm_kirichko_test_task.presenter.IPixbayHitsPresenter;
import com.kirichko.amocrm_kirichko_test_task.view.adapters.PixBayHitsAdapter;
import com.kirichko.amocrm_kirichko_test_task.view.listeners.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Киричко on 21.08.2016.
 *
 * Фрагменты служащие для отображения порции элементов
 */
public class PixbayHitsFragment extends SwipeableFragment implements IPixbayHitsView {
    private PixBayHitsAdapter mPixBayHitsAdapter;
    private IPixbayHitComponent mPixbayHitComponent;
    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mErrorTextView;
    private RecyclerView mRVPixbayHit;

    private ArrayList<PixbayHit> mPixbayHits = new ArrayList<>();
    private boolean mIsLoadingProcess = false;

    @Inject
    public IPixbayHitsPresenter mPixbayHitsPresenter;

    /**
     * Инициализируем фрагмент
     * @param number уникальный номер фрагмента в списке, а также метка для получения
     * только конкретных элементов для отображения
     * @return
     */
    public static SwipeableFragment newInstance(int number) {
        SwipeableFragment swipeableFragment = new PixbayHitsFragment();
        swipeableFragment.setFragmentNumber(number);
        return swipeableFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(TestTaskApp.get(getActivity()).getAppComponent());
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pixbay_hit, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView tvFragmentNumber = (TextView) view.findViewById(R.id.fragmentNumber);
        tvFragmentNumber.setText(String.valueOf(fragmentNumber));

        mErrorTextView = (TextView) view.findViewById(R.id.errorText);
        mErrorTextView.setVisibility(View.GONE);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPixbayHits.clear();
                mPixBayHitsAdapter.notifyDataSetChanged();
                mPixbayHitsPresenter.requestPixbayHits(PIXBAY_HITS_PER_FRAGMENT * fragmentNumber, COUNT_OF_NEXT_REQUESTED_ELEMENTS);
            }
        });

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        if(mIsLoadingProcess)
        {
            mProgressBar.setVisibility(View.VISIBLE);
        } else
        {
            mProgressBar.setVisibility(View.GONE);
        }

        mRVPixbayHit = (RecyclerView) view.findViewById(R.id.pixbayHitRecyclerView);
        mRVPixbayHit.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRVPixbayHit.setLayoutManager(linearLayoutManager);
        if(mPixbayHits.size()==0)
        {
            mPixbayHitsPresenter.requestPixbayHits(PIXBAY_HITS_PER_FRAGMENT * fragmentNumber, COUNT_OF_NEXT_REQUESTED_ELEMENTS);
        }
        mPixBayHitsAdapter = new PixBayHitsAdapter(getActivity(), mPixbayHits);
        mRVPixbayHit.setAdapter(mPixBayHitsAdapter);
        mRVPixbayHit.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            /**
             * Метод решает нужно ли еще подгружать элементы или число текущих уже максимально
             * @param totalItemsCount число элементов которые уже отображаются в списке
             */
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (mPixbayHits.size() >= PIXBAY_HITS_PER_FRAGMENT) {
                    return;
                }
                int startPosition = PIXBAY_HITS_PER_FRAGMENT * fragmentNumber + totalItemsCount;
                mPixbayHitsPresenter.requestPixbayHits(startPosition, COUNT_OF_NEXT_REQUESTED_ELEMENTS);
            }
        });
    }

    @Override
    public void showProgress() {
        mIsLoadingProcess = true;
        if(mProgressBar !=null)
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mIsLoadingProcess = false;
        if(mProgressBar !=null)
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showData(ArrayList<PixbayHit> pixbayHit) {
        mPixbayHits.addAll(pixbayHit);
        if(mPixBayHitsAdapter !=null) {
            Handler handler = new Handler();
            final Runnable r = new Runnable() {
                public void run() {
                    mPixBayHitsAdapter.notifyDataSetChanged();
                    if(mSwipeRefreshLayout!=null)
                    {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }
            };
            handler.post(r);
        }
        if(mErrorTextView!=null)
        {
            mErrorTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(int error) {
        if(mErrorTextView!=null)
        {
            mErrorTextView.setVisibility(View.VISIBLE);
            mErrorTextView.setText(error);
        }
        if(mSwipeRefreshLayout!=null)
        {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if(mPixBayHitsAdapter!=null)
        {
            mPixBayHitsAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Создание компонента dagger для получения ссылки на IPixbayHitsPresenter
     * @param appComponent
     */
    protected void setupComponent(IAppComponent appComponent) {
        mPixbayHitComponent = DaggerIPixbayHitComponent.builder()
                .iAppComponent(appComponent)
                .pixbayHitsModule(new PixbayHitsModule(this))
                .build();
        mPixbayHitComponent.inject(this);
    }
}
