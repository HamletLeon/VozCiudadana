package com.juventudrd.hsantana.vozciudadana.infraestructure.utils;

import android.support.design.widget.AppBarLayout;

/**
 * Created by hsantana on 5/1/2017.
 */

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {
    public static final int IDLE = 0;
    public static final int EXPANDED = 1;
    public static final int COLLAPSED = 2;
    public static final int UNDER_MIDDLE = 4;

    private int mCurrentState = IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (mCurrentState != EXPANDED) {
                onStateChanged(appBarLayout, EXPANDED);
            }
            mCurrentState = EXPANDED;
        }else if (Math.abs(i) <= ((appBarLayout.getTotalScrollRange())/2)){
            if (mCurrentState != UNDER_MIDDLE) {
                onStateChanged(appBarLayout, UNDER_MIDDLE);
            }
            mCurrentState = UNDER_MIDDLE;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != COLLAPSED) {
                onStateChanged(appBarLayout, COLLAPSED);
            }
            mCurrentState = COLLAPSED;
        } else {
            if (mCurrentState != IDLE) {
                onStateChanged(appBarLayout, IDLE);
            }
            mCurrentState = IDLE;
        }
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, int state);
}

/*
new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (verticalOffset == 0) {
                    if (collapsingToolbar!=null) {
                        if (mToolbar.getNavigationIcon()!=null){
                            collapsingToolbar.setTitle(getString(R.string.app_name));
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
                                    });
                                }
                            });
                        }
                    }
                    isShow = true;
                } else if (isShow) {
                    if (collapsingToolbar!=null) {
                        collapsingToolbar.setTitle(" ");
                        if (mToolbar.getNavigationIcon()!=null){
                            mToolbar.getNavigationIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                            mToolbar.getMenu().setGroupVisible(0, false);
                        }
                    }
                    isShow = false;
                }
            }
        }
 */
