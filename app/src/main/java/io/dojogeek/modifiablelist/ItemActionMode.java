package io.dojogeek.modifiablelist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by norveo on 3/4/17.
 */

public class ItemActionMode implements ActionMode.Callback {

    private Context mContext;
    private static ActionMode mActionMode;
    private ActionableListener mActionableListener;

    private ItemActionMode(Context context) {
        mContext = context;
        mActionableListener = (ActionableListener) context;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {

        MenuInflater menuInflater = mode.getMenuInflater();

        menuInflater.inflate(R.menu.menu_multi_select, menu);

        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_delete:
                mActionableListener.remove();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mode = null;
        mActionMode = mode;
        mActionableListener.exit();
    }

    public static ActionMode prepareIfItIsNotFor(Context context) {

        if (mActionMode == null) {

            ItemActionMode itemActionMode = new ItemActionMode(context);

            mActionMode = ((AppCompatActivity) context).startActionMode(itemActionMode);

        }

        return mActionMode;
    }

}
