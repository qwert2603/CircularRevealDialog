package com.qwert2603.circular_reveal_dialog_example;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.qwert2603.circular_reveal_dialog.CircularRevealDialog;
import com.qwert2603.circular_reveal_dialog.OnDialogButtonClickListenerAdapter;
import com.qwert2603.circular_reveal_dialog.ResultListener;

public class SelectDialog extends DialogFragment {

    private ResultListener resultListener;

    @SuppressWarnings("ConstantConditions")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                .setTitle("SelectDialog")
                .setSingleChoiceItems(new String[]{"one", "two", "three", "four", "five", "six"}, 2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resultListener.onResult(new Intent().putExtra("selected_index", which));
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setNeutralButton("42", null)
                .create();
        resultListener = CircularRevealDialog.initDialogForCircularReveal(this, alertDialog, new OnDialogButtonClickListenerAdapter() {
            @Nullable
            @Override
            public Intent onNeutral() {
                return new Intent().putExtra("selected_index", 42);
            }
        }, getArguments().getInt(MainFragment.START_X_KEY), getArguments().getInt(MainFragment.START_Y_KEY), 500);
        return alertDialog;
    }
}
