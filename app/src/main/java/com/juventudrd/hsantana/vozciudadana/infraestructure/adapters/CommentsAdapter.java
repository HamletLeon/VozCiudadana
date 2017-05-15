package com.juventudrd.hsantana.vozciudadana.infraestructure.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.juventudrd.hsantana.vozciudadana.R;
import com.juventudrd.hsantana.vozciudadana.infraestructure.entities.Comments;
import com.juventudrd.hsantana.vozciudadana.infraestructure.entities.News;
import com.juventudrd.hsantana.vozciudadana.infraestructure.viewHolders.NewsViewHolder;

import java.util.Collections;
import java.util.List;

/**
 * Created by hsantana on 4/22/2017.
 */

public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private News NewsSelected;
    private List<Comments> CommentsList;
    private Context mContext;

    public CommentsAdapter(Context context, News newsSelected, List<Comments> commentsOfNews){
        this.mContext = context;
        this.NewsSelected = newsSelected;
        this.CommentsList = commentsOfNews;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0: // News
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_listitem, parent, false);
                return new NewsViewHolder(v);
            case 1: // Write a comment
                return null;
            default: // Comments
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return CommentsList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void insertComment(int position, Comments comment){
        CommentsList.add(position, comment);
        notifyItemInserted(position);
    }

    public void removeComment(Comments comment){
        int position = CommentsList.indexOf(comment);
        CommentsList.remove(position);
        notifyItemRemoved(position);
    }
}
