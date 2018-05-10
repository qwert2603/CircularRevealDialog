package com.qwert2603.circular_reveal_dialog;

import android.support.annotation.NonNull;

/**
 * Callbacks for dialog's button.
 * Return {@link DialogButtonClickConsumeResult} as result of consuming dialog's button click.
 * <p>
 * Return {@link DialogButtonClickConsumeResult#isExit} == true
 * to run exit animation, dismiss dialog and sent {@link DialogButtonClickConsumeResult#result} to target fragment, false otherwise.
 * <p>
 * {@link DialogButtonClickConsumeResult#result} will be sent only if {@link DialogButtonClickConsumeResult#isExit} == true.
 */
public interface OnDialogButtonClickListener {
    @NonNull
    DialogButtonClickConsumeResult onPositive();

    @NonNull
    DialogButtonClickConsumeResult onNeutral();

    @NonNull
    DialogButtonClickConsumeResult onNegative();
}