package com.example.quarterapplication.view;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.quarterapplication.R;
import com.example.quarterapplication.fragment.RecommendFragment;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    private FragmentTransaction fragmentTransaction;
    private RecommendFragment recommendFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recommendFragment = new RecommendFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.lf, recommendFragment).commit();
        rg = findViewById(R.id.gr);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (checkedId) {
                    case R.id.rb1:
                        fragmentTransaction.show(recommendFragment).commit();
                        break;
                    case R.id.rb2:
                        break;
                    case R.id.rb3:
                        break;
                }
            }
        });
    }

    public void Hined() {
        if (recommendFragment != null && recommendFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().hide(recommendFragment).commit();
        }
       /* if (classify_fragment1 != null && classify_fragment1.isAdded()) {
            getSupportFragmentManager().beginTransaction().hide(classify_fragment1).commit();
        }
        if (find_fragment2 != null && find_fragment2.isAdded()) {
            getSupportFragmentManager().beginTransaction().hide(find_fragment2).commit();
        }*/
    }
}
