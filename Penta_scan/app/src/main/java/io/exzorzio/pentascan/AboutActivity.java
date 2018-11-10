package io.exzorzio.pentascan;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import android.util.Log;

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;



//import android.support.annotation.RequiresApi;


public class AboutActivity extends AppCompatActivity {
    final String FILENAME = "file";
  //  final String POTEN = "potentialpoko";
    final String FILENAME_FRIEND = "file_friend";
    static final int REQUEST_TAKE_PHOTO = 1;
    public String mCurrentPhotoPath;
    private Uri photoURI;
    private ImageView human;
    Button scan;
    Button scan2;
    Button scan3;
    //private Timer mTimer;
    //private MyTimerTask mMyTimerTask;

    TextView nname;
    EditText type_name;
    ImageView human_sec;
    LinearLayout name_lay;
    Button name_subm;
    Button name_subm2;
    final String LOG_TAG = "myLogs";
    //String name_per;

   // DBBHelper dbbHelper;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_interface);
        human = (ImageView) findViewById(R.id.human);
        human_sec = (ImageView) findViewById(R.id.human_sec);
        scan = (Button) findViewById(R.id.scan);
        scan2 = (Button) findViewById(R.id.scan2);
        scan3 = (Button) findViewById(R.id.scan3);
        name_subm = (Button) findViewById(R.id.name_subm);
        name_subm2 = (Button) findViewById(R.id.name_subm2);
        name_lay = (LinearLayout) findViewById(R.id.name_lay);

        nname = (TextView) findViewById(R.id.nname);
        type_name = (EditText) findViewById(R.id.type_name);
        scan.setOnClickListener(OnClickListener2);
        scan2.setOnClickListener(OnClickListener3);
        scan3.setOnClickListener(OnClickListener4);
        dispatchTakePictureIntent();
        name_lay.setVisibility(View.VISIBLE);
       // String kk="";
//writeFilepot(kk);



final String result ="";
       readFile(result);



