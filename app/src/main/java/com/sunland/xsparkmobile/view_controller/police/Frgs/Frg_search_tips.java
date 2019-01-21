package com.sunland.xsparkmobile.view_controller.police.Frgs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunland.xsparkmobile.R;

import butterknife.ButterKnife;

public class Frg_search_tips extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_search_tips, container, false);
        ButterKnife.bind(this,view);
        return view;
    }


}
