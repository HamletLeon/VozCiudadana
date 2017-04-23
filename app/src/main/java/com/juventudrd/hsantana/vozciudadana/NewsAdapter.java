package com.juventudrd.hsantana.vozciudadana;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by hsantana on 4/22/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    private List<News> mNewsItems = Collections.emptyList();
    private Context mContext;

    public NewsAdapter(Context context, List<News> newsItems){
        this.mContext = context;
        this.mNewsItems = newsItems;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_listitem, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News item = mNewsItems.get(position);
        holder.mTitle.setText(item.getTitle());
        holder.mDescription.setText(item.getDescription());

        Resources res = mContext.getResources();
        holder.mVoteQTY.setText(String.format(res.getString(R.string.votesQTY), item.getVotes()));
        holder.mAgainstQTY.setText(String.format(res.getString(R.string.againstQTY), item.getAgainst()));
        holder.mCommentQTY.setText(String.format(res.getString(R.string.commentsQTY), item.getComments()));

        if (item.mExampleImage!=0) holder.mImage.setImageResource(item.mExampleImage);
    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void insert(int position, News news){
        mNewsItems.add(position, news);
        notifyItemInserted(position);
    }

    public void remove(News news){
        int position = mNewsItems.indexOf(news);
        mNewsItems.remove(position);
        notifyItemRemoved(position);
    }
}
