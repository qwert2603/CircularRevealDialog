package com.qwert2603.circular_reveal_dialog;

import android.content.Intent;
import android.support.annotation.Nullable;

public class OnDialogButtonClickListenerAdapter implements OnDialogButtonClickListener {
    @Nullable
    @Override
    public Intent onPositive() {
        return null;
    }

    @Nullable
    @Override
    public Intent onNeutral() {
        return null;
    }

    @Nullable
    @Override
    public Intent onNegative() {
        return null;
    }
}