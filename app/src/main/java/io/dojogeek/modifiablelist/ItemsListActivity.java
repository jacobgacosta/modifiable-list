package io.dojogeek.modifiablelist;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ActionMode;

import java.util.List;
import java.util.ArrayList;

import io.dojogeek.modifiablelist.adapters.SelectedItemAdapter;

public class ItemsListActivity extends AppCompatActivity implements ActionableListener, SelecteableListener,
        AlertDialogListener{

    private ActionMode mActionMode;
    private RecyclerView mItemList;
    private SelectedItemAdapter mSelectedItemAdapter;
    private List<Integer> mItemsToRemove = new ArrayList<>();
    private List<String> mItemValues = new ArrayList<>();

    @Override
    public void simpleClick(String value, int position) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert Dialog");
        alertDialog.setMessage("selected " + value);
        alertDialog.show();
    }

    @Override
    public void selectableClick(String value, int position) {

        mActionMode = ItemActionMode.prepareIfItIsNotFor(this);

        addOrRemoveSelectedItem(position);

        if (mItemsToRemove.size() == 0) {
            mActionMode.finish();
        }

        mActionMode.setTitle(String.valueOf(mItemsToRemove.size()));
    }

    private void addOrRemoveSelectedItem(int selectedItem) {

        for (int index = 0; index < mItemsToRemove.size(); index++) {

            int itemIndex = mItemsToRemove.get(index);

            if (itemIndex == selectedItem) {
                mItemsToRemove.remove(index);
                return;
            }

        }

        mItemsToRemove.add(selectedItem);

    }

    @Override
    public void remove() {

        showRemoveDesicionAlert();

    }

    @Override
    public void exit() {

        mItemsToRemove.clear();

        mSelectedItemAdapter.notifyDataSetChanged();

    }

    @Override
    public void accept() {

        removeSelectedItems();

        mItemsToRemove.clear();

        mSelectedItemAdapter.notifyDataSetChanged();

        mActionMode.finish();

    }

    @Override
    public void cancel() {

        mItemsToRemove.clear();

        mActionMode.finish();

        mSelectedItemAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_items_list);

        loadViews();

        initData();

        loadAdapter();

    }

    private void loadViews() {
        mItemList = (RecyclerView) findViewById(R.id.itemList);
    }

    private void initData() {
        mItemValues.add("Item one");
        mItemValues.add("Item two");
        mItemValues.add("Item three");
    }

    private void loadAdapter() {

        mSelectedItemAdapter = new SelectedItemAdapter(this, mItemValues);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        mItemList.setLayoutManager(layoutManager);
        mItemList.setAdapter(mSelectedItemAdapter);
        mItemList.setItemAnimator(new DefaultItemAnimator());
    }

    private void showRemoveDesicionAlert() {

        String dialogMessage = mItemsToRemove.size() > 1 ? String.format(getResources().
                getString(R.string.alert_dialog_message_some_items), mItemsToRemove.size()) :
                getResources().getString(R.string.alert_dialog_message_one_item);


        DesicionAlertDialog desicionAlertDialog = new DesicionAlertDialog.DesicionAlertDialogBuilder().
                message(dialogMessage).
                textPositiveButton(R.string.alert_dialog_positive).
                textNegativeButton(R.string.alert_dialog_negative).build();

        desicionAlertDialog.showFor(this);
    }

    private void removeSelectedItems() {
        for (Integer positionItem :  mItemsToRemove) {
            mItemValues.remove(positionItem.intValue());
        }
    }
}
