package com.juventudrd.hsantana.vozciudadana.infraestructure.viewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.juventudrd.hsantana.vozciudadana.R;

/**
 * Created by hsantana on 4/22/2017.
 */

public class CommentsViewHolder extends RecyclerView.ViewHolder {
    public CardView mCardview;
    public ImageView mImage; // NetWorkImageView cuando implemente Volley
    public TextView mTitle;
    public TextView mDescription;
    public TextView mVoteQTY;
    public TextView mAgainstQTY;
    public TextView mCommentQTY;
    public View mVoteAction;
    public TextView mVoteActionText;
    public View mAgainstAction;
    public TextView mAgainstActionText;
    public View mCommentAction;
    public TextView mCommentActionText;

    public CommentsViewHolder(View itemView) {
        super(itemView);
        mCardview = (CardView) itemView.findViewById(R.id.cardView);
        mImage = (ImageView) itemView.findViewById(R.id.image);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mDescription = (TextView) itemView.findViewById(R.id.description);
        mVoteQTY = (TextView) itemView.findViewById(R.id.vote_QTY);
        mAgainstQTY = (TextView) itemView.findViewById(R.id.against_QTY);
        mCommentQTY = (TextView) itemView.findViewById(R.id.comments_QTY);

        mVoteAction = itemView.findViewById(R.id.vote_action);
        mVoteActionText = (TextView) itemView.findViewById(R.id.vote_action_text);
        mAgainstAction = itemView.findViewById(R.id.against_action);
        mAgainstActionText = (TextView) itemView.findViewById(R.id.against_action_text);
        mCommentAction = itemView.findViewById(R.id.comment_action);
        mCommentActionText = (TextView) itemView.findViewById(R.id.comment_action_text);
    }
}
