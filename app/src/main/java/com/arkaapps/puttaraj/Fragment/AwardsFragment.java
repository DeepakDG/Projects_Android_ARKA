package com.arkaapps.puttaraj.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkaapps.puttaraj.R;

/**
 * Created by CSC on 3/26/2018.
 */

public class AwardsFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentawards, container, false);
        return view;
    }
}
