package com.example.chillnoise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ActivityNoises extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private SeekBar mSeekBar;
    private AudioManager mAudioManager;
    private LinearLayout mLinearLayout;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noises);
        /*---------AUDIO-----------*/
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        /*---------END_AUDIO-----------*/


        /*---------HOOKS-----------*/
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_bar);
        mToolbar = (Toolbar) findViewById(R.id.toolbarek);
        mLinearLayout = (LinearLayout) findViewById(R.id.liniowy2);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        /*---------END_HOOKS-----------*/

        /*---------RECYCLER_VIEW--------*/
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        mRecyclerView.setHasFixedSize(true);

        int spanCount = 2;
        int spacing = 50;
        boolean includeEdge = true;
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        ArrayList<NoisesData> noisesData = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            noisesData.add(new NoisesData(i));
        }

        mRecyclerView.setAdapter(new MyAdapter(noisesData, mRecyclerView, getApplicationContext(), mLinearLayout));
        /*---------END_RECYCLER_VIEW--------*/

        /*---------DRAWER_MENU-------------*/
        setSupportActionBar(mToolbar);
        mNavigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        /*---------END_DRAWER_MENU-------------*/


        mLinearLayout.setVisibility(View.INVISIBLE);
        initControls();
    }

    /*---------AUDIO_SEEKBAR----------*/
    private void initControls() {
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mSeekBar.setMax(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        mSeekBar.setProgress(mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    /*---------END_AUDIO_SEEKBAR----------*/

    /*---------SELECTED_MENU_ITEM---------*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.authors:
                intent = new Intent(ActivityNoises.this, ActivityCreateFragment.class);
                intent.putExtra("key",R.id.authors);
                startActivity(intent);
                break;
            case R.id.donate_me:
                intent = new Intent(ActivityNoises.this, ActivityCreateFragment.class);
                intent.putExtra("key",R.id.donate_me);
                startActivity(intent);
                break;

        }

        return false;
    }
    /*---------END_SELECTED_MENU_ITEM---------*/

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, ServicePlayingAudio.class));
    }


}