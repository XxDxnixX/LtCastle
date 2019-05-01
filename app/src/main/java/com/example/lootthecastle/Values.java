package com.example.lootthecastle;

import android.content.Context;

public class Values {
    public static int axt_kr = 0; //
    public static int schwert_kr = 0;//
    public static int schild_kr = 0;//
    public static int bogen_kr = 0;//
    public static int streitkolben_kr = 0;//
    public static int elixir_amt = 0;//
    public static int gold_amt = 0;//
    public static int click_amt = 0;
    public static int stage_lvl = 0;//


    public static void getValues(Context context) {
        DatabaseHelper dh = new DatabaseHelper(context);

        axt_kr = dh.findValue("axt_kr");
        schwert_kr = dh.findValue("schwert_kr");
        schild_kr = dh.findValue("schild_kr");
        bogen_kr = dh.findValue("bogen_kr");
        streitkolben_kr = dh.findValue("streitkolben_kr");
        elixir_amt = dh.findValue("elixir_amt");
        gold_amt = dh.findValue("gold_amt");
        click_amt = dh.findValue("click_amt");
        stage_lvl = dh.findValue("stage_lvl");
    }
    public static void setValues(Context context){
        if(true) {                                                 // Sichert aktuellen Fortschritt (true)

            DatabaseHelper dh = new DatabaseHelper(context);

            dh.replace("axt_kr", axt_kr);
            dh.replace("schwert_kr", schwert_kr);
            dh.replace("schild_kr", schild_kr);
            dh.replace("bogen_kr", bogen_kr);
            dh.replace("streitkolben_kr", streitkolben_kr);
            dh.replace("elixir_amt", elixir_amt);
            dh.replace("gold_amt", gold_amt);
            dh.replace("click_amt", click_amt);
            dh.replace("stage_lvl", stage_lvl);
        }
    }
    public static void resetData(Context context){
        DatabaseHelper dh = new DatabaseHelper(context);

        dh.replace("axt_kr", 0);
        dh.replace("schwert_kr", 0);
        dh.replace("schild_kr", 0);
        dh.replace("bogen_kr", 0);
        dh.replace("streitkolben_kr", 0);
        dh.replace("elixir_amt", 0);
        dh.replace("gold_amt", 0);
        dh.replace("click_amt", 0);
        dh.replace("stage_lvl", 0);
        Values.getValues(context);

    }
}
