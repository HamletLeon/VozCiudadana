package com.juventudrd.hsantana.vozciudadana.activities.feed;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.juventudrd.hsantana.vozciudadana.R;
import com.juventudrd.hsantana.vozciudadana.infraestructure.NavigationViewController;
import com.juventudrd.hsantana.vozciudadana.infraestructure.adapters.NewsAdapter;
import com.juventudrd.hsantana.vozciudadana.infraestructure.entities.News;
import com.juventudrd.hsantana.vozciudadana.infraestructure.utils.AppBarStateChangeListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends NavigationViewController implements View.OnClickListener {
    private Toolbar mToolbar;
    private FloatingActionButton mFloatingButton;
    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;
    private List<News> mNewsItems = new ArrayList<>();

    private int[] mExampleImages = new int[]{R.drawable.multitud, R.drawable.multitud1, R.drawable.multitud2, R.drawable.multitud3, R.drawable.multitud4};

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mStubContainer.setLayoutResource(R.layout.activity_feed);
        mStubContainer.inflate();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        initCollapsingToolbar();

        mNewsItems = getSomeExampleData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mNewsAdapter = new NewsAdapter(this, mNewsItems);
        mRecyclerView.setAdapter(mNewsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFloatingButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingButton.setOnClickListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (mToolbar.getNavigationIcon()!=null)mToolbar.getNavigationIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
    }

    private List<News> getSomeExampleData() {
        List<News> news = new ArrayList<>();
        for (int i=0; i<mExampleImages.length; i++) {
            news.add(new News("Texto de ejemplo como titulo de la noticia", getString(R.string.large_text), 5+i, 10+i, 20+i, mExampleImages[i]));
        }
        news.add(new News("Voz Ciudadana mejorando la participacion ciudadana", getString(R.string.large_text), 100, 2, 50, R.drawable.logo_aplicacion));
        return news;
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        if (collapsingToolbar!=null) {
            collapsingToolbar.setTitle(" ");
        }
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        if (appBarLayout!=null)appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        if (appBarLayout!=null)appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, int state) {
                switch (state){
                    case AppBarStateChangeListener.COLLAPSED:
                        if (collapsingToolbar!=null && mToolbar.getNavigationIcon() != null) {
                            mToolbar.animate().alpha(0.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    mToolbar.animate().alpha(1.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationStart(Animator animation) {
                                            super.onAnimationStart(animation);
                                            mToolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                                            mToolbar.getMenu().setGroupVisible(0, true);
                                        }

                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);
                                            collapsingToolbar.setTitle(getString(R.string.app_name));
                                        }
                                    });
                                }
                            });
                        }
                        break;
                    case AppBarStateChangeListener.EXPANDED:
                        if (collapsingToolbar!=null) {
                            collapsingToolbar.setTitle(" ");
                            if (mToolbar.getNavigationIcon()!=null){
                                mToolbar.getNavigationIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                                mToolbar.getMenu().setGroupVisible(0, false);
                            }
                        }
                        break;
                    case AppBarStateChangeListener.UNDER_MIDDLE:
                        if (collapsingToolbar!=null) collapsingToolbar.setTitle(" ");
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.setGroupVisible(0, false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            default:
                Toast.makeText(this, getString(R.string.inConstruction), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
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
