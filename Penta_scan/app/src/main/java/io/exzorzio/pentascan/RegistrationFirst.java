package io.exzorzio.pentascan;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RegistrationFirst extends AppCompatActivity{
   TextView item1;
    TextView item2;
    TextView item;
    TextView time_prac;
    TextView part_day;
    ImageView fon_day;
    DBHelper dbHelper;
    ImageView human_sec;
    ImageView redpill;
    ImageView bluepill;
    ImageView back;
    TextView name_person;
    LinearLayout alerto;
    final String FILENAME = "file";
    final String FILENAMEFRIEND = "file_friend";
    final String POTEN = "potentialpoko";
    final String RISE1 = "rise1";
    final String RISE2 = "rise2";
    final String RISE3 = "rise3";
    final String RISE4 = "rise4";
    final String RISE5 = "rise5";
    final String RISEM1 = "risem1";
    final String RISEM2 = "risem2";
    final String RISEM3 = "risem3";
    final String RISEM4 = "risem4";
    final String RISEM5 = "risem5";
    final String RISEH1 = "riseh1";
    final String RISEH2 = "riseh2";
    final String RISEH3 = "riseh3";
    final String RISEH4 = "riseh4";
    final String RISEH5 = "riseh5";
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.regfirst);

        item1 = (TextView) findViewById(R.id.item1);
        item2 = (TextView) findViewById(R.id.item2);
        item = (TextView) findViewById(R.id.item);
        time_prac = (TextView) findViewById(R.id.time_prac);
        part_day = (TextView) findViewById(R.id.part_day);
        name_person = (TextView) findViewById(R.id.name_person);
        fon_day = (ImageView) findViewById(R.id.fon_day);
        human_sec = (ImageView) findViewById(R.id.human_sec);
        redpill = (ImageView) findViewById(R.id.redpill);
        bluepill = (ImageView) findViewById(R.id.bluepill);
        back = (ImageView) findViewById(R.id.back);
        alerto = (LinearLayout) findViewById(R.id.alerto);
        Intent intent1 = getIntent();

        String path = intent1.getStringExtra("path");
        String uri = intent1.getStringExtra("uri");
        Uri myUri = Uri.parse(uri);


        new CountDownTimer(7000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                alerto.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);

            }
        }.start();
        human_sec.setImageURI(myUri);
      dbHelper = new DBHelper(this);
        redpill.setOnClickListener(OnClickListener1);
        bluepill.setOnClickListener(OnClickListener3);
        back.setOnClickListener(OnClickListener2);
        try {
            File f = new File(path);
            ExifInterface exif = new ExifInterface(f.getPath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            int angle = 0;

            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                angle = 90;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                angle = 180;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                angle = 270;
            }

            Matrix mat = new Matrix();
            mat.postRotate(angle);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bmp = BitmapFactory.decodeStream(new FileInputStream(f),
                    null, options);
            Bitmap bitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
                    bmp.getHeight(), mat, true);
            ByteArrayOutputStream outstudentstreamOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100,
                    outstudentstreamOutputStream);
            human_sec.setImageBitmap(bitmap);

        } catch (IOException e) {
        } catch (OutOfMemoryError oom) {
        }

        String[] s = new String[92];

        List<String> pr = new ArrayList<String>();
        int lo;
        String p;
        for(lo=0;lo<92;lo++){
           p =Integer.toString(lo);
            if(lo==91){s[lo]="99";}
            else{
            s[lo]=p;}
        }

       for(int j=1;j<92;j++){
            if(j<=40){
for(int k=0;k<2;k++){
           pr.add(s[j]);
            }
            }
            if(j>40&&j<=69){
                for(int k=0;k<40;k++){
                    pr.add(s[j]);}
            }
            if(j>69&&j<=90){
                for(int k=0;k<35;k++){
                    pr.add(s[j]);}
            }
            if(j==91){
                for(int k=0;k<5;k++){
                    pr.add(s[j]);}
            }
           }

        String ss[]= {"Жрица","Ритуальный маг","Чернокнижие европейское","Чернокнижие русское",
                "Викка","Таро","Энергетика","Северная традиция","Некромаг","Характерник","Оракул",
                "Медиум","Техномаг","Травничество","Ведьма","Вуду","Демонология","Эклектика","Экстрасенс",
                "Целитель","Стихийная магия","Природная магия","Шаманизм","Друидизм","Каббала",
                "Симпатическая магия"};
        List<String> sss = new ArrayList<String>();
        List<String> ssss= Arrays.asList("Посох","Шест",
                "Атам",
                "Таро",
                "Руны",
                "Зачарованный песок",
                "Бубен",
                "Маятник",
                "Клинок криц",
                "Гримуар",
                "Чаша",
                "Хрустальный Шар",
                "Котел",
                "Метла",
                "Пентаграмма",
                "Магическое-тату",
                "Фамильяр",
                "Череп",
                "Эспекорус");
        int b = (int) (Math.random()*23);
        int c = (int) (Math.random()*59);
        int hour = b;
        int min = c;
        String hour_str = Integer.toString(hour);
        String min_str = Integer.toString(min);
        Intent intent = getIntent();
        String person = intent.getStringExtra("person");


        if(hour>=0&&hour<10){
            time_prac.setText("0"+hour_str+":"+min_str);
        }
        if(min>=0&&min<10){
            time_prac.setText(hour_str+":0"+min_str);
        }
        if(hour>=0&&hour<10&&min>=0&&min<10){
            time_prac.setText("0"+hour_str+":0"+min_str);
        }
        else{
            time_prac.setText(hour_str+":"+min_str);
        }

        if(hour>=0&&hour<=4||hour==23){
            part_day.setText("Ночь");
            fon_day.setBackgroundResource(R.drawable.night);
        }
        if(hour>4&&hour<=12){
            part_day.setText("Утро");
            fon_day.setBackgroundResource(R.drawable.morning);
        }
        if(hour>12&&hour<=16){
            part_day.setText("День");
            fon_day.setBackgroundResource(R.drawable.day);
        }
        if(hour>16&&hour<23){
            part_day.setText("Вечер");
            fon_day.setBackgroundResource(R.drawable.evening);
        }


        for(int i=0;i<26;i++){
            if(i==0||i==1||i>=5&&i<=11||i==13||i>=15&&i<=23||i==25){
                for(int j=0;j<5;j++){
                sss.add(ss[i]);
                }
            }
            if(i==14){
                for(int j=0;j<4;j++){
                    sss.add(ss[i]);
                }
            }
            if(i==2){
                for(int j=0;j<3;j++){
                    sss.add(ss[i]);}
            }

            if(i==4||i==12||i==17) {
                    sss.add(ss[i]);
            }
            if(i==3){
                for(int j=0;j<7;j++){
                    sss.add(ss[i]);
                }
            }
            if(i==24){
                for(int j=0;j<6;j++){
                    sss.add(ss[i]);
                }
            }
            if(i==16){
                for(int j=0;j<10;j++){
                    sss.add(ss[i]);
                }
            }


        }
        for(int i =0;i<3;i++){
          Collections.shuffle(pr);
            Collections.shuffle(sss);
            Collections.shuffle(ssss);

        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("potential", sss.get(1));
        cv.put("disposition", pr.get(1));
        cv.put("tool", ssss.get(1));
        long rowID = db.insert("mytable", null, cv);
        db.insert("mytable", null, cv);

        Cursor cc = db.query("mytable", null, null, null, null, null, null);

        cc.moveToFirst();
        int potentialColIndex = cc.getColumnIndex("potential");
        int dispositionColIndex = cc.getColumnIndex("disposition");
        int toolColIndex = cc.getColumnIndex("tool");
        Cursor ccc = db.query("mytable", null, null, null, null, null, null);
        ccc.moveToLast();
        int potentialfIndex = ccc.getColumnIndex("potential");
        int dispositionfIndex = ccc.getColumnIndex("disposition");
        int toolfIndex = ccc.getColumnIndex("tool");


        if(person.equals("you")){
            readFilepot(FILENAME);

            item.setText(cc.getString(dispositionfIndex));
            item2.setText(cc.getString(toolfIndex));
                item1.setText(cc.getString(potentialfIndex));
                cc.close();


        }
        else if(person.equals("friend")){
        item1.setText(ccc.getString(potentialColIndex));
        item.setText(ccc.getString(dispositionColIndex));
        item2.setText(ccc.getString(toolColIndex));
            ccc.close();
            readFilepot(FILENAMEFRIEND);
        }

        String iid = Long.toString(rowID);
       int ro = Integer.parseInt(iid);
      if(ro>1){

          readFilepot(POTEN);
      }
      else{
          String chk = "";
          writeFile(chk,POTEN);
          readFilepot(POTEN);
          writeFile(chk,RISE1);
          writeFile(chk,RISE2);
          writeFile(chk,RISE3);
          writeFile(chk,RISE4);
          writeFile(chk,RISE5);
          writeFile(chk,RISEM1);
          writeFile(chk,RISEM2);
          writeFile(chk,RISEM3);
          writeFile(chk,RISEM4);
          writeFile(chk,RISEM5);
          writeFile(chk,RISEH1);
          writeFile(chk,RISEH2);
          writeFile(chk,RISEH3);
          writeFile(chk,RISEH4);
          writeFile(chk,RISEH5);
      }

            dbHelper.close();
    }

    View.OnClickListener OnClickListener1  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(RegistrationFirst.this, Practise.class);
            startActivity(intent);

        }

    };

    View.OnClickListener OnClickListener2  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            alerto.setVisibility(View.INVISIBLE);
            back.setVisibility(View.INVISIBLE);
        }

    };
    View.OnClickListener OnClickListener3  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent browserIntent = new
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfqbDSTf2f3PiCiY7cW7LcqfPQ6oyEL_ov4yyhH_tltgPY19w/viewform"));
            startActivity(browserIntent);
        }

    };


    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "potential text,"
                    + "disposition text,"
                    + "tool text" + ");");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    void writeFile(String poten,String file) {
        try {

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(file, MODE_PRIVATE)));
            bw.write(poten);
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFilepot(String file) {
        String pot = item.getText().toString();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(file)));
            String str = "";
            String  res="";
            while ((str = br.readLine()) != null) {
                res = res + str;
            }
            if(file.equals("potentialpoko")){

                if(res.equals("")){ writeFile(pot,POTEN);
                }
                else{item.setText(res);}

            }
            else{name_person.setText(res);}


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
