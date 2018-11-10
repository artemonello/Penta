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

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;



public class MainActivity extends AppCompatActivity{

   TextView helptxt;
    TextView alert;
    ImageView penta;
    ImageView backgod;
    ImageView godpick;
    Button pashalka;
    Button check_god;
    Button tomaterial;
LinearLayout godent;
    TextInputEditText type_god;
 DBBHelper dbbHelper;
    final String FILENAME = "time_info";
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
        tomaterial = (Button) findViewById(R.id.tomaterial);
        godent = (LinearLayout) findViewById(R.id.godent);
        type_god = (TextInputEditText) findViewById(R.id.type_god);
        //time = (TextView) findViewById(R.id.time);
        helptxt = (TextView) findViewById(R.id.helptxt);
        alert = (TextView) findViewById(R.id.alert);
        final Animation a = AnimationUtils.loadAnimation(MainActivity.this,
                R.anim.rotate);
        a.setDuration(8000);
        penta.startAnimation(a);
        dbbHelper = new DBBHelper(this);
        penta.setOnClickListener(OnClickListener1);
pashalka.setOnClickListener(OnClickListener2);
        backgod.setOnClickListener(OnClickListener3);
        tomaterial.setOnClickListener(OnClickListener4);

        check_god.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                 String name_god = type_god.getText().toString();
                 String appropriate_vars[] ={"Odin","Odinn","odin","odinn","Один","Одинн","один","одинн"};

                 for(int i=0;i<appropriate_vars.length;i++){
                     if(name_god.equals(appropriate_vars[i])){
                         godpick.setImageResource(R.drawable.dich35);
                         helptxt.setText("Твоя наград");
                         break;
                     }
                     else{
                         helptxt.setText("Подумай лучше");
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

           Calendar calendar = Calendar.getInstance();

            int curmin = calendar.get(Calendar.MINUTE);
            int hrs = calendar.get(Calendar.HOUR_OF_DAY);
            String curminwrite = Integer.toString(curmin);
            String curhourwrite=Integer.toString(hrs);
            idd.put("minute", curmin);
            long rowID = d.insert("ttable", null, idd);
            d.insert("mytable", null, idd);
            String checker = Long.toString(rowID);
            int ro = Integer.parseInt(checker);
            if(ro>1){
                readFile(FILENAME,curmin,hrs);

            }
            else{
                writeFile(curhourwrite,curminwrite,FILENAME);
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);

                startActivity(intent);
            }
            dbbHelper.close();

}

    };
    View.OnClickListener OnClickListener4  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, Practise.class);

            startActivity(intent);
        }
    };

  void writeFile(String hour,String min,String file) {
      try {

          BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                  openFileOutput(file, MODE_PRIVATE)));
          bw.write(hour+":"+min);
          bw.close();

      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  void readFile(String file,int min,int h) {

      try {
          BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(file)));
          String str = "";
          String  res="";
          while ((str = br.readLine()) != null) {
              res = res + str;
          }
          String[] subStr;
          subStr = res.split(":");
          int min_prev = Integer.parseInt(subStr[1]);
          int h_prev = Integer.parseInt(subStr[0]);
          int mindiv = min - min_prev;


       if(min_prev==55){
              if(h>h_prev){
                  String mincur = Integer.toString(min);
                  String hcur = Integer.toString(h);
                  writeFile(hcur,mincur,FILENAME);
                  Intent intent = new Intent(MainActivity.this, AboutActivity.class);

                  startActivity(intent);
              }
              else{
                  tomaterial.setVisibility(View.VISIBLE);
                  alert.setVisibility(View.VISIBLE);
              }
          }
          else if(min_prev==56){
              if(h>h_prev&&min>=1){
                  String mincur = Integer.toString(min);
                  String hcur = Integer.toString(h);
                  writeFile(hcur,mincur,FILENAME);
                  Intent intent = new Intent(MainActivity.this, AboutActivity.class);

                  startActivity(intent);
              }
              else{
                  tomaterial.setVisibility(View.VISIBLE);
                  alert.setVisibility(View.VISIBLE);
              }
          }
          else if(min_prev==57){
              if(h>h_prev&&min>=2){
                  helptxt.setText("huinie");
                  String mincur = Integer.toString(min);
                  String hcur = Integer.toString(h);
                  writeFile(hcur,mincur,FILENAME);
                  Intent intent = new Intent(MainActivity.this, AboutActivity.class);

                  startActivity(intent);
              }
              else{
                  tomaterial.setVisibility(View.VISIBLE);
                  alert.setVisibility(View.VISIBLE);
              }
          }
          else if(min_prev==58){
              if(h>h_prev&&min>=3){
                  String mincur = Integer.toString(min);
                  String hcur = Integer.toString(h);
                  writeFile(hcur,mincur,FILENAME);
                  Intent intent = new Intent(MainActivity.this, AboutActivity.class);

                  startActivity(intent);
              }
              else{
                  tomaterial.setVisibility(View.VISIBLE);
                  alert.setVisibility(View.VISIBLE);
              }
          }
          else if(min_prev==59){
              if(h>h_prev&&min>=4){
                  String mincur = Integer.toString(min);
                  String hcur = Integer.toString(h);
                  writeFile(hcur,mincur,FILENAME);
                  Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                  startActivity(intent);
              }
              else{
                  tomaterial.setVisibility(View.VISIBLE);
                  alert.setVisibility(View.VISIBLE);
              }
          }
          else if(mindiv<5){
           tomaterial.setVisibility(View.VISIBLE);
alert.setVisibility(View.VISIBLE);
          }
          else{
              String mincur = Integer.toString(min);
              String hcur = Integer.toString(h);
writeFile(hcur,mincur,FILENAME);
              Intent intent = new Intent(MainActivity.this, AboutActivity.class);

              startActivity(intent);
          }


      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
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