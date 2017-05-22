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
    public TextView mCommentInfo;
    public View mVoteActionsLayout;
    public TextView mInFavorAction;
    public TextView mAgaisntAction;
    public TextView mDate;
    public TextView mText;
    public View mActionsLayout;
    public TextView mResponses;
    public TextView mResponseAction;
    public TextView mReportAction;

    public CommentsViewHolder(View itemView) {
        super(itemView);
        mCardview = (CardView) itemView.findViewById(R.id.cardView);
        mCommentInfo = (TextView) itemView.findViewById(R.id.commentInfo);
        mVoteActionsLayout = itemView.findViewById(R.id.voteActions);
        mInFavorAction = (TextView) itemView.findViewById(R.id.inFavor);
        mAgaisntAction = (TextView) itemView.findViewById(R.id.agaisnt);
        mDate = (TextView) itemView.findViewById(R.id.date);
        mText = (TextView) itemView.findViewById(R.id.text);
        mActionsLayout = itemView.findViewById(R.id.actions);
        mResponses = (TextView) itemView.findViewById(R.id.responses);
        mResponseAction = (TextView) itemView.findViewById(R.id.respond);
        mReportAction = (TextView) itemView.findViewById(R.id.report);
    }
}
