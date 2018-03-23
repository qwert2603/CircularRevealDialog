package com.qwert2603.circular_reveal_dialog_example;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.qwert2603.circular_reveal_dialog.CircularRevealDialog;
import com.qwert2603.circular_reveal_dialog.OnDialogButtonClickListenerAdapter;

public class ExampleDialog extends DialogFragment {

    @SuppressWarnings("ConstantConditions")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                .setTitle("ExampleDialog")
                .setMessage("Городская трасса в Альберт-парке редко используется, утром асфальт был пыльным и грязным, но потом ситуация нормализовалась. В Pirelli довольны итогами дня и готовятся к дождям в субботу.")
                .setPositiveButton(android.R.string.ok, null)
                .setNeutralButton("untitled", null)
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        CircularRevealDialog.initDialogForCircularReveal(this, alertDialog, new OnDialogButtonClickListenerAdapter() {
            @Nullable
            @Override
            public Intent onPositive() {
                Toast.makeText(requireContext(), "ok clicked", Toast.LENGTH_SHORT).show();
                return null;
            }
        }, getArguments().getInt(MainFragment.START_X_KEY), getArguments().getInt(MainFragment.START_Y_KEY), 500);
        return alertDialog;
    }
}
