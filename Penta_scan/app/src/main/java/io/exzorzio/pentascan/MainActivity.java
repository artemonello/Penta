package io.exzorzio.pentascan;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity{
   //private Timer mTimer;
    //private MyTimerTask MyTimerTask;
   TextView helptxt;
    ImageView penta;
    ImageView backgod;
    ImageView godpick;
    Button pashalka;
    Button check_god;
LinearLayout godent;
    TextInputEditText type_god;
 DBBHelper dbbHelper;
//TextView time;
    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        penta = (ImageView) findViewById(R.id.penta);
        backgod = (ImageView) findViewById(R.id.backgod);
        godpick = (ImageView) findViewById(R.id.godpick);
        pashalka = (Button) findViewById(R.id.pashalka);
        check_god = (Button) findViewById(R.id.check_god);
        godent = (LinearLayout) findViewById(R.id.godent);
        type_god = (TextInputEditText) findViewById(R.id.type_god);
        //time = (TextView) findViewById(R.id.time);
        helptxt = (TextView) findViewById(R.id.helptxt);
        final Animation a = AnimationUtils.loadAnimation(MainActivity.this,
                R.anim.rotate);
        a.setDuration(8000);
        penta.startAnimation(a);
        dbbHelper = new DBBHelper(this);
        penta.setOnClickListener(OnClickListener1);
pashalka.setOnClickListener(OnClickListener2);
        backgod.setOnClickListener(OnClickListener3);


        check_god.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                 String name_god = type_god.getText().toString();
                 String appropriate_vars[] ={"Odin","Odinn","odin","odinn","Один","Одинн","один","одинн"};

                 for(int i=0;i<appropriate_vars.length;i++){
                     if(name_god.equals(appropriate_vars[i])){
                         godpick.setImageResource(R.drawable.dich35);
                         helptxt.setText("Нихуя ты баклажан, молодчик");
                         break;
                     }
                     else{
                         helptxt.setText("Ну ты и лох, маслорий, иди покезяй,солнышко :-)");
                         break;
                     }
                 }
            }});

        type_god.setOnFocusChangeListener(listener);
    }
    View.OnClickListener OnClickListener3  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            penta.setVisibility(View.VISIBLE);
            godent.setVisibility(View.INVISIBLE);
            backgod.setVisibility(View.INVISIBLE);
        }};
    View.OnClickListener OnClickListener2  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            penta.setVisibility(View.INVISIBLE);
godent.setVisibility(View.VISIBLE);
backgod.setVisibility(View.VISIBLE);
        }};

    View.OnClickListener OnClickListener1  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ContentValues idd = new ContentValues();

            SQLiteDatabase d = dbbHelper.getWritableDatabase();
           /* if (mTimer != null) {
                mTimer.cancel();
            }*/


           Calendar calendar = Calendar.getInstance();/*

            int mi;
            int min;

            int curmin = calendar.get(Calendar.MINUTE);
            int hrs = calendar.get(Calendar.HOUR_OF_DAY);
           int checkint = 1000;
            Cursor cur = d.rawQuery("SELECT COUNT(*) FROM ttable", null);
            if (cur != null) {
                cur.moveToFirst();                       // Always one row returned.
                if (cur.getInt (0) == 0){               // Zero count means empty table.
                    idd.put("minute", curmin);

                    long rowID = d.insert("ttable", null, idd);

                    //
                    Cursor cc = d.query("ttable", null, null, null, null, null, null);
                    cc.moveToLast();


                    min = cc.getColumnIndex("minute");
                    int index = cc.getColumnIndex("id");
                    int hour = cc.getColumnIndex("hour");
                    mi = cc.getInt(min);
                    int hor = cc.getInt(hour);
                    int id = cc.getInt(index);
                    cc.close();
                    String iid = Long.toString(rowID);

                     Intent intent = new Intent(MainActivity.this, AboutActivity.class);

                        startActivity(intent);

                }
                else if(cur.getInt (0) !=0){
                    Cursor ccc = d.query("ttable", null, null, null, null, null, null);
                    ccc.moveToLast();
                     min = ccc.getColumnIndex("minute");
                    //int index = ccc.getColumnIndex("id");
                    //int hour = ccc.getColumnIndex("hour");
                    mi = ccc.getInt(min);
                   // int hor = ccc.getInt(hour);
                    //int id = ccc.getInt(index);
                    /*ccc.moveToPrevious();
                    int minprev = ccc.getColumnIndex("minute");
                    int miprev = ccc.getInt(minprev);*/
                    //int div =5;
                    /*if(curmin<mi+2){

                        time.setText("Погодь 2м дядя");
                    }
                    else if(curmin>mi+2){
                        idd.put("minute", curmin);
                        ccc.moveToLast();
                        min = ccc.getColumnIndex("minute");
                        mi = ccc.getInt(min);
                        time.setText("Го парень");
                        //Intent intent = new Intent(MainActivity.this, AboutActivity.class);

                        String miii = Integer.toString(mi);

                        time.setText(miii);
                       // startActivity(intent);

                    }
                }
                cur.close();
            }

            dbbHelper.close();*/



            //int hrs_db;
            //int minn_db;



   /* else{
//if(curmin<mi+2){
  cc.moveToLast();
    int minlast = cc.getColumnIndex("minute");
    int milast = cc.getInt(minlast);
    cc.moveToPrevious();
    int minprev = cc.getColumnIndex("minute");
    int miprev = cc.getInt(minlast);*/




  /*  time.setText("Погодь 2м дядя");
    calendar.set(Calendar.MINUTE, curmin + 2);
    calendar.set(Calendar.HOUR_OF_DAY, hrs);
    mTimer = new Timer();
    MyTimerTask = new MyTimerTask();
    mTimer.schedule(MyTimerTask, calendar.getTime());
    penta.setEnabled(false);*/
