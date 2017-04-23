package com.juventudrd.hsantana.vozciudadana;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton mFloatingButton;
    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;
    private List<News> mNewsItems = new ArrayList<>();

    private int[] mExampleImages = new int[]{R.drawable.multitud, R.drawable.multitud1, R.drawable.multitud2, R.drawable.multitud3, R.drawable.multitud4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        mNewsItems = getSomeExampleData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mNewsAdapter = new NewsAdapter(this, mNewsItems);
        mRecyclerView.setAdapter(mNewsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFloatingButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingButton.setOnClickListener(this);
    }

    private List<News> getSomeExampleData() {
        List<News> news = new ArrayList<>();
        for (int i=0; i<6; i++) {
            if (i<mExampleImages.length)news.add(new News("Texto de ejemplo como titulo de la noticia", getString(R.string.large_text), 5+i, 10+i, 20+i, mExampleImages[i]));
        }
        return news;
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        if (collapsingToolbar!=null) collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        if (appBarLayout!=null)appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        if (appBarLayout!=null)appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    if (collapsingToolbar!=null) collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    if (collapsingToolbar!=null) collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
                Toast.makeText(this, "En construcción!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                Toast.makeText(this, "En construcción!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
