package com.qwert2603.circular_reveal_dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.core.util.Function;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;

public final class CircularRevealDialog {

    public static final String KEY_CENTER_X = "KEY_CENTER_X";
    public static final String KEY_CENTER_Y = "KEY_CENTER_Y";
    public static final String KEY_START_ANIMATION_SHOWN = "KEY_START_ANIMATION_SHOWN";
    public static final String KEY_WAS_DESTROYED = "KEY_WAS_DESTROYED";

    @NonNull
    public static ResultListener initDialogForCircularReveal(
            @NonNull final DialogFragment dialogFragment,
            @NonNull final AlertDialog alertDialog,
            @NonNull final OnDialogButtonClickListener buttonClickListener,
            @Nullable final Integer startX,
            @Nullable final Integer startY,
            final long duration
    ) {
        final class Helper {
            private final float minRadius;
            @NonNull
            private final Bundle arguments;

            private Helper() {
                Bundle arguments = dialogFragment.getArguments();
                if (arguments == null) {
                    arguments = new Bundle();
                    dialogFragment.setArguments(arguments);
                }
                this.arguments = arguments;
                minRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, dialogFragment.getResources().getDisplayMetrics());
                init();
            }

            private void init() {
                dialogFragment.getLifecycle().addObserver(new LifecycleObserver() {
                    @OnLifecycleEvent(Lifecycle.Event.ON_START)
                    void onStart() {
                        final Window window = alertDialog.getWindow();
                        if (window == null) return;
                        final View decorView = window.getDecorView();
                        Utils.onPreDraw(decorView, new Utils.PreDrawAction() {
                            @Override
                            public boolean execute(View view) {
                                if (arguments.getBoolean(KEY_START_ANIMATION_SHOWN) || startX == null || startY == null) {
                                    return true;
                                }
                                int[] locationOnScreen = new int[2];
                                decorView.getLocationOnScreen(locationOnScreen);
                                int centerX = startX - locationOnScreen[0];
                                int centerY = startY - locationOnScreen[1];
                                arguments.putInt(KEY_CENTER_X, centerX);
                                arguments.putInt(KEY_CENTER_Y, centerY);
                                final float endRadius = (float) Math.hypot(decorView.getWidth(), decorView.getHeight());
                                arguments.putBoolean(KEY_START_ANIMATION_SHOWN, true);
                                decorView.setVisibility(View.GONE);
                                decorView.setVisibility(View.VISIBLE);
                                final Animator animator = ViewAnimationUtils.createCircularReveal(decorView, centerX, centerY, minRadius, endRadius);
                                animator.setDuration(duration);
                                animator.setInterpolator(new AccelerateInterpolator());
                                animator.start();
                                return false;
                            }
                        });
                    }

                    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                    void onDestroy() {
                        arguments.putBoolean(KEY_WAS_DESTROYED, true);
                    }
                });
            }

            void runExitAnimation(@Nullable Intent data) {
                final Fragment targetFragment = dialogFragment.getTargetFragment();
                if (targetFragment != null) {
                    targetFragment.onActivityResult(dialogFragment.getTargetRequestCode(), Activity.RESULT_OK, data);
                }

                final Window window = alertDialog.getWindow();
                if (window == null) return;
                final View decorView = window.getDecorView();
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(null);
                alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(null);
                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(null);
                decorView.setOnTouchListener(new View.OnTouchListener() {
                    @SuppressLint("ClickableViewAccessibility")
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });
                int startX = arguments.getBoolean(KEY_WAS_DESTROYED) ? decorView.getWidth() / 2 : arguments.getInt(KEY_CENTER_X);
                int startY = arguments.getBoolean(KEY_WAS_DESTROYED) ? decorView.getHeight() / 2 : arguments.getInt(KEY_CENTER_Y);
                float startRadius = (float) Math.hypot(decorView.getWidth(), decorView.getHeight());
                decorView.animate()
                        .setStartDelay(duration * 5 / 8)
                        .setDuration(duration * 3 / 8)
                        .alpha(0f);
                final Animator animator = ViewAnimationUtils.createCircularReveal(decorView, startX, startY, startRadius, minRadius);
                animator.setDuration(duration);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (dialogFragment.getFragmentManager() != null) {
                            dialogFragment.dismissAllowingStateLoss();
                        }
                    }
                });
                animator.start();
            }
        }

        final Helper helper = new Helper();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    helper.runExitAnimation(null);
                    return true;
                }
                return false;
            }
        });
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        helper.runExitAnimation(buttonClickListener.onPositive());
                    }
                });
                alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        helper.runExitAnimation(buttonClickListener.onNeutral());
                    }
                });
                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        helper.runExitAnimation(buttonClickListener.onNegative());
                    }
                });
            }
        });
        return new ResultListener(new Function<Intent, Void>() {
            @Override
            public Void apply(Intent input) {
                helper.runExitAnimation(input);
                return null;
            }
        });
    }
}
