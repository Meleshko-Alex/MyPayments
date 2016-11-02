package edu.mypayments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Александр on 13.10.2016.
 */
public class MenuActivity extends AppCompatActivity {
    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        String options = getIntent().getStringExtra("option");
        FragmentManager fm = getSupportFragmentManager();
        //Fragment fragment = fm.findFragmentById(R.id.fragment_container);


        if (options.equals("send_meter")) {
            fragment = new SendLetterFragment();
        }

        if (options.equals("new_port_and_ip")) {
            fragment = new NewServerConectFragment();
        }

        fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();

    }
}
