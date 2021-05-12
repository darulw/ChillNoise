package com.example.chillnoise;

import android.content.Intent;

import androidx.fragment.app.Fragment;

public class ActivityCreateFragment extends AbstractCreateNewFragment {
    private int test;


    @Override
    protected Fragment newFragment() {
        Intent intent = getIntent();
        test = intent.getIntExtra("key",0);
        if(test==R.id.authors){
            return new FragmentAuthor();
        }
        else if(test==R.id.donate_me){
            return new FragmentDonate();

        }
        return null;
    }
}
