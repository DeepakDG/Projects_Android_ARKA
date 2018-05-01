package com.arkaapps.puttaraj.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkaapps.puttaraj.Adapters.CarouselWallpaperAdapter;
import com.arkaapps.puttaraj.Pojo.CarouselWallpaperPojo;
import com.arkaapps.puttaraj.R;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

/**
 * Created by CSC on 3/29/2018.
 */

public class CarouselWallpaperFragment extends Fragment {

    private FeatureCoverFlow coverFlow;
    private CarouselWallpaperAdapter adapter;
    private ArrayList<CarouselWallpaperPojo> games;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.carouelwallpapersceen, container, false);
        coverFlow = (FeatureCoverFlow) view.findViewById(R.id.coverflow);

        settingDummyData();
        adapter = new CarouselWallpaperAdapter(getActivity(), games);
        coverFlow.setAdapter(adapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());
        return view;
    }

    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("MainActiivty", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("MainActivity", "scrolling");
            }
        };
    }

    private void settingDummyData() {
        games = new ArrayList<>();
        games.add(new CarouselWallpaperPojo(R.drawable.puttaraj_wallpaper_1, "Assassin Creed 3"));
        games.add(new CarouselWallpaperPojo(R.drawable.puttaraj_wallpaper_1, "Avatar 3D"));
        games.add(new CarouselWallpaperPojo(R.drawable.puttaraj_wallpaper_1, "Call Of Duty Black Ops 3"));
        games.add(new CarouselWallpaperPojo(R.drawable.puttaraj_wallpaper_1, "DotA 2"));
        games.add(new CarouselWallpaperPojo(R.drawable.puttaraj_wallpaper_1, "Halo 5"));
        games.add(new CarouselWallpaperPojo(R.drawable.puttaraj_wallpaper_1, "Left 4 Dead 2"));
        games.add(new CarouselWallpaperPojo(R.drawable.puttaraj_wallpaper_1, "StarCraft"));
        games.add(new CarouselWallpaperPojo(R.drawable.puttaraj_wallpaper_1, "The Witcher 3"));
        games.add(new CarouselWallpaperPojo(R.drawable.puttaraj_wallpaper_1, "Tom raider 3"));
        games.add(new CarouselWallpaperPojo(R.drawable.puttaraj_wallpaper_1, "Need for Speed Most Wanted"));
    }
}
