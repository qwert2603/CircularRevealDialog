package com.qwert2603.circular_reveal_dialog_example;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {

    public static final String START_X_KEY = "startX";
    public static final String START_Y_KEY = "startY";

    private int lastTouchX;
    private int lastTouchY;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.showDialog_TextView).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                lastTouchX = (int) event.getRawX();
                lastTouchY = (int) event.getRawY();
                return false;
            }
        });
        view.findViewById(R.id.showTimeDialog_TextView).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                lastTouchX = (int) event.getRawX();
                lastTouchY = (int) event.getRawY();
                return false;
            }
        });
        view.findViewById(R.id.showSelectDialog_TextView).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                lastTouchX = (int) event.getRawX();
                lastTouchY = (int) event.getRawY();
                return false;
            }
        });

        view.findViewById(R.id.showDialog_TextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ExampleDialog exampleDialog = new ExampleDialog();
                exampleDialog.setArguments(createArguments());
                exampleDialog.setTargetFragment(MainFragment.this, 2);
                exampleDialog.show(getFragmentManager(), "ExampleDialog");
            }
        });
        view.findViewById(R.id.showTimeDialog_TextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TestTimeDialogFragment exampleDialog = new TestTimeDialogFragment();
                exampleDialog.setArguments(createArguments());
                exampleDialog.setTargetFragment(MainFragment.this, 3);
                exampleDialog.show(getFragmentManager(), "ExampleDialog");
            }
        });
        view.findViewById(R.id.showSelectDialog_TextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SelectDialog selectDialog = new SelectDialog();
                selectDialog.setArguments(createArguments());
                selectDialog.setTargetFragment(MainFragment.this, 1);
                selectDialog.show(getFragmentManager(), "SelectDialog");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("AASSDD", "onActivityResult " + requestCode + " " + resultCode + " " + data);
        if (data != null) Log.d("AASSDD", "onActivityResult data" + data.getExtras());
    }

    private Bundle createArguments() {
        final Bundle args = new Bundle();
        args.putInt(START_X_KEY, lastTouchX);
        args.putInt(START_Y_KEY, lastTouchY);
        return args;
    }
}
