package io.dojogeek.modifiablelist;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import android.support.test.espresso.contrib.RecyclerViewActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static io.dojogeek.modifiablelist.matchers.ColorMatcher.withBgColor;
import static org.hamcrest.Matchers.not;
import static junit.framework.Assert.assertEquals;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

/**
 * Created by norveo on 2/23/17.
 */

@RunWith(AndroidJUnit4.class)
public class ItemListTest {

    @Rule
    public ActivityTestRule<ItemsListActivity> mActivityRule =
            new ActivityTestRule<>(ItemsListActivity.class);

    @Test
    public void simpleClickItem_showToast() {

        onView(withId(R.id.itemList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withText("selected Item one")).check(matches(isDisplayed()));

    }

    @Test
    public void actionMode_removeSelectedItems() {

        onView(withId(R.id.itemList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));

        onView(withId(R.id.itemList)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.action_delete)).check(matches(isDisplayed()));

        onView(withId(R.id.action_delete)).perform(click());

        RecyclerView recyclerView = (RecyclerView) mActivityRule.getActivity().findViewById(R.id.itemList);

        int itemsBeforeRemoval = recyclerView.getAdapter().getItemCount();

        onView(withText("¿Eliminar 2 items seleccionados?")).check(matches(isDisplayed()));

        onView(withText("Aceptar")).perform(click());

        onView(withId(R.id.action_delete)).check(matches(not(isDisplayed())));

        int itemsAfterRemoval = recyclerView.getAdapter().getItemCount();

        assertEquals(itemsAfterRemoval, (itemsBeforeRemoval - 2));

    }

    @Test
    public void actionMode_noRemoveDeselectedItem() {

        onView(withId(R.id.itemList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));

        onView(withId(R.id.itemList)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.itemList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.action_delete)).check(matches(isDisplayed()));

        onView(withId(R.id.action_delete)).perform(click());

        RecyclerView recyclerView = (RecyclerView) mActivityRule.getActivity().findViewById(R.id.itemList);

        int itemsBeforeRemoval = recyclerView.getAdapter().getItemCount();

        onView(withText("¿Eliminar item seleccionado?")).check(matches(isDisplayed()));

        onView(withText("Aceptar")).perform(click());

        onView(withId(R.id.action_delete)).check(matches(not(isDisplayed())));

        int itemsAfterRemoval = recyclerView.getAdapter().getItemCount();

        assertEquals(itemsAfterRemoval, (itemsBeforeRemoval - 1));

    }

    @Test
    public void longPress_enableActionMode() {

        onView(withId(R.id.itemList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));

        onView(withId(R.id.action_delete)).check(matches(isDisplayed()));

        onView(withId(R.id.action_mode_close_button)).perform(click());
    }

    @Test
    public void normalMode_whenDeselectingAllItems() {

        onView(withId(R.id.itemList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));

        onView(withId(R.id.itemList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.action_delete)).check(matches(not(isDisplayed())));

    }

    @Test
    public void selectedMode_shadeAndLightItem() {

        onView(withId(R.id.itemList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick())).
                check(matches(withBgColor(Color.LTGRAY)));

        onView(withId(R.id.itemList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click())).
                check(matches(withBgColor(Color.TRANSPARENT)));

    }

}
