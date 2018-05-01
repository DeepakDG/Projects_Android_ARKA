package com.arkaapps.puttaraj.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arkaapps.puttaraj.FontUtils;
import com.arkaapps.puttaraj.R;

/**
 * Created by CSC on 3/26/2018.
 */

public class BioFragment extends Fragment {

    private View view;
    private TextView mTextViewHeading, mTextViewDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentbio, container, false);

        init();
        return view;
    }

    private void init() {
        mTextViewHeading = (TextView) view.findViewById(R.id.tv_bio_heading);
        mTextViewDetails = (TextView) view.findViewById(R.id.tv_bio_details);
        FontUtils fn = new FontUtils();
        mTextViewHeading.setTypeface(fn.getBaskervilleFont(getActivity()));
        mTextViewDetails.setTypeface(fn.getCalibriFont(getActivity()));

    }
}
