package com.qwert2603.circular_reveal_dialog_example;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.widget.TimePicker;

import com.qwert2603.circular_reveal_dialog.CircularRevealDialog;
import com.qwert2603.circular_reveal_dialog.DialogButtonClickConsumeResult;
import com.qwert2603.circular_reveal_dialog.OnDialogButtonClickListenerAdapter;

public class TestTimeDialogFragment extends DialogFragment {

    private TimePicker timePicker = null;

    @SuppressWarnings("ConstantConditions")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(), null, 19, 18, true) {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                super.onTimeChanged(view, hourOfDay, minute);
                timePicker = view;
            }
        };
        CircularRevealDialog.initDialogForCircularReveal(this, timePickerDialog, new OnDialogButtonClickListenerAdapter() {
            @NonNull
            @Override
            public DialogButtonClickConsumeResult onPositive() {
                if (timePicker != null) {
                    return new DialogButtonClickConsumeResult(
                            true,
                            new Intent().putExtra("time", timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute())
                    );
                }
                return new DialogButtonClickConsumeResult(true, null);
            }
        }, getArguments().getInt(MainFragment.START_X_KEY), getArguments().getInt(MainFragment.START_Y_KEY), 400);
        return timePickerDialog;
    }
}
