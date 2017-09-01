package com.arturoguillen.flightsearch.utils.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.arturoguillen.flightsearch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arturo.guillen on 01/09/2017.
 */

public class LoadingButton extends LinearLayout {

    @BindView(R.id.loading_button_bt)
    Button button;

    @BindView(R.id.loading_button_pb)
    ProgressBar progressBar;

    public LoadingButton(Context context) {
        super(context);
        init();
    }

    public LoadingButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LoadingButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        init();
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingButton, 0, 0);
        try {
            button.setText(ta.getString(R.styleable.LoadingButton_text));
        } finally {
            ta.recycle();
        }
    }

    private void init() {
        ButterKnife.bind(this, LayoutInflater
                .from(getContext())
                .inflate(R.layout.loading_button, this));
    }

    public void enableLoadingState() {
        setClickable(false);
        button.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);

    }

    public void disableLoadingState() {
        setClickable(true);
        button.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        button.setOnClickListener(l);
    }
}
