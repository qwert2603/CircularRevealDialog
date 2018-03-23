package com.qwert2603.circular_reveal_dialog;

import android.arch.core.util.Function;
import android.content.Intent;

public final class ResultListener {

    private Function<Intent, Void> listener;

    ResultListener(Function<Intent, Void> listener) {
        this.listener = listener;
    }

    public void onResult(Intent intent) {
        listener.apply(intent);
    }
}
