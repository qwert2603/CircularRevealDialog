package com.qwert2603.circular_reveal_dialog;

import android.support.annotation.NonNull;

public class OnDialogButtonClickListenerAdapter implements OnDialogButtonClickListener {
    @NonNull
    @Override
    public DialogButtonClickConsumeResult onPositive() {
        return DialogButtonClickConsumeResult.DEFAULT;
    }

    @NonNull
    @Override
    public DialogButtonClickConsumeResult onNeutral() {
        return DialogButtonClickConsumeResult.DEFAULT;
    }

    @NonNull
    @Override
    public DialogButtonClickConsumeResult onNegative() {
        return DialogButtonClickConsumeResult.DEFAULT;
    }
}