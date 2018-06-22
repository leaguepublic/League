package com.welab.league.widget;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.welab.league.R;

import java.util.ArrayList;

public class FilterLayout extends ConstraintLayout {
    private final int MAX_ROW_COUNT = 3;

    private static Animation mUpAnim;
    private static Animation mDimAnim;

    public FilterLayout(Context context) {
        super(context);
    }

    public FilterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FilterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setData(ArrayList<String> localNameList, final ArrayList<String> SELECTED_LOCAL_NAME_LIST) {
        LinearLayout localNameContainer = findViewById(R.id.local_name_container);
        LinearLayout rowLayout = null;
        CheckBox localNameCheckBox = null;

        for (int i = 0; i < localNameList.size(); i++) {
            if (i % MAX_ROW_COUNT == 0) {
                rowLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.local_name_row_container, this, false);
                localNameContainer.addView(rowLayout);
            }

            localNameCheckBox = (CheckBox) LayoutInflater.from(getContext()).inflate(R.layout.local_name_layout, rowLayout, false);
            localNameCheckBox.setText(localNameList.get(i));
            localNameCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked == true) {
                        SELECTED_LOCAL_NAME_LIST.add(compoundButton.getText().toString());
                    } else {
                        SELECTED_LOCAL_NAME_LIST.remove(compoundButton.getText().toString());
                    }
                }
            });

            rowLayout.addView(localNameCheckBox);
        }

        startAnim(getContext(), this);
    }

    private void startAnim(Context context, View rootView) {
        View slidingUpview = rootView.findViewById(R.id.menu_layout);
        View dimView = rootView.findViewById(R.id.dim_imageview);

        if (mUpAnim == null) {
            mUpAnim = AnimationUtils.loadAnimation(context, R.anim.slide_up);
        }

        if (mDimAnim == null) {
            mDimAnim = AnimationUtils.loadAnimation(context, R.anim.fade_in_dim);
        }

        dimView.startAnimation(mDimAnim);
        slidingUpview.startAnimation(mUpAnim);
    }
}
