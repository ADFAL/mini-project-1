package com.example.myapplication;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

public class OnSwipeTouchListener implements View.OnTouchListener {
    private static final float THRESHOLD = 100;
    private Context context;
    private GestureDetector gestureDetector;

    public OnSwipeTouchListener(Context context) {
        this.context = context;
        this.gestureDetector = new GestureDetector(context, new GestureListener());
    }

    class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() >= THRESHOLD)
                swipeLeft();
            else if (e2.getX() - e1.getX() >= THRESHOLD)
                swipeRight();

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    public void swipeLeft() {

    }

    public void swipeRight() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}

