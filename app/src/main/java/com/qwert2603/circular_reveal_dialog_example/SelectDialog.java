package com.qwert2603.circular_reveal_dialog_example;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

import com.qwert2603.circular_reveal_dialog.CircularRevealDialog;
import com.qwert2603.circular_reveal_dialog.DialogButtonClickConsumeResult;
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
                .setSingleChoiceItems(new String[]{"zero", "one", "two", "three", "four", "five", "six"}, 2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resultListener.onResult(new Intent().putExtra("selected_index", which));
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setNeutralButton("42", null)
                .setPositiveButton("positive", null)
                .create();
        resultListener = CircularRevealDialog.initDialogForCircularReveal(this, alertDialog, new OnDialogButtonClickListenerAdapter() {
            @NonNull
            @Override
            public DialogButtonClickConsumeResult onNeutral() {
                return new DialogButtonClickConsumeResult(false, null);
            }

            @NonNull
            @Override
            public DialogButtonClickConsumeResult onPositive() {
                final Intent intent = new Intent().putExtra("selected_index", 26);
                return new DialogButtonClickConsumeResult(true, intent);
            }
        }, getArguments().getInt(MainFragment.START_X_KEY), getArguments().getInt(MainFragment.START_Y_KEY), 500);
        return alertDialog;
    }
}
