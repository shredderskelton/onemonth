package com.shredder.onemonth.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.shredder.onemonth.R;
import com.shredder.onemonth.base.BaseFragment;
import com.shredder.onemonth.progress.ProgressManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsFragment extends BaseFragment {

    @Bind(R.id.settings_timestamp_entry) EditText overrrideTimestampEditText;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected String getTitle() {
        return "Settings";
    }

    @OnClick(R.id.settings_timestamp_entry_button)
    public void onOverride() {
        new ProgressManager(getActivity()).overrideStartDate(Long.valueOf(overrrideTimestampEditText.getText().toString()));
    }
}