        name_subm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String name_per = type_name.getText().toString();
                writeFile(name_per);
                name_lay.setVisibility(View.INVISIBLE);
                readFile(result);
            }
        });

        name_subm2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String name_per = type_name.getText().toString();
                writeFilefriend(name_per);
                name_lay.setVisibility(View.INVISIBLE);
                readFilefriend(result);
            }
        });
        type_name.setOnFocusChangeListener(listener);
        // name_subm.setOnClickListener(OnClickListener5);
      /*  dbbHelper = new DBBHelper(this);

        final ContentValues idd = new ContentValues();
        name_per = type_name.getText().toString();
        SQLiteDatabase d = dbbHelper.getWritableDatabase();
        Cursor cur = d.rawQuery("SELECT COUNT(*) FROM ttable", null);
        if (cur != null) {
            cur.moveToFirst();
            if (cur.getInt(0) == 0) {
                name_lay.setVisibility(View.VISIBLE);
                idd.put("name", "kk");
                name_subm.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        name_lay.setVisibility(View.INVISIBLE);
                    }
                });
            } else {
                // Cursor ccc = d.query("ttable", null, null, null, null, null, null);
                //ccc.moveToLast();
                name_lay.setVisibility(View.INVISIBLE);*/
           // }
      //  }
    }

    @Override


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {

            human.setImageURI(photoURI);

            try {
                File f = new File(mCurrentPhotoPath);
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
                human.setImageBitmap(bitmap);

            } catch (IOException e) {
                Log.w("TAG", "-- Error in setting image");
            } catch (OutOfMemoryError oom) {
                Log.w("TAG", "-- OOM Error in setting image");
            }


        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();

        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.provider",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

            }


        }
    }


    View.OnClickListener OnClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final View bar = findViewById(R.id.bar);
            final TextView bartext = findViewById(R.id.bartext);
            final Animation animation = AnimationUtils.loadAnimation(AboutActivity.this, R.anim.anim);
            scan.setBackgroundDrawable(getResources().getDrawable(R.drawable.mybutton2));
            scan.setEnabled(false);
            bartext.setText("Сканирую...");
            Context context = human.getContext();
            human.setColorFilter(ContextCompat.getColor(context, R.color.colorImg));
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    bar.setVisibility(View.GONE);
                    bartext.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });

            bar.setVisibility(View.VISIBLE);
            bar.startAnimation(animation);
            bartext.setVisibility(View.VISIBLE);
            bartext.startAnimation(animation);
           /* if (mTimer != null) {
                mTimer.cancel();
            }

            mTimer = new Timer();
            mMyTimerTask = new MyTimerTask();*/

            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            //"dd:MMMM:yyyy HH:mm:ss a", Locale.getDefault());
           /* Calendar calendar = Calendar.getInstance();

            int curmin = calendar.get(Calendar.MINUTE);
            int hrs = calendar.get(Calendar.HOUR_OF_DAY);
            String hrs_str = Integer.toString(hrs);

            calendar.set(Calendar.MINUTE,curmin + 2);
            int nextmin = curmin +2;
            String nextmin_str = Integer.toString(nextmin);
            scan_access.setText("Следующий скан доступен в "+hrs_str+":"+nextmin_str);*/

            //final String strDate = simpleDateFormat.format(calendar.getTime());
            // mTimer.schedule(mMyTimerTask,calendar.getTime());
            new CountDownTimer(8000, 1000) {

                public void onTick(long millisUntilFinished) {
                    // scan.setText("Осталось: " + millisUntilFinished / 1000+" сек");

                }

                public void onFinish() {
                    scan.setEnabled(true);
                    scan.setBackgroundDrawable(getResources().getDrawable(R.drawable.mybutton));
                }
            }.start();
            Handler handler1 = new Handler();
            String friend = "you";
            String name_per = type_name.getText().toString();
            final Intent intent1 = new Intent(AboutActivity.this, RegistrationFirst.class);
            intent1.putExtra("person", friend);
            intent1.putExtra("name_per",name_per);
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //String path=null;
                    //String uri =null;

                    String uris = photoURI.toString();
                    intent1.putExtra("path", mCurrentPhotoPath);
                    intent1.putExtra("uri", uris);

                    startActivity(intent1);
                }

            }, 7500);


        }


    };

    View.OnClickListener OnClickListener3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dispatchTakePictureIntent();

        ;

            new CountDownTimer(4000, 1000) {

                public void onTick(long millisUntilFinished) {
                    //scan.setText("Осталось: " + millisUntilFinished / 1000+" сек");
                }

                public void onFinish() {
                    name_lay.setVisibility(View.VISIBLE);
                    name_subm.setVisibility(View.INVISIBLE);
                    name_subm2.setVisibility(View.VISIBLE);
                    scan2.setVisibility(View.INVISIBLE);
                    scan3.setVisibility(View.VISIBLE);
                }
            }.start();

        }
    };
    View.OnClickListener OnClickListener4 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final View bar = findViewById(R.id.bar);
            final TextView bartext = findViewById(R.id.bartext);
            final Animation animation = AnimationUtils.loadAnimation(AboutActivity.this, R.anim.anim);
            scan3.setBackgroundDrawable(getResources().getDrawable(R.drawable.mybutton2));
            scan3.setEnabled(false);
            bartext.setText("Сканирую...");
            Context context = human.getContext();
            human.setColorFilter(ContextCompat.getColor(context, R.color.colorImg));
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    bar.setVisibility(View.GONE);
                    bartext.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });

            bar.setVisibility(View.VISIBLE);
            bar.startAnimation(animation);
            bartext.setVisibility(View.VISIBLE);
            bartext.startAnimation(animation);

           /* if (mTimer != null) {
                mTimer.cancel();
            }

            mTimer = new Timer();
            mMyTimerTask = new MyTimerTask();

            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            //"dd:MMMM:yyyy HH:mm:ss a", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();

            int curmin = calendar.get(Calendar.MINUTE);
            int hrs = calendar.get(Calendar.HOUR_OF_DAY);
            String hrs_str = Integer.toString(hrs);

            calendar.set(Calendar.MINUTE,curmin + 2);
            int nextmin = curmin +2;
            String nextmin_str = Integer.toString(nextmin);
            scan_access.setText("Следующий скан доступен в "+hrs_str+":"+nextmin_str);*/

            //final String strDate = simpleDateFormat.format(calendar.getTime());
            // mTimer.schedule(mMyTimerTask,calendar.getTime());
            new CountDownTimer(9000, 1000) {

                public void onTick(long millisUntilFinished) {
                    //scan.setText("Осталось: " + millisUntilFinished / 1000+" сек");
                }

                public void onFinish() {
                    scan2.setEnabled(true);
                    scan3.setBackgroundDrawable(getResources().getDrawable(R.drawable.mybutton3));
                    scan2.setVisibility(View.VISIBLE);
                    scan3.setVisibility(View.INVISIBLE);
                }
            }.start();

            Handler handler1 = new Handler();
            String name_per = type_name.getText().toString();
            String friend = "friend";
            final Intent intent1 = new Intent(AboutActivity.this, RegistrationFirst.class);
            intent1.putExtra("person", friend);
            intent1.putExtra("name_per", name_per);
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //String path=null;
                    //String uri =null;

                    String uris = photoURI.toString();
                    intent1.putExtra("path", mCurrentPhotoPath);
                    intent1.putExtra("uri", uris);

                    startActivity(intent1);
                }

            }, 7500);

        }
    };

    void writeFile(String name_p) {
        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)));
            // пишем данные
            bw.write(name_p);
            // закрываем поток
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeFilefriend(String name_p) {
        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME_FRIEND, MODE_PRIVATE)));
            // пишем данные
            bw.write(name_p);
            // закрываем поток
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFile(String res) {
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILENAME)));
            String str = "";
           res="";

            List<String> welcome_arr= Arrays.asList("Тьма ждёт тебя, ","Я взываю к тебе, ","Я соскучился, ","","Кто воплощение магии? - ","Сансара ждёт тебя, ","А твой потенциал хорош, ");
            Collections.shuffle(welcome_arr);
            String welcoming = welcome_arr.get(1);
            // читаем содержимое
            while ((str = br.readLine()) != null) {
              res = res + str;
                if (!res.equals("")) {
                    name_lay.setVisibility(View.INVISIBLE);
                }
            }
            nname.setText(welcoming + res);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void readFilefriend(String res) {
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILENAME_FRIEND)));
            String str = "";
            res="";
            List<String> welcome_arr= Arrays.asList("Тьма ждёт тебя, ","Я взываю к тебе, ","Я соскучился, ","","Кто воплощение магии? - ","Сансара ждёт тебя, ","А твой потенциал хорош, ");
            Collections.shuffle(welcome_arr);
            String welcoming = welcome_arr.get(1);
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                res = res + str;

            }
            nname.setText(welcoming + res);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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

    /*  View.OnClickListener OnClickListener5  = new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String name_per = type_name.getText().toString();
              ContentValues idd = new ContentValues();

              SQLiteDatabase d = dbbHelper.getWritableDatabase();
  idd.put("name",name_per);


          }
      };*/
   /* class DBBHelper extends SQLiteOpenHelper {

        public DBBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDBB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase d) {

            d.execSQL("create table ttable ("
                    + "id integer primary key autoincrement,"
                    + "name string"
                    + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase d, int oldVersion, int newVersion) {

        }
    }*/
   /* class MyTimerTask extends TimerTask {

        @Override
        public void run() {



            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Calendar calendar = Calendar.getInstance();
                    int hrs = calendar.get(Calendar.HOUR);
                    String hrs_str = Integer.toString(hrs);
                    scan.setBackgroundDrawable(getResources().getDrawable(R.drawable.mybutton));
                    scan.setText("Сканить");
                    scan_access.setText(" ");
                    scan.setEnabled(true);
                }
            });
        }
    }*/

}
