package com.arkaapps.puttaraj.Pojo;

/**
 * Created by CSC on 3/29/2018.
 */

public class CarouselWallpaperPojo {
    private String name;
    private int imageSource;

    public CarouselWallpaperPojo(int imageSource, String name) {
        this.name = name;
        this.imageSource = imageSource;
    }

    public String getName() {
        return name;
    }

    public int getImageSource() {
        return imageSource;
    }
}
