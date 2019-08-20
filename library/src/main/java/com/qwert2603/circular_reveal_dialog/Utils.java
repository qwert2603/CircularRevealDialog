package com.qwert2603.circular_reveal_dialog;

import androidx.annotation.NonNull;
import android.view.View;
import android.view.ViewTreeObserver;

final class Utils {
    interface PreDrawAction {
        boolean execute(@NonNull View view);
    }

    static void onPreDraw(@NonNull final View view, @NonNull final PreDrawAction action) {
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                return action.execute(view);
            }
        });
    }
}
