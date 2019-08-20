package com.qwert2603.circular_reveal_dialog;

import android.content.Intent;
import androidx.annotation.Nullable;

final public class DialogButtonClickConsumeResult {

    public static final DialogButtonClickConsumeResult DEFAULT = new DialogButtonClickConsumeResult(true, null);

    public final boolean isExit;

    @Nullable
    public final Intent result;

    public DialogButtonClickConsumeResult(boolean isExit, @Nullable Intent result) {
        this.isExit = isExit;
        this.result = result;
    }
}
