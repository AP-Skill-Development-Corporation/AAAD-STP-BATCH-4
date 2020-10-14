package com.example.apssdc.exampleads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    AdView adView;
    InterstitialAd ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adView = findViewById(R.id.av);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //Interstitial ad
        ad = new InterstitialAd(this);
        ad.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        ad.loadAd(new AdRequest.Builder().build());
    }

    public void showad(View view) {
        if (ad.isLoaded()){
            ad.show();
        }else{
            Toast.makeText(this, "Not yet loaded", Toast.LENGTH_SHORT).show();
        }
    }
}