package io.exzorzio.pentascan;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity{
    TextView alert;
    ImageView penta;
    Button tomaterial;
 DBBHelper dbbHelper;
    final String FILENAME = "time_info";
    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        penta = (ImageView) findViewById(R.id.penta);
        tomaterial = (Button) findViewById(R.id.tomaterial);
        alert = (TextView) findViewById(R.id.alert);
        final Animation a = AnimationUtils.loadAnimation(MainActivity.this,
                R.anim.rotate);
        a.setDuration(8000);
        penta.startAnimation(a);
        dbbHelper = new DBBHelper(this);
        penta.setOnClickListener(OnClickListener1);
        tomaterial.setOnClickListener(OnClickListener4);
    }
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
          int[] m_prev={55,56,57,58,59};
          int[] min_now={0,1,2,3,4};
for(int i=0;i<5;i++){
    if(min_prev==m_prev[i]){
        if(h>h_prev&&min>=min_now[i]){
            String mincur = Integer.toString(min);
            String hcur = Integer.toString(h);
            writeFile(hcur,mincur,FILENAME);
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            break;
        }
        else{
            tomaterial.setVisibility(View.VISIBLE);
            alert.setVisibility(View.VISIBLE);
        }
    }
}
         if(mindiv<5){
           tomaterial.setVisibility(View.VISIBLE);
alert.setVisibility(View.VISIBLE);
          }
          else{
             tomaterial.setVisibility(View.INVISIBLE);
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