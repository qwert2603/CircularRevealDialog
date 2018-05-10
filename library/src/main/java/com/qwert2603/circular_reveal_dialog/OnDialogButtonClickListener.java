package com.qwert2603.circular_reveal_dialog;

import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Callbacks for dialog's button.
 * Returned intent (may be null) will be sent to target fragment.
 */
public interface OnDialogButtonClickListener {
    @Nullable
    Intent onPositive();

    @Nullable
    Intent onNeutral();

    @Nullable
    Intent onNegative();
}