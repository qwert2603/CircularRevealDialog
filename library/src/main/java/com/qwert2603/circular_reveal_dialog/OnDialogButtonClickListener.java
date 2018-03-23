package com.qwert2603.circular_reveal_dialog;

import android.content.Intent;
import android.support.annotation.Nullable;

public interface OnDialogButtonClickListener {
    @Nullable
    Intent onPositive();

    @Nullable
    Intent onNeutral();

    @Nullable
    Intent onNegative();
}