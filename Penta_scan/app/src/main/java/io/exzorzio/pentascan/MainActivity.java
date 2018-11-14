package io.exzorzio.pentascan;
import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private static final int PERMISSION_REQUEST_CODE = 123;
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
        tomaterial.setOnClickListener(OnClickListener4);
        penta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasPermissions()){
                    CoolFunc();

                }
                else {
                    requestPermissionWithRationale();
                }
            }
        });
    }

    void CoolFunc(){
        ContentValues idd = new ContentValues();
        SQLiteDatabase d = dbbHelper.getWritableDatabase();
        Calendar calendar = Calendar.getInstance();
        int curmin = calendar.get(Calendar.MINUTE);
        int hrs = calendar.get(Calendar.HOUR_OF_DAY);
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        String curdaywrite = Integer.toString(day);
        String curminwrite = Integer.toString(curmin);
        String curhourwrite=Integer.toString(hrs);
        idd.put("minute", curmin);
        long rowID = d.insert("ttable", null, idd);
        d.insert("mytable", null, idd);
        String checker = Long.toString(rowID);
        int ro = Integer.parseInt(checker);
        if(ro>1){
            readFile(FILENAME,curmin,hrs,day);
        }
        else{
            writeFile(curhourwrite,curminwrite,curdaywrite,FILENAME);
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }

        dbbHelper.close();

    }

    private boolean hasPermissions(){
        int res = 0;
        //string array of permissions,
        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};

        for (String perms : permissions){
            res = checkCallingOrSelfPermission(perms);
            if (!(res == PackageManager.PERMISSION_GRANTED)){
                return false;
            }
        }
        return true;
    }

    private void requestPerms(){
        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(permissions,PERMISSION_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean allowed = true;

        switch (requestCode){
            case PERMISSION_REQUEST_CODE:

                for (int res : grantResults){
                    // if user granted all permissions.
                    allowed = allowed && (res == PackageManager.PERMISSION_GRANTED);
                }

                break;
            default:
                // if user not granted permissions.
                allowed = false;
                break;
        }

        if (allowed){
            //user granted all permissions we can perform our task.
            CoolFunc();
        }
        else {
            // we will give warning to user that they haven't granted permissions.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)||shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                    Toast.makeText(this, "Storage and Camera Permissions denied.", Toast.LENGTH_SHORT).show();

                } else {
                    showNoStoragePermissionSnackbar();
                }
            }
        }

    }


    public void showNoStoragePermissionSnackbar() {
        Snackbar.make(MainActivity.this.findViewById(R.id.activity_view), "Storage and camera permission isn't granted" , Snackbar.LENGTH_LONG)
                .setAction("SETTINGS", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openApplicationSettings();

                        Toast.makeText(getApplicationContext(),
                                "Open Permissions and grant the Storage permission",
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .show();
    }

    public void openApplicationSettings() {
        Intent appSettingsIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(appSettingsIntent, PERMISSION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            CoolFunc();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void requestPermissionWithRationale() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            final String message = "Storage and camera permission is needed to show files count";
            Snackbar.make(MainActivity.this.findViewById(R.id.activity_view), message, Snackbar.LENGTH_LONG)
                    .setAction("GRANT", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            requestPerms();
                        }
                    })
                    .show();
        } else {
            requestPerms();
        }
    }




    View.OnClickListener OnClickListener4  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, Practise.class);

            startActivity(intent);
        }
    };

  void writeFile(String hour,String min,String day,String file) {
      try {

          BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                  openFileOutput(file, MODE_PRIVATE)));
          bw.write(hour+":"+min+":"+day);
          bw.close();

      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  void readFile(String file,int min,int h,int day) {

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
          int day_prev = Integer.parseInt(subStr[2]);
          int mindiv = min - min_prev;
if(h_prev==23&&min_prev==55){
    if(day>day_prev){
        tomaterial.setVisibility(View.INVISIBLE);
        String mincur = Integer.toString(min);
        String hcur = Integer.toString(h);
        String daycur = Integer.toString(day);
        writeFile(hcur,mincur,daycur,FILENAME);
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }
    else{
        tomaterial.setVisibility(View.VISIBLE);
        alert.setVisibility(View.VISIBLE);
    }
}


if(day>day_prev){
    tomaterial.setVisibility(View.INVISIBLE);
    String mincur = Integer.toString(min);
    String hcur = Integer.toString(h);
    String daycur = Integer.toString(day);
    writeFile(hcur,mincur,daycur,FILENAME);
    Intent intent = new Intent(MainActivity.this, AboutActivity.class);
    startActivity(intent);
}
          if(min_prev==55){
              if(h>h_prev){
                  tomaterial.setVisibility(View.INVISIBLE);
                  String mincur = Integer.toString(min);
                  String hcur = Integer.toString(h);
                  String daycur = Integer.toString(day);
                  writeFile(hcur,mincur,daycur,FILENAME);
                  Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                  startActivity(intent);
              }
              else{
                  tomaterial.setVisibility(View.VISIBLE);
                  alert.setVisibility(View.VISIBLE);
              }
          }
          if(min_prev==56){

              if(h-h_prev==1&&h>h_prev&&min>=1){
                  tomaterial.setVisibility(View.INVISIBLE);
                  String mincur = Integer.toString(min);
                  String hcur = Integer.toString(h);
                  String daycur = Integer.toString(day);
                  writeFile(hcur,mincur,daycur,FILENAME);
                  Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                  startActivity(intent);
              }
              else if(h-h_prev>1){
                  tomaterial.setVisibility(View.INVISIBLE);
                  String mincur = Integer.toString(min);
                  String hcur = Integer.toString(h);
                  String daycur = Integer.toString(day);
                  writeFile(hcur,mincur,daycur,FILENAME);
                  Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                  startActivity(intent);
              }
              else{
                  tomaterial.setVisibility(View.VISIBLE);
                  alert.setVisibility(View.VISIBLE);
              }
          }
          if(min_prev==57){
              if(h-h_prev==1&&h>h_prev&&min>=2){
                  tomaterial.setVisibility(View.INVISIBLE);
                  String mincur = Integer.toString(min);
                  String hcur = Integer.toString(h);
                  String daycur = Integer.toString(day);
                  writeFile(hcur,mincur,daycur,FILENAME);
                  Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                  startActivity(intent);
              }
              else if(h-h_prev>1){
                  tomaterial.setVisibility(View.INVISIBLE);
                  String mincur = Integer.toString(min);
                  String hcur = Integer.toString(h);
                  String daycur = Integer.toString(day);
                  writeFile(hcur,mincur,daycur,FILENAME);
                  Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                  startActivity(intent);
              }
              else{
                  tomaterial.setVisibility(View.VISIBLE);
                  alert.setVisibility(View.VISIBLE);
              }
          }
          if(min_prev==58){
              if(h-h_prev==1&&h>h_prev&&min>=3){
                  tomaterial.setVisibility(View.INVISIBLE);
                  String mincur = Integer.toString(min);
                  String hcur = Integer.toString(h);
                  String daycur = Integer.toString(day);
                  writeFile(hcur,mincur,daycur,FILENAME);
                  Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                  startActivity(intent);
              }
              else if(h-h_prev>1){
                  tomaterial.setVisibility(View.INVISIBLE);
                  String mincur = Integer.toString(min);
                  String hcur = Integer.toString(h);
                  String daycur = Integer.toString(day);
                  writeFile(hcur,mincur,daycur,FILENAME);
                  Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                  startActivity(intent);
              }
              else{
                  tomaterial.setVisibility(View.VISIBLE);
                  alert.setVisibility(View.VISIBLE);
              }
          }
          if(min_prev==59){
              if(h-h_prev==1&&h>h_prev&&min>=4){
                  tomaterial.setVisibility(View.INVISIBLE);
                  String mincur = Integer.toString(min);
                  String hcur = Integer.toString(h);
                  String daycur = Integer.toString(day);
                  writeFile(hcur,mincur,daycur,FILENAME);
                  Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                  startActivity(intent);
              }
              else if(h-h_prev>1){
                  tomaterial.setVisibility(View.INVISIBLE);
                  String mincur = Integer.toString(min);
                  String hcur = Integer.toString(h);
                  String daycur = Integer.toString(day);
                  writeFile(hcur,mincur,daycur,FILENAME);
                  Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                  startActivity(intent);
              }
              else{
                  tomaterial.setVisibility(View.VISIBLE);
                  alert.setVisibility(View.VISIBLE);
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
             String daycur = Integer.toString(day);
             writeFile(hcur,mincur,daycur,FILENAME);
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
}