package com.juventudrd.hsantana.vozciudadana;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hsantana on 4/22/2017.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {
    CardView mCardview;
    ImageView mImage; // NetWorkImageView cuando implemente Volley
    TextView mTitle;
    TextView mDescription;
    TextView mVoteQTY;
    TextView mAgainstQTY;
    TextView mCommentQTY;
    View mVoteAction;
    View mAgainstAction;
    View mCommentAction;

    public NewsViewHolder(View itemView) {
        super(itemView);
        mCardview = (CardView) itemView.findViewById(R.id.cardView);
        mImage = (ImageView) itemView.findViewById(R.id.image);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mDescription = (TextView) itemView.findViewById(R.id.description);
        mVoteQTY = (TextView) itemView.findViewById(R.id.vote_QTY);
        mAgainstQTY = (TextView) itemView.findViewById(R.id.against_QTY);
        mCommentQTY = (TextView) itemView.findViewById(R.id.comments_QTY);

        mVoteAction = itemView.findViewById(R.id.vote_action);
        mAgainstAction = itemView.findViewById(R.id.against_action);
        mCommentAction = itemView.findViewById(R.id.comment_action);
    }
}
