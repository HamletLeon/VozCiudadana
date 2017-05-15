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

import com.juventudrd.hsantana.vozciudadana.infraestructure.viewHolders.NewsViewHolder;
import com.juventudrd.hsantana.vozciudadana.R;
import com.juventudrd.hsantana.vozciudadana.infraestructure.entities.News;

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
        if (!item.getVoteForIt()) holder.mVoteQTY.setText(String.format(res.getString(R.string.votesQTY), item.getVotesQTY()));
        else holder.mVoteQTY.setText(String.format(res.getString(R.string.votesYouAndQTY), item.getVotesQTY()));
        if (!item.getAgainstIt()) holder.mAgainstQTY.setText(String.format(res.getString(R.string.againstQTY), item.getAgainstQTY()));
        else holder.mAgainstQTY.setText(String.format(res.getString(R.string.againstYouAndQTY), item.getAgainstQTY()));
        holder.mCommentQTY.setText(String.format(res.getString(R.string.commentsQTY), item.getCommentsQTY()));

        if (item.mExampleImage!=0) holder.mImage.setImageResource(item.mExampleImage);

        holder.mCommentQTY.setOnClickListener(onActionClicked(holder, item, position, res));
        holder.mVoteAction.setOnClickListener(onActionClicked(holder, item, position, res));
        holder.mAgainstAction.setOnClickListener(onActionClicked(holder, item, position, res));
        holder.mCommentAction.setOnClickListener(onActionClicked(holder, item, position, res));
    }

    private View.OnClickListener onActionClicked(final NewsViewHolder holder, final News item, final int position, final Resources res) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
//                    case R.id.comments_QTY:
//                        break;
                    case R.id.vote_action:
                        item.setVoteForIt(!item.getVoteForIt());
                        if (item.getVoteForIt()) {
                            if (item.getAgainstIt()){
                                item.setAgainstIt(!item.getAgainstIt());
                                holder.mAgainstActionText.setTextColor(ContextCompat.getColor(mContext, R.color.cardview_dark_background));
                                holder.mAgainstActionText.setPaintFlags(holder.mAgainstQTY.getPaintFlags());
                                holder.mAgainstQTY.setText(String.format(res.getString(R.string.againstQTY), item.getAgainstQTY()));
                            }
                            holder.mVoteActionText.setTextColor(Color.BLUE);
                            holder.mVoteActionText.setPaintFlags(holder.mVoteActionText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                            holder.mVoteQTY.setText(String.format(res.getString(R.string.votesYouAndQTY), item.getVotesQTY()));
                        }
                        else {
                            holder.mVoteActionText.setTextColor(ContextCompat.getColor(mContext, R.color.cardview_dark_background));
                            holder.mVoteActionText.setPaintFlags(holder.mVoteQTY.getPaintFlags());
                            holder.mVoteQTY.setText(String.format(res.getString(R.string.votesQTY), item.getVotesQTY()));
                        }
                        break;
                    case R.id.against_action:
                        item.setAgainstIt(!item.getAgainstIt());
                        if (item.getAgainstIt()) {
                            if (item.getVoteForIt()){
                                item.setVoteForIt(!item.getVoteForIt());
                                holder.mVoteActionText.setTextColor(ContextCompat.getColor(mContext, R.color.cardview_dark_background));
                                holder.mVoteActionText.setPaintFlags(holder.mVoteQTY.getPaintFlags());
                                holder.mVoteQTY.setText(String.format(res.getString(R.string.votesQTY), item.getVotesQTY()));
                            }
                            holder.mAgainstActionText.setTextColor(Color.RED);
                            holder.mAgainstActionText.setPaintFlags(holder.mAgainstActionText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                            holder.mAgainstQTY.setText(String.format(res.getString(R.string.againstYouAndQTY), item.getAgainstQTY()));
                        }
                        else {
                            holder.mAgainstActionText.setTextColor(ContextCompat.getColor(mContext, R.color.cardview_dark_background));
                            holder.mAgainstActionText.setPaintFlags(holder.mAgainstQTY.getPaintFlags());
                            holder.mAgainstQTY.setText(String.format(res.getString(R.string.againstQTY), item.getAgainstQTY()));
                        }
                        break;
//                    case R.id.comment_action:
//                        break;
                    default:
                        Toast.makeText(mContext, mContext.getString(R.string.inConstruction), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
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
