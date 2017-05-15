package com.juventudrd.hsantana.vozciudadana.activities.feed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.juventudrd.hsantana.vozciudadana.R;
import com.juventudrd.hsantana.vozciudadana.infraestructure.NavigationViewController;

/**
 * Created by hsantana on 5/2/2017.
 */

public class CommentsActivity extends NavigationViewController {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mStubContainer.setLayoutResource(R.layout.activity_feed);
        View logo = findViewById(R.id.logo);
        logo.setVisibility(View.GONE);
        mStubContainer.inflate();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }
}
