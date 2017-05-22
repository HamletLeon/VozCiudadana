package com.juventudrd.hsantana.vozciudadana.infraestructure.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.juventudrd.hsantana.vozciudadana.R;
import com.juventudrd.hsantana.vozciudadana.infraestructure.entities.Comments;
import com.juventudrd.hsantana.vozciudadana.infraestructure.entities.News;
import com.juventudrd.hsantana.vozciudadana.infraestructure.viewHolders.CommentsViewHolder;
import com.juventudrd.hsantana.vozciudadana.infraestructure.viewHolders.NewsViewHolder;
import com.juventudrd.hsantana.vozciudadana.infraestructure.viewHolders.WriteCommentViewHolder;

import java.util.Calendar;
import java.util.List;

/**
 * Created by hsantana on 4/22/2017.
 */

public class CommentsAdapter extends RecyclerView.Adapter<ViewHolder> {
    private News NewsSelected;
    private List<Comments> CommentsList;
    private Context mContext;

    public CommentsAdapter(Context context, News newsSelected, List<Comments> commentsOfNews){
        this.mContext = context;
        this.NewsSelected = newsSelected;
        this.CommentsList = commentsOfNews;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0: // News
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_listitem, parent, false);
                return new NewsViewHolder(view1);
            case 1: // Write a comment
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.writecomment_listitem, parent, false);
                view2.findViewById(R.id.commentText).clearFocus();
                return new WriteCommentViewHolder(view2);
            default: // Comments
                View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_listitem, parent, false);
                return new CommentsViewHolder(view3);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (position){
            case 0:
                setNewsViewData((NewsViewHolder) holder);
                break;
            case 1:
                setWriteCommentViewListener((WriteCommentViewHolder) holder);
                break;
            default:
                setCommentsViewData((CommentsViewHolder) holder, position);
                break;
        }
    }

    private void setNewsViewData(NewsViewHolder holder) {
        holder.mTitle.setText(NewsSelected.getTitle());
        holder.mDescription.setMaxLines(Integer.MAX_VALUE);
        holder.mDescription.setText(NewsSelected.getDescription());

        Resources res = mContext.getResources();
        if (!NewsSelected.getVoteForIt()) holder.mVoteQTY.setText(String.format(res.getString(R.string.votesQTY), NewsSelected.getVotesQTY()));
        else holder.mVoteQTY.setText(String.format(res.getString(R.string.votesYouAndQTY), NewsSelected.getVotesQTY()));
        if (!NewsSelected.getAgainstIt()) holder.mAgainstQTY.setText(String.format(res.getString(R.string.againstQTY), NewsSelected.getAgainstQTY()));
        else holder.mAgainstQTY.setText(String.format(res.getString(R.string.againstYouAndQTY), NewsSelected.getAgainstQTY()));
        holder.mCommentQTY.setText(String.format(res.getString(R.string.commentsQTY), NewsSelected.getCommentsQTY()));

        if (NewsSelected.mExampleImage!=0) holder.mImage.setImageResource(NewsSelected.mExampleImage);

        holder.mCommentQTY.setOnClickListener(onNewsActionClicked(holder, NewsSelected, res));
        holder.mVoteAction.setOnClickListener(onNewsActionClicked(holder, NewsSelected, res));
        holder.mAgainstAction.setOnClickListener(onNewsActionClicked(holder, NewsSelected, res));
        holder.mCommentAction.setOnClickListener(onNewsActionClicked(holder, NewsSelected, res));
    }
    private View.OnClickListener onNewsActionClicked(final NewsViewHolder holder, final News item, final Resources res) {
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

    private void setWriteCommentViewListener(final WriteCommentViewHolder holder) {
        holder.mSendAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = holder.mWriteComment.getText().toString().trim();
                if (!data.isEmpty()) {
                    insertComment(0, new Comments(data, "", new int[]{0, 0}, 0, Calendar.getInstance().getTime()));
                    holder.mWriteComment.setText("");
                    holder.mWriteComment.clearFocus();
                } else holder.mWriteComment.setError("Favor ingrese algún comentario!");
            }
        });
    }

    private void setCommentsViewData(CommentsViewHolder holder, int position) {
        Comments comment = CommentsList.get(position-2);
        String info = "";
        int inFavor = comment.getInFavor();
        boolean userInFavor = comment.getInFavorAboutUser();
        int against = comment.getAgainst();
        boolean userAgainst = comment.getAgainstAboutUser();
        if (inFavor>0 && against==0){
            if (userInFavor) info = String.format(mContext.getString(R.string.votesYouAndQTY), inFavor);
            else info = String.format(mContext.getString(R.string.votesQTY), inFavor);
        } else if (against>0 && inFavor==0){
            if (userAgainst) info = String.format(mContext.getString(R.string.againstYouAndQTY), against);
            else info = String.format(mContext.getString(R.string.againstQTY), against);
        } else if (against>0 && inFavor>0){
            if (userInFavor) info = String.format(mContext.getString(R.string.youInFavor$AgainstQTY), inFavor, against);
            else if (userAgainst) info = String.format(mContext.getString(R.string.favor$YouAgainstQTY), inFavor, against);
            else info = String.format(mContext.getString(R.string.favor$AgainstQTY), inFavor, against);
        }
        holder.mCommentInfo.setText(info);
        String date = String.format(mContext.getString(R.string.publishDate), comment.getCreationDate());
        holder.mDate.setText(date);
        holder.mText.setText(comment.getText());
        if (comment.getResponsesQTY()!=0)
        holder.mResponses.setText(mContext.getResources().getQuantityString(R.plurals.responseQTY, comment.getResponsesQTY(), comment.getResponsesQTY()));
        else holder.mResponses.setText(mContext.getString(R.string.noneResponse));
    }

    @Override
    public int getItemCount() {
        return CommentsList.size()+2;
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
