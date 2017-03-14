package io.dojogeek.modifiablelist;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by norveo on 3/5/17.
 */

public class DesicionAlertDialog {

    private String mMessage;
    private int mTextPositiveButton;
    private int mTextNegativeButton;

    private DesicionAlertDialog(String message, int textPositiveButton, int textNegativeButton) {
        mMessage = message;
        mTextPositiveButton = textPositiveButton;
        mTextNegativeButton = textNegativeButton;
    }

    public void showFor(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        final AlertDialogListener alertDialogListener = ((AlertDialogListener) context);

        builder.setMessage(mMessage)
               .setPositiveButton(mTextPositiveButton, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       alertDialogListener.accept();
                   }
               })
               .setNegativeButton(mTextNegativeButton, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       alertDialogListener.cancel();
                   }
               });

        builder.create().show();
    }

    public static class DesicionAlertDialogBuilder {

        private String mMessage;
        private int mTextPositiveButton;
        private int mTextNegativeButton;

        public DesicionAlertDialogBuilder message(String message) {
            mMessage = message;
            return this;
        }

        public DesicionAlertDialogBuilder textPositiveButton(int textPositiveButton) {
            mTextPositiveButton = textPositiveButton;
            return this;
        }

        public DesicionAlertDialogBuilder textNegativeButton(int textNegativeButton) {
            mTextNegativeButton = textNegativeButton;
            return this;
        }

        public DesicionAlertDialog build() {
            return new DesicionAlertDialog(mMessage, mTextPositiveButton, mTextNegativeButton);
        }

    }

}
