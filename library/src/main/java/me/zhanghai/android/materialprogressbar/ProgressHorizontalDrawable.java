/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.materialprogressbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

import me.zhanghai.android.materialprogressbar.internal.ThemeUtils;

public class ProgressHorizontalDrawable extends LayerDrawable {

    private int mSecondaryAlpha;
    private SingleProgressHorizontalDrawable mTrackDrawable;
    private SingleProgressHorizontalDrawable mSecondaryProgressDrawable;
    private SingleProgressHorizontalDrawable mProgressDrawable;

    public ProgressHorizontalDrawable(Context context) {
        super(new Drawable[]{
                new SingleProgressHorizontalDrawable(context),
                new SingleProgressHorizontalDrawable(context),
                new SingleProgressHorizontalDrawable(context)
        });

        setId(0, android.R.id.background);
        mTrackDrawable = (SingleProgressHorizontalDrawable) getDrawable(0);

        setId(1, android.R.id.secondaryProgress);
        mSecondaryProgressDrawable = (SingleProgressHorizontalDrawable) getDrawable(1);
        float disabledAlpha = ThemeUtils.getThemeAttrFloat(context, android.R.attr.disabledAlpha);
        mSecondaryAlpha = Math.round(disabledAlpha * 0xFF);
        mSecondaryProgressDrawable.setAlpha(mSecondaryAlpha);
        mSecondaryProgressDrawable.setShowTrack(false);

        setId(2, android.R.id.progress);
        mProgressDrawable = (SingleProgressHorizontalDrawable) getDrawable(2);
        mProgressDrawable.setShowTrack(false);
    }

    public boolean getShowTrack() {
        return mTrackDrawable.getShowTrack();
    }

    public void setShowTrack(boolean showTrack) {
        if (mTrackDrawable.getShowTrack() != showTrack) {
            mTrackDrawable.setShowTrack(showTrack);
            // HACK: Make alpha act as if composited.
            mSecondaryProgressDrawable.setAlpha(showTrack ? mSecondaryAlpha : 2 * mSecondaryAlpha);
        }
    }

    public boolean getUseIntrinsicPadding() {
        return mTrackDrawable.getUseIntrinsicPadding();
    }

    public void setUseIntrinsicPadding(boolean useIntrinsicPadding) {
        mTrackDrawable.setUseIntrinsicPadding(useIntrinsicPadding);
        mSecondaryProgressDrawable.setUseIntrinsicPadding(useIntrinsicPadding);
        mProgressDrawable.setUseIntrinsicPadding(useIntrinsicPadding);
    }

    /**
     * {@inheritDoc}
     */
    // Rewrite for compatibility.
    @Override
    public void setTint(int tint) {
        mTrackDrawable.setTint(tint);
        mSecondaryProgressDrawable.setTint(tint);
        mProgressDrawable.setTint(tint);
    }

    /**
     * {@inheritDoc}
     */
    // Rewrite for compatibility.
    @Override
    public void setTintList(ColorStateList tint) {
        mTrackDrawable.setTintList(tint);
        mSecondaryProgressDrawable.setTintList(tint);
        mProgressDrawable.setTintList(tint);
    }

    /**
     * {@inheritDoc}
     */
    // Rewrite for compatibility.
    @Override
    public void setTintMode(PorterDuff.Mode tintMode) {
        mTrackDrawable.setTintMode(tintMode);
        mSecondaryProgressDrawable.setTintMode(tintMode);
        mProgressDrawable.setTintMode(tintMode);
    }
}
