package com.kirichko.amocrm_kirichko_test_task.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kirichko.amocrm_kirichko_test_task.R;
import com.kirichko.amocrm_kirichko_test_task.network.model.PixbayHit;

import java.util.ArrayList;


/**
 * Created by Киричко on 21.08.2016.
 *
 * Адаптер для размещения элементов в списке из карточек
 */
public class PixBayHitsAdapter extends RecyclerView.Adapter<PixBayHitsAdapter.ViewHolder> {
        private ArrayList<PixbayHit> mPixabayHit;
        private Context mContext;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mPostOwnerName;
            public TextView mInfo;
            public ImageView mPreView;
            public ViewHolder(View v) {
                super(v);
                mPostOwnerName = (TextView)v.findViewById(R.id.post_owner_name);
                mPreView = (ImageView) v.findViewById(R.id.pre_view);
                mInfo = (TextView)v.findViewById(R.id.info);
            }
        }

        public PixBayHitsAdapter(Context context, ArrayList<PixbayHit> pixabayHit) {
            mPixabayHit = pixabayHit;
            mContext = context;
        }

        @Override
        public PixBayHitsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_pixbay_hit, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mPostOwnerName.setText(mPixabayHit.get(position).getUser());
            holder.mInfo.setText(mPixabayHit.get(position).getId());
            Glide.clear(holder.mPreView);
            Glide.with(mContext)
                    .load(mPixabayHit.get(position).getPreviewURL())
                    .into(holder.mPreView);
        }

        @Override
        public int getItemCount() {
            return mPixabayHit.size();
        }
}

