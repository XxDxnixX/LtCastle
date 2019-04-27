package com.example.lootthecastle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton schildbtn;
    private TextView countdown;
    private TextView goldanzeige;
    private ImageView elixirimage;
    private ImageView goldimage;
    private TextView cpstext;

    public int countdownint;
    public int counterint = 0;
    public int countdown1 = 1;
    private int schildlebenholz = 5;
    private int schildlebenbronze = 10;
    private int schildlebeneisen = 15;
    private int schildlebengold = 20;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    break;

                case R.id.navigation_shop:
                    Intent intent1 = new Intent(MainActivity.this, ShopActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.navigation_settings:
                    break;
            }
            return false;
        }
    };
    /**Initialisierung der Database*/
    public void databaseSetup() {
        DatabaseHelper dh = new DatabaseHelper(this);
        dh.addData("axt_kr",0);
        dh.addData("schwert_kr",0);
        dh.addData("schild_kr",0);
        dh.addData("bogen_kr",0);
        dh.addData("streitkolben_kr",0);
        dh.addData("elixir_amt",0);
        dh.addData("gold_amt",0);
        dh.addData("click_amt",0);
        dh.addData("stage_lvl",0);
        Values.getValues(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseSetup();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        schildbtn = (ImageButton) findViewById(R.id.imageButton);
        countdown = (TextView) findViewById(R.id.countdown);
        goldanzeige = (TextView) findViewById(R.id.goldanzeige);
        goldimage = (ImageView) findViewById(R.id.goldimage);
        cpstext = (TextView) findViewById(R.id.cpstext);
        elixirimage = (ImageView) findViewById(R.id.imageView3);
        countdown.setText("" + countdownint);
        cpstext.setVisibility(View.INVISIBLE);
        elixirimage.setVisibility(View.INVISIBLE);
        schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildholz0));


      //  updateCounter();
      //  updateSchildButton();


        // Holz, Bronze , Eisen, Gold  COUNTDOWNS
        schildbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity","Values.click_amt " + Values.click_amt + " Values.stage_lvl " + Values.stage_lvl + " countdownint "+ countdownint + " countdown1 " + countdown1 + " counterint " + counterint);
                Values.click_amt++;

                if (Values.stage_lvl == 0) {
                    countdown.setText("" + countdownint);
                    countdownint--;
                    updateSchildButton();


                }

                // BEGINN DER 2. STAGE
                else if (Values.stage_lvl == 1) {
                    counterint++;  // Max. bis ca. 2* 10^9
                    countdown.setText("" + counterint);
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.unknown2));
                    Values.elixir_amt ++;
                    elixirimage.setVisibility(View.VISIBLE);
                    cpstext.setVisibility(View.VISIBLE);
                }
                refreshGoldandElexir();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        updateCounter();
        updateSchildButton();
        refreshGoldandElexir();
        Log.d("MainActivity","Values.click_amt " + Values.click_amt + " Values.stage_lvl " + Values.stage_lvl + " countdownint "+ countdownint + " countdown1 " + countdown1 + " counterint " + counterint);
    }

    @Override
    protected void onResume(){
        super.onResume();
        refreshGoldandElexir();
    }

    @Override
    protected void onDestroy (){
        super.onDestroy();
        Values.setValues(this);
    }

    /**Refreshen der Gold- & Elexiranzeige. Sie sind bei 0 ausgeblendet.*/
    public void refreshGoldandElexir(){
        goldanzeige.setText(""+Values.gold_amt);
        if (Values.gold_amt == 0){
            goldanzeige.setVisibility(View.INVISIBLE);
            goldimage.setVisibility(View.INVISIBLE);
        } else {
            goldanzeige.setVisibility(View.VISIBLE);
            goldimage.setVisibility(View.VISIBLE);
        }
        cpstext.setText("CPS:"+getCps());

    }

    /**Updaten des Schildbuttons*/
    private void updateSchildButton() {
        switch (countdown1) {
            // HOLZ
            case 1: {
                int i = (countdownint * 100 / schildlebenholz);

                if(i > 75) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildholz0));
                }
                if (i <= 75 && i > 50) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildholz1));
                    //test();
                }
                if (i <= 50 && i > 25) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildholz2));
                }
                if (i <= 25 && i > 00) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildholz3));
                }
                if(i <= 00) {
                    countdown1++;
                    Values.gold_amt = Values.gold_amt + 50;
                    countdownint = schildlebenbronze;
                }
                break;
            }
            // BRONZE
            case 2: {
                int i = (countdownint * 100 / schildlebenbronze);

                if(i > 75) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildbronze0));
                }
                if (i <= 75 && i > 50) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildbronze1));
                }
                if (i <= 50 && i > 25) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildbronze2));
                }
                if (i <= 25 && i > 00) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildbronze3));
                }
                if(i <= 00) {
                    Values.gold_amt = Values.gold_amt + 200;
                    countdown1++;
                    countdownint = schildlebeneisen;
                }
                break;
            }
            // EISEN
            case 3: {
                int i = (countdownint * 100 / schildlebeneisen);

                if(i > 75) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildeisen0));
                }
                if (i <= 75 && i > 50) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildeisen1));
                }
                if (i <= 50 && i > 25) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildeisen2));
                }
                if (i <= 25 && i > 00) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildeisen3));
                }
                if(i <= 00) {
                    Values.gold_amt = Values.gold_amt + 750;
                    countdown1++;
                    countdownint = schildlebengold;
                }
                break;
            }
            // GOLD
            case 4: {
                int i = (countdownint * 100 / schildlebengold);

                if(i > 75) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildgold0));
                }
                if (i <= 75 && i > 50) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildgold1));
                }
                if (i <= 50 && i > 25) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildgold2));
                }
                if (i <= 25 && i > 00) {
                    schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.schildgold3));
                }
                if(i <= 00) {
                    Values.gold_amt = Values.gold_amt + 4000;
                    countdown1++;
                }
                break;
            }
            // 2nd STAGE
            case 5: {
                Values.stage_lvl = 1;
                schildbtn.setImageDrawable(getResources().getDrawable(R.drawable.unknown2));
                elixirimage.setVisibility(View.VISIBLE);
                cpstext.setVisibility(View.VISIBLE);
                break;
            }

        }
        refreshGoldandElexir();
    }

    /**Updaten der Counter*/
    private void updateCounter() {
        if(Values.stage_lvl == 1) {
            counterint = Values.click_amt - (schildlebenholz + schildlebenbronze + schildlebeneisen + schildlebengold);
            countdown1 = 5;
            countdown.setText(""+counterint);
        } else {
            int temp = Values.click_amt;
            if((temp - schildlebenholz) < 0) {
                countdown1 = 1;
                countdownint = schildlebenholz - temp;
            } else if((temp - (schildlebenholz + schildlebenbronze)) < 0) {
                countdown1 = 2;
                countdownint = (schildlebenholz + schildlebenbronze) - temp;
            } else if((temp - (schildlebenholz + schildlebenbronze + schildlebeneisen)) < 0) {
                countdown1 = 3;
                countdownint = (schildlebenholz + schildlebenbronze + schildlebeneisen) - temp;
            } else if((temp - (schildlebenholz + schildlebenbronze + schildlebeneisen + schildlebengold)) < 0) {
                countdown1 = 4;
                countdownint = (schildlebenholz + schildlebenbronze + schildlebeneisen + schildlebengold) - temp;
            }
            countdown.setText(""+countdownint);
        }
    }
    /**Berechnen der CPS*/
    private double getCps (){
        return (Values.schwert_kr*0.3)+(Values.schild_kr*0.1)+(Values.bogen_kr*0.5)+(Values.axt_kr*1.5)+(Values.streitkolben_kr*5.0);
    }
}
