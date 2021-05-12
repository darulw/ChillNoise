package com.example.chillnoise;

import java.util.ArrayList;
import java.util.Arrays;

public class NoisesData {

    private ArrayList<Integer> images = new ArrayList<>(Arrays.asList(
            R.drawable.fire,
            R.drawable.rain,
            R.drawable.coffee,
            R.drawable.storm,
            R.drawable.tree,
            R.drawable.wave));
    private int mImages;
    public NoisesData(int i){
        mImages = images.get(i);
    }

    public int getImages() {
        return mImages;
    }
}
