package io.exzorzio.pentascan;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
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
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AboutActivity extends AppCompatActivity {
    final String FILENAME = "file";
    final String FILENAME_FRIEND = "file_friend";
    static final int REQUEST_TAKE_PHOTO = 1;
    public String mCurrentPhotoPath;
    private Uri photoURI;
    private ImageView human;
    Button scan;
    Button scan2;
    Button scan3;
    ImageView backgod;
    TextView helptxt;
    TextInputEditText type_god;
    ImageView godpick;
    Button pashalka;
    Button check_god;
    LinearLayout godent;
    TextView nname;
    EditText type_name;
    ImageView human_sec;
    LinearLayout name_lay;
    Button name_subm;
    Button name_subm2;
    final String LOG_TAG = "myLogs";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_interface);
        human = (ImageView) findViewById(R.id.human);
        human_sec = (ImageView) findViewById(R.id.human_sec);
        scan = (Button) findViewById(R.id.scan);
        scan2 = (Button) findViewById(R.id.scan2);
        type_god = (TextInputEditText) findViewById(R.id.type_god);
        scan3 = (Button) findViewById(R.id.scan3);
        name_subm = (Button) findViewById(R.id.name_subm);
        name_subm2 = (Button) findViewById(R.id.name_subm2);
        name_lay = (LinearLayout) findViewById(R.id.name_lay);
        backgod = (ImageView) findViewById(R.id.backgod);
        godpick = (ImageView) findViewById(R.id.godpick);
        pashalka = (Button) findViewById(R.id.pashalka);
        check_god = (Button) findViewById(R.id.check_god);
        helptxt = (TextView) findViewById(R.id.helptxt);
        godent = (LinearLayout) findViewById(R.id.godent);
        nname = (TextView) findViewById(R.id.nname);
        type_name = (EditText) findViewById(R.id.type_name);
        scan.setOnClickListener(OnClickListener2);
        scan2.setOnClickListener(OnClickListener3);
        scan3.setOnClickListener(OnClickListener4);
        dispatchTakePictureIntent();
        name_lay.setVisibility(View.VISIBLE);
        pashalka.setOnClickListener(OnClickListener6);
        backgod.setOnClickListener(OnClickListener5);
        check_god.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String name_god = type_god.getText().toString();
                String appropriate_vars[] ={"Odin","Odinn","odin","odinn","Один","Одинн","один","одинн"};
                if(name_god.equals(appropriate_vars[0])||name_god.equals(appropriate_vars[1])||
                        name_god.equals(appropriate_vars[2])||name_god.equals(appropriate_vars[3])
                        ||name_god.equals(appropriate_vars[4])||name_god.equals(appropriate_vars[5])
                        ||name_god.equals(appropriate_vars[6])||name_god.equals(appropriate_vars[7])){
                    godpick.setImageResource(R.drawable.dich35);
                    helptxt.setText("Твоя награда");

                }
                else{
                    helptxt.setText("Подумай лучше");
                }

            }});
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
        type_god.setOnFocusChangeListener(listener);
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
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        mCurrentPhotoPath = image.getAbsolutePath();

        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            }
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

            new CountDownTimer(8000, 1000) {

                public void onTick(long millisUntilFinished) {
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
            new CountDownTimer(4000, 1000) {

                public void onTick(long millisUntilFinished) {
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

    View.OnClickListener OnClickListener5  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            godent.setVisibility(View.INVISIBLE);
            backgod.setVisibility(View.INVISIBLE);
        }};
    View.OnClickListener OnClickListener6  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            godent.setVisibility(View.VISIBLE);
            backgod.setVisibility(View.VISIBLE);
        }};
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

            new CountDownTimer(9000, 1000) {
                public void onTick(long millisUntilFinished) {
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
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)));
            bw.write(name_p);
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeFilefriend(String name_p) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME_FRIEND, MODE_PRIVATE)));
            bw.write(name_p);
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFile(String res) {
        try {
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
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILENAME_FRIEND)));
            String str = "";
            res="";
            List<String> welcome_arr= Arrays.asList("Тьма ждёт тебя, ","Я взываю к тебе, ","Я соскучился, ","","Кто воплощение магии? - ","Сансара ждёт тебя, ","А твой потенциал хорош, ");
            Collections.shuffle(welcome_arr);
            String welcoming = welcome_arr.get(1);
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
}