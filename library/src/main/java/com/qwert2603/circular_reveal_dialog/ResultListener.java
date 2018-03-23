package com.qwert2603.circular_reveal_dialog;

import android.arch.core.util.Function;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
