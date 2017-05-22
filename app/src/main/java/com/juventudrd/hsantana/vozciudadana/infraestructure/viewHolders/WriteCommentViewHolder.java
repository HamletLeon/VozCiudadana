package com.juventudrd.hsantana.vozciudadana.infraestructure.viewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.juventudrd.hsantana.vozciudadana.R;

/**
 * Created by hsantana on 4/22/2017.
 */

public class WriteCommentViewHolder extends RecyclerView.ViewHolder {
    public CardView mCardview;
    public EditText mWriteComment;
    public Button mSendAction;

    public WriteCommentViewHolder(View itemView) {
        super(itemView);
        mCardview = (CardView) itemView.findViewById(R.id.cardView);
        mWriteComment = (EditText) itemView.findViewById(R.id.commentText);
        mSendAction = (Button) itemView.findViewById(R.id.send);
    }
}
