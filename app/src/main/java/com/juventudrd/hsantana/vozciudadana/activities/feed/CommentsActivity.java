package com.juventudrd.hsantana.vozciudadana.activities.feed;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.juventudrd.hsantana.vozciudadana.R;
import com.juventudrd.hsantana.vozciudadana.infraestructure.NavigationViewController;
import com.juventudrd.hsantana.vozciudadana.infraestructure.adapters.CommentsAdapter;
import com.juventudrd.hsantana.vozciudadana.infraestructure.entities.Comments;
import com.juventudrd.hsantana.vozciudadana.infraestructure.entities.News;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hsantana on 5/2/2017.
 */

public class CommentsActivity extends AppCompatActivity implements View.OnClickListener {
//    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private News mNewsSelected;
    private CommentsAdapter mCommentsAdapter;
    private List<Comments> mCommentsList = new ArrayList<>();
    private FloatingActionButton mFloatingButton;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.content_xmain);

        Intent intent = getIntent();

        if (intent.hasExtra("NewsSelected")) mNewsSelected = intent.getParcelableExtra("NewsSelected");
        else mNewsSelected = new News("ERROR","ERROR",0,0,0,0);

        mCommentsList = getSomeExampleData(5);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mCommentsAdapter = new CommentsAdapter(this, mNewsSelected, mCommentsList);
        mRecyclerView.setAdapter(mCommentsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<Comments> getSomeExampleData(int qty) {
        List<Comments> commentsList = new ArrayList<>();
        for (int i=0; i<qty; i++){
            commentsList.add(new Comments("Esto es solo un ejemplo No."+i, "", new int[]{i, i}, i, Calendar.getInstance().getTime()));
        }
        return commentsList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                Toast.makeText(this, getString(R.string.inConstruction), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
