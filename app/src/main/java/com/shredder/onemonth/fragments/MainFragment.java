package com.shredder.onemonth.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shredder.onemonth.R;
import com.shredder.onemonth.base.BackButtonSupportFragment;
import com.shredder.onemonth.base.BaseFragment;
import com.shredder.onemonth.build.AlarmBuilder;
import com.shredder.onemonth.progress.ProgressManager;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends BaseFragment implements BackButtonSupportFragment {

    @BindString(R.string.directions_title) String title;
    @Bind(R.id.directions_stop_start_button) Button startStopButton;
    @Bind(R.id.directions_text_detailed) TextView detailsTextView;
    @Bind(R.id.directions_text_welcome) TextView welcomeTextView;
    @Bind(R.id.directions_text_days) TextView daysTextView;
    @Bind(R.id.directions_days_sober_view) View daysSoberView;

    private ProgressManager progressManager;
    private AlarmBuilder alarmBuilder;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        progressManager = new ProgressManager(getActivity());
        alarmBuilder = new AlarmBuilder(getActivity());
        onStateChange();
        return view;
    }

    private void onStateChange() {
        startStopButton.setText(progressManager.isStarted() ? R.string.directions_stop : R.string.directions_start);
        detailsTextView.setText(progressManager.isStarted() ? R.string.directions_detailed_started : R.string.directions_detailed);
        welcomeTextView.setText(progressManager.isStarted() ? R.string.directions_welcome_started : R.string.directions_welcome);
        daysTextView.setText(progressManager.isStarted() ? String.valueOf(progressManager.daysSober()) : "");
        daysSoberView.setVisibility(progressManager.isStarted() ? View.VISIBLE : View.GONE);
    }

    @Override
    protected String getTitle() {
        return title;
    }

    @Override
    public boolean onBackPressed() {
        return false; //delegated
    }

    @OnClick(R.id.directions_stop_start_button)
    public void onStartPressed() {
        if (progressManager.isStarted()) {
            alarmBuilder.cancelAlarm();
            progressManager.stop();
        } else {
            alarmBuilder.createAlarm();
            progressManager.start();
        }
        onStateChange();
    }

    @OnClick(R.id.directions_reset_button)
    public void onResetPressed() {
        alarmBuilder.cancelAlarm();
        progressManager.stop();
        alarmBuilder.createAlarm();
        progressManager.start();
        onStateChange();
    }

    @OnClick(R.id.directions_check_in_button)
    public void onCheckinPressed() {
        progressManager.checkIn();
        onStateChange();
    }
}