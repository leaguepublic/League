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
import android.widget.Toast;

import com.welab.league.R;

import java.util.ArrayList;

public class FilterLayout extends ConstraintLayout {
    private final int MAX_ROW_COUNT = 4;
    private final int SELECT_MAX_COUNT = 3;

    private Animation mUpAnim;
    private Animation mDownAnim;
    private Animation mDimAnim;

    private View mLocalNameMenuView;
    private View mDimView;
    private LinearLayout mLocalNameContainer;

    private ArrayList<String> mSelectedLocalNameList;

    OnClickListener mOkOnClickListener;

    public FilterLayout(Context context) {
        super(context);
    }

    public FilterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FilterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        findViewById(R.id.cancel_button).setOnClickListener(view -> {
            close();
        });

        findViewById(R.id.ok_button).setOnClickListener(view -> {
            startSlideDownAnim(getContext());
            mOkOnClickListener.onClick(view);
            mLocalNameContainer.removeAllViews();
        });

        mLocalNameMenuView = findViewById(R.id.menu_layout);
        mDimView = findViewById(R.id.dim_imageview);
    }

    public void setOkButtonListener(OnClickListener onClickListener) {
        mOkOnClickListener = onClickListener;
    }

    public void setData(ArrayList<String> localNameList, ArrayList<String> selectedLocalNameList) {
        mLocalNameContainer = findViewById(R.id.local_name_container);
        LinearLayout rowLayout = null;
        CheckBox localNameCheckBox = null;

        mSelectedLocalNameList = selectedLocalNameList;

        for (int i = 0; i < localNameList.size(); i++) {
            if (i % MAX_ROW_COUNT == 0) {
                rowLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.local_name_row_container, this, false);
                mLocalNameContainer.addView(rowLayout);
            }

            localNameCheckBox = (CheckBox) LayoutInflater.from(getContext()).inflate(R.layout.local_name_layout, rowLayout, false);
            localNameCheckBox.setText(localNameList.get(i));

            for (String selectedLocalName : mSelectedLocalNameList) {
                if (localNameList.get(i).equals(selectedLocalName) == true) {
                    localNameCheckBox.setChecked(true);
                }
            }

            localNameCheckBox.setOnCheckedChangeListener(mLocalNameOnCheckedChangeListener);

            rowLayout.addView(localNameCheckBox);
        }

        int emptyButtonCount = MAX_ROW_COUNT - (localNameList.size() % MAX_ROW_COUNT);

        for (int i = 0; i < emptyButtonCount; i++) {
            localNameCheckBox = (CheckBox) LayoutInflater.from(getContext()).inflate(R.layout.local_name_layout, rowLayout, false);
            localNameCheckBox.setVisibility(View.INVISIBLE);

            rowLayout.addView(localNameCheckBox);
        }

        startSlideupAnim(getContext(), this);
    }

    public boolean close() {
        boolean isOpened = mLocalNameContainer == null ? false : mLocalNameContainer.getChildCount() > 0;

        if (isOpened == true) {
            startSlideDownAnim(getContext());
            mLocalNameContainer.removeAllViews();
        }

        return isOpened;
    }

    private void startSlideupAnim(Context context, View rootView) {
        mUpAnim = AnimationUtils.loadAnimation(context, R.anim.slide_up);
        mUpAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mLocalNameMenuView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mDimAnim = AnimationUtils.loadAnimation(context, R.anim.fade_in_dim);
        mDimAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mDimView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mDimView.startAnimation(mDimAnim);
        mLocalNameMenuView.startAnimation(mUpAnim);
    }

    private void startSlideDownAnim(Context context) {
        mDimAnim = AnimationUtils.loadAnimation(context, R.anim.fade_out_dim);
        mDimAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                mDimView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        mDownAnim = AnimationUtils.loadAnimation(context, R.anim.slide_down);
        mDownAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                mLocalNameMenuView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        mDimView.startAnimation(mDimAnim);
        mLocalNameMenuView.startAnimation(mDownAnim);
    }

    private CompoundButton.OnCheckedChangeListener mLocalNameOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (isChecked == true) {
                if (mSelectedLocalNameList.size() < SELECT_MAX_COUNT) {
                    mSelectedLocalNameList.add(compoundButton.getText().toString());
                } else {
                    Toast.makeText(getContext(), R.string.select_local_subtitle, Toast.LENGTH_SHORT).show();
                    compoundButton.setOnCheckedChangeListener(null);
                    compoundButton.setChecked(false);
                    compoundButton.setOnCheckedChangeListener(mLocalNameOnCheckedChangeListener);
                }
            } else {
                mSelectedLocalNameList.remove(compoundButton.getText().toString());
            }
        }
    };
}
