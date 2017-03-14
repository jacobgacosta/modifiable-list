package io.dojogeek.modifiablelist.adapters;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.dojogeek.modifiablelist.R;
import io.dojogeek.modifiablelist.SelecteableListener;

/**
 * Created by norveo on 2/23/17.
 */

public class SelectedItemAdapter extends RecyclerView.Adapter<SelectedItemAdapter.ItemViewHolder> {

    private Context mContext;
    private List<String> mItemValues;
    private boolean mIsSelecteableMode;
    private SelecteableListener mSelecteableListener;

    public SelectedItemAdapter(Context context, List<String> itemvalues) {
        mContext = context;
        mItemValues = itemvalues;
        mSelecteableListener = (SelecteableListener) context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.clearView();
        holder.getItemValue().setText(mItemValues.get(position));
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return mItemValues.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {

        private boolean isSelected;
        private int mPosition;
        private TextView mItemValue;
        private View mContainerView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mContainerView = itemView;
            setListenersToView(itemView);
            mItemValue = (TextView) itemView.findViewById(R.id.itemValue);
        }

        public TextView getItemValue() {
            return mItemValue;
        }

        public void setPosition(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View view) {

            if (mIsSelecteableMode) {

                if (isSelected) {

                    changeToNormalMode(view);

                    isSelected = false;

                    mSelecteableListener.selectableClick(mItemValue.getText().toString(), mPosition);

                    return;
                }

                changeToPressedMode(view);

                isSelected = true;

                mSelecteableListener.selectableClick(mItemValue.getText().toString(), mPosition);

                return;
            }

            mSelecteableListener.simpleClick(mItemValue.getText().toString(), mPosition);
        }

        @Override
        public boolean onLongClick(View view) {

            enableSelecteableMode();

            isSelected = true;

            changeToPressedMode(view);

            mSelecteableListener.selectableClick(mItemValue.getText().toString(), mPosition);

            return true;
        }

        public void clearView() {
            setPosition(0);
            isSelected = false;
            mItemValue.setText(null);
            mContainerView.setBackgroundColor(Color.TRANSPARENT);
        }

        private void enableSelecteableMode() {
            mIsSelecteableMode = true;
        }

        private void setListenersToView(View itemView) {
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        private void changeToPressedMode(View view) {
            view.setBackgroundColor(Color.LTGRAY);
        }

        private void changeToNormalMode(View view) {
            view.setBackgroundColor(Color.TRANSPARENT);
        }
    }

}
