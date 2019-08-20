package com.qwert2603.circular_reveal_dialog;

import android.app.Dialog;
import android.widget.Button;

final class DialogButtonsGetter {

    private final Dialog dialog;

    DialogButtonsGetter(Dialog dialog) {
        this.dialog = dialog;
    }

    Button getDialogButton(int which) {
        if (dialog instanceof android.app.AlertDialog) {
            return ((android.app.AlertDialog) dialog).getButton(which);
        } else if (dialog instanceof androidx.appcompat.app.AlertDialog) {
            return ((androidx.appcompat.app.AlertDialog) dialog).getButton(which);
        } else {
            throw new IllegalArgumentException("dialog must be android.app.AlertDialog or android.support.v7.app.AlertDialog");
        }
    }
}
