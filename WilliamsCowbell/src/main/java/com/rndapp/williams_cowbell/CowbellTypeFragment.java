package com.rndapp.williams_cowbell;

import android.content.Context;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ell on 10/23/13.
 */
public class CowbellTypeFragment extends Fragment {
    /**
     * The fragment strings representing various arguments
     */
    private static final String ARG_TITLE = "title";
    private static final String ARG_IMAGE_ID = "image_id";

    /**
     * Returns a new instance of this fragment for the given args
     */
    public static CowbellTypeFragment newInstance(String title, int imageId) {
        CowbellTypeFragment fragment = new CowbellTypeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putInt(ARG_IMAGE_ID, imageId);
        fragment.setArguments(args);
        return fragment;
    }

    public CowbellTypeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cowbell, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.tv_title);
        textView.setText(getArguments().getString(ARG_TITLE));

        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
        imageView.setImageDrawable(getResources().getDrawable(getArguments().getInt(ARG_IMAGE_ID)));
        return rootView;
    }
}
