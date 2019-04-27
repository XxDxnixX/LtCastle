package com.example.lootthecastle;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {

    private static Button buttontomainscreen;
    private static ImageButton kaufbutton1;
    private static ImageButton kaufbutton2;
    private static ImageButton kaufbutton3;
    private static ImageButton kaufbutton4;
    private static ImageButton kaufbutton5;
    private static TextView goldanzeige;
    private static TextView schwertlvl;
    private static TextView schildlvl;
    private static TextView bogenlvl;
    private static TextView axtlvl;
    private static TextView streitkolbenlvl;
    private static TextView elixiranzeige;
    private static TextView preisanzeigeSchwert;
    private static TextView preisanzeigeSchild;
    private static TextView preisanzeigeBogen;
    private static TextView preisanzeigeAxt;
    private static TextView preisanzeigeStreitkolben;


    private double multischwert = 1.1;
    private double multischild = 1.05;
    private double multibogen = 1.15;
    private double multiaxt = 1.25;
    private double multistreitkolben = 1.75;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        buttontomainscreen = (Button) findViewById(R.id.button);
        kaufbutton1 = (ImageButton) findViewById(R.id.kaufbutton1);
        kaufbutton2 = (ImageButton) findViewById(R.id.kaufbutton2);
        kaufbutton3 = (ImageButton) findViewById(R.id.kaufbutton3);
        kaufbutton4 = (ImageButton) findViewById(R.id.kaufbutton4);
        kaufbutton5 = (ImageButton) findViewById(R.id.kaufbutton5);
        goldanzeige = (TextView) findViewById(R.id.goldtext);
        elixiranzeige = (TextView) findViewById(R.id.elixirtext);
        schwertlvl = (TextView) findViewById(R.id.schwertlvl);
        schildlvl = (TextView) findViewById(R.id.schildlvl);
        bogenlvl = (TextView) findViewById(R.id.bogenlvl);
        axtlvl = (TextView) findViewById(R.id.axtlvl);
        streitkolbenlvl = (TextView) findViewById(R.id.streitkolbenlvl);

        preisanzeigeSchwert = (TextView) findViewById(R.id.preisschwert);
        preisanzeigeSchild = (TextView) findViewById(R.id.preisschild);
        preisanzeigeBogen = (TextView) findViewById(R.id.preisbogen);
        preisanzeigeAxt = (TextView) findViewById(R.id.preisaxt);
        preisanzeigeStreitkolben = (TextView) findViewById(R.id.preisstreitkolben);

        refreshAMTandLVL ();

        buttontomainscreen.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        finish();
                    }
                });

        kaufbutton1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Values.schwert_kr ++;
                        refreshAMTandLVL ();
                    }
                });
        kaufbutton2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Values.schild_kr++;
                        refreshAMTandLVL ();
                    }
                });
        kaufbutton3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Values.bogen_kr++;
                        refreshAMTandLVL ();
                    }
                });
        kaufbutton4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Values.axt_kr++;
                        refreshAMTandLVL ();
                    }
                });
        kaufbutton5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Values.streitkolben_kr++;
                        refreshAMTandLVL ();
                    }
                });

    }
    void refreshAMTandLVL (){
        schwertlvl.setText(""+Values.schwert_kr);
        schildlvl.setText(""+Values.schild_kr);
        bogenlvl.setText(""+Values.bogen_kr);
        axtlvl.setText(""+Values.axt_kr);
        streitkolbenlvl.setText(""+Values.streitkolben_kr);
        goldanzeige.setText(""+Values.gold_amt);
        elixiranzeige.setText(""+Values.elixir_amt);

        preisanzeigeSchwert.setText(""+preisBerechnung(Values.schwert_kr,multischwert));
        preisanzeigeSchild.setText(""+preisBerechnung(Values.schild_kr,multischild));
        preisanzeigeBogen.setText(""+preisBerechnung(Values.bogen_kr,multibogen));
        preisanzeigeAxt.setText(""+preisBerechnung(Values.axt_kr,multiaxt));
        preisanzeigeStreitkolben.setText(""+preisBerechnung(Values.streitkolben_kr,multistreitkolben));
    }
    private double preisBerechnung (int lvl, double multiplikator) {
        double temp = 1;
        for (int i = 0; i<lvl; i++){
            temp = (temp * multiplikator);
        }
        int preis = (int) temp;
        return preis;
    }
}
