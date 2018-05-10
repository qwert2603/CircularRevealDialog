package com.qwert2603.circular_reveal_dialog;

import android.arch.core.util.Function;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Listener that will be returned to animating DialogFragment.
 * DialogFragment must use it to send result to target fragment (resulting intent may be null).
 * Dialog's buttons click events must be consumed via {@link OnDialogButtonClickListener}
 */
public final class ResultListener {

    @NonNull
    private final Function<Intent, Void> listener;

    ResultListener(@NonNull Function<Intent, Void> listener) {
        this.listener = listener;
    }

    public void onResult(@Nullable Intent intent) {
        listener.apply(intent);
    }
}