//}else{
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);

            startActivity(intent);
}



      /*  idd.put("hour", hrs);
        idd.put("minute",curmin+2);
        d.insert("ttable", null, idd);
//cc.moveToPrevious();
       int prevminuteIndex = cc.getColumnIndex("minute");
        //int prevhourIndex = cc.getColumnIndex("hour");
       int prevminute = cc.getInt(prevminuteIndex);
       // int prevhour = cc.getInt(prevhourIndex);
        cc.moveToLast();
        int lastminuteIndex = cc.getColumnIndex("minute");
       // int lasthourIndex = cc.getColumnIndex("hour");
        int lastminute = cc.getInt(lastminuteIndex);
        //int lasthour = cc.getInt(lasthourIndex);
        cc.close();
       // String lashour = Integer.toString(lasthour);
       // String prehour = Integer.toString(prevhour);
        //time.setText(lashour +" "+ prehour);
            //String mi_db = Integer.toString(minn_db);
            // time.setText(mi_db);

        /*if(prevhour==1000){
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);

            startActivity(intent);
        }*/

       // int hourColIndex = cc.getColumnIndex("hour");
        //int minColIndex = cc.getColumnIndex("minute");


/*if(curmin<(lastminute+2)) {
    hrs_db = cc.getInt(hourColIndex);
    minn_db = cc.getInt(minColIndex);
    calendar.set(Calendar.MINUTE, minn_db);
    calendar.set(Calendar.HOUR_OF_DAY, hrs_db);
    cc.close();
    calendar.set(Calendar.MINUTE, minn_db + 2);
    calendar.set(Calendar.HOUR_OF_DAY, hrs);
//if(curmin<minn_db)
    mTimer = new Timer();
    MyTimerTask = new MyTimerTask();
    mTimer.schedule(MyTimerTask, calendar.getTime());
}*/


   // }//}
          //  if(curmin>minn_db){}

    /*else{
            ContentValues cvv = new ContentValues();


                            //Database

            cvv.put("hour", hrs);
            cvv.put("minute",curmin);
            d.insert("ttable", null, cvv);

           Cursor c = d.query("ttable", null, null, null, null, null, null);



            c.moveToFirst();

                int hourColIndex = c.getColumnIndex("hour");
                int minColIndex = c.getColumnIndex("minute");


               // String hrdb = c.getString(hourColIndex);
                //String min_db = c.getString(minColIndex);
                int hrs_db = c.getInt(hourColIndex);
                int minn_db = c.getInt(minColIndex);
                calendar.set(Calendar.MINUTE,minn_db);
                calendar.set(Calendar.HOUR_OF_DAY,hrs_db);
                c.close();
                calendar.set(Calendar.MINUTE,minn_db + 2);
                calendar.set(Calendar.HOUR_OF_DAY,hrs);


//String mi_db = Integer.toString(minn_db);
   // time.setText(mi_db);
}*/













            //mTimer.schedule(MyTimerTask, calendar.getTime());







    };





  /* class MyTimerTask extends TimerTask {

        @Override
        public void run() {



            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                   // penta.setEnabled(true);
                    penta.setEnabled(true);
                    time.setText("Можешь сканить");
                    //Intent intent = new Intent(MainActivity.this, AboutActivity.class);

                    //startActivity(intent);

                }
            });
        }
    }*/

    class DBBHelper extends SQLiteOpenHelper {

        public DBBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDBB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase d) {

            d.execSQL("create table ttable ("
                    + "id integer primary key autoincrement,"
                    + "minute integer,"
                    + "hour integer"
                    +  ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase d, int oldVersion, int newVersion) {

        }
    }
    final View.OnFocusChangeListener listener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) hideKeyboard();
        }
    };

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }
}