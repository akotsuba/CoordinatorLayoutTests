package com.example.coordinator.coordinatorlayouttest;


import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class PhotoBehavior extends CoordinatorLayout.Behavior<ImageView> {

    private static final Float UNDEFINED = -999.998f;

    private Float max = UNDEFINED;
    // hardcoded for tests
    Float min = 210.0f;

    public PhotoBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        Log.d("PhotoBehavior", "dependency.getY() = " + dependency.getY());

        if (max.equals(UNDEFINED)) {
            max = dependency.getY();
        }

        Float percent = (1.0f - (dependency.getY() - min) / (max - min));

        Float scalepercentage = 1.0f + percent  * 4.0f;

        child.setScaleX(scalepercentage);
        child.setScaleY(scalepercentage);

        int color = Color.argb((int)(Math.min(percent, 0.85f) * 255.0f), 44, 78, 85);
        child.setColorFilter(color, PorterDuff.Mode.SRC_OVER);

        return true;
    }
}
