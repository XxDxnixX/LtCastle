package com.example.lootthecastle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.example.lootthecastle.R.id.countdown;

public class MainActivity extends AppCompatActivity {

    private ImageButton schildbtn;
    private TextView countdown;
    public int countdownint = 5;
    public int counterint= 0;
    public int countdown1 = 1;
    public int stage = 1;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        schildbtn = (ImageButton) findViewById(R.id.imageButton);
        countdown = (TextView) findViewById(R.id.countdown);
        countdown.setText(""+countdownint);


        // Holz, Bronze , Eisen, Gold  COUNTDOWNS
        schildbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stage == 1) {
                    countdownint--;
                    countdown.setText("" + countdownint);

                    if (countdownint < 1) {
                        countdown1++;

                        switch (countdown1) {
                            // HOLZ
                            case 1: {
                                countdownint = 5;
                                break;
                            }
                            // BRONZE
                            case 2: {
                                countdownint = 10;
                                break;
                            }
                            // EISEN
                            case 3: {
                                countdownint = 15;
                                break;
                            }
                            // GOLD
                            case 4: {
                                countdownint = 20;
                                break;
                            }

                            // Übergang zu 2. Stage
                            case 5: {
                                stage = 2;
                                break;
                            }

                        }
                    }


                }

                // BEGINN DER 2. STAGE
                else if (stage == 2){
                    counterint ++;  // Max. bis ca. 2* 10^9
                    countdown.setText("" + counterint);
// original Thanos
                }

            }
        });


}



}
