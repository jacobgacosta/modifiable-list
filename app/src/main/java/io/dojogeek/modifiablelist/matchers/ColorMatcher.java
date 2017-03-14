package io.dojogeek.modifiablelist.matchers;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by norveo on 3/7/17.
 */

public final class ColorMatcher {

    @NonNull
    public static Matcher<View> withBgColor(final int expectedColor) {

        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("with bg color: ");
            }

            @Override
            public boolean matchesSafely(final RecyclerView recyclerView) {

                View selectedView = recyclerView.findChildViewUnder(recyclerView.getX(),
                        recyclerView.getY());

                Drawable background = selectedView.getBackground();

                int color = Color.TRANSPARENT;

                if (background instanceof ColorDrawable) {
                    color = ((ColorDrawable) background).getColor();
                }

                return color == expectedColor;
            }
        };
    }

}
