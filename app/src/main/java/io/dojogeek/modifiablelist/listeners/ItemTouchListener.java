package io.dojogeek.modifiablelist.listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import io.dojogeek.modifiablelist.SelecteableListener;

/**
 * Created by norveo on 2/24/17.
 */

public class ItemTouchListener implements RecyclerView.OnItemTouchListener {

    private Context mContext;
    private SelecteableListener mSelecteableListener;

    public ItemTouchListener(Context context) {
        mContext = context;
        mSelecteableListener = (SelecteableListener) context;
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent event) {


        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
