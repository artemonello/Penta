package io.exzorzio.pentascan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PracText extends AppCompatActivity {
    TextView prac_text;
    ImageView prac_im;
    ImageView backpot;
    ImageView crosspot;
    ImageView potimg;
    Button rise_easy;
    Button rise_med;
    Button rise_high;
    LinearLayout imgpot;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_prac);
       // final LinearLayout rl = (LinearLayout) findViewById(R.id.rl);
        prac_text= (TextView) findViewById(R.id.prac_text);
        prac_im = (ImageView) findViewById(R.id.prac_im);
        potimg = (ImageView) findViewById(R.id.potimg);
        backpot = (ImageView) findViewById(R.id.backpot);
        crosspot = (ImageView) findViewById(R.id.crosspot);
        rise_easy = (Button) findViewById(R.id.rise_esay);
        rise_med = (Button) findViewById(R.id.rise_med);
        rise_high = (Button) findViewById(R.id.rise_high);
        imgpot = (LinearLayout) findViewById(R.id.imgpot);
        Intent intent = getIntent();
        String num_prac = intent.getStringExtra("num_prac");
        if(num_prac.equals("first_easy")){
            rise_easy.setVisibility(View.VISIBLE);
            readFile(RISE1);
            rise_easy.setOnClickListener(new View.OnClickListener() {
                                             public void onClick(View v) {
                                                 potimg.setImageResource(R.drawable.fdich);
                                                 imgpot.setVisibility(View.VISIBLE);
                                                 read_and_writeFile(POTEN);
                                             }});

prac_text.setText(R.string.f_prac_fl);
        }
        else if(num_prac.equals("second_easy")){
            rise_easy.setVisibility(View.VISIBLE);
            readFile(RISE2);
            rise_easy.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    potimg.setImageResource(R.drawable.dich2);
                    imgpot.setVisibility(View.VISIBLE);
                    backpot.setVisibility(View.VISIBLE);
                    read_and_writeFile(POTEN);


                }});
            prac_text.setText(R.string.s_prac_fl);
        }
        else if(num_prac.equals("third_easy")){
            rise_easy.setVisibility(View.VISIBLE);
            readFile(RISE3);
            rise_easy.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    potimg.setImageResource(R.drawable.dich3);
                    imgpot.setVisibility(View.VISIBLE);
                    backpot.setVisibility(View.VISIBLE);
                    read_and_writeFile(POTEN);
                }});
            prac_text.setText(R.string.th_prac_fl);
        }
        else if(num_prac.equals("fourth_easy")){
            rise_easy.setVisibility(View.VISIBLE);
            readFile(RISE4);
            rise_easy.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    potimg.setImageResource(R.drawable.dich4);
                    imgpot.setVisibility(View.VISIBLE);
                    backpot.setVisibility(View.VISIBLE);
                    read_and_writeFile(POTEN);
                }});
            prac_text.setText(R.string.fth_prac_fl);
        }
        else if(num_prac.equals("fifth_easy")){
            rise_easy.setVisibility(View.VISIBLE);
            readFile(RISE5);
            rise_easy.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    potimg.setImageResource(R.drawable.dich5);
                    imgpot.setVisibility(View.VISIBLE);
                    backpot.setVisibility(View.VISIBLE);
                    read_and_writeFile(POTEN);
                }});
            prac_text.setText(R.string.fif_prac_fl);
        }
        else if(num_prac.equals("first_med")){
            rise_med.setVisibility(View.VISIBLE);
            readFile2(RISEM1);
            rise_med.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    potimg.setImageResource(R.drawable.dich6);
                    imgpot.setVisibility(View.VISIBLE);
                    backpot.setVisibility(View.VISIBLE);
                    read_and_writeFile2(POTEN);
                }});
            prac_text.setText(R.string.f_prac_sl);
        }
        else if(num_prac.equals("second_med")){
            prac_im.setVisibility(View.VISIBLE);
            prac_text.setText(R.string.s_prac_sl);
            readFile2(RISEM2);
            rise_med.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    potimg.setImageResource(R.drawable.dich7);
                    imgpot.setVisibility(View.VISIBLE);
                    backpot.setVisibility(View.VISIBLE);
                    read_and_writeFile2(POTEN);
                }});
           prac_im.setImageResource(R.drawable.totem);

        }
        else if(num_prac.equals("third_med")){
            rise_med.setVisibility(View.VISIBLE);
            readFile2(RISEM3);
            rise_med.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    potimg.setImageResource(R.drawable.dich8);
                    imgpot.setVisibility(View.VISIBLE);
                    backpot.setVisibility(View.VISIBLE);
                    read_and_writeFile2(POTEN);
                }});
            prac_text.setText(R.string.th_prac_sl);
        }
        else if(num_prac.equals("fourth_med")){
            rise_med.setVisibility(View.VISIBLE);
            prac_text.setText(R.string.fth_prac_sl);
            readFile2(RISEM4);
            rise_med.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    potimg.setImageResource(R.drawable.dich9);
                    imgpot.setVisibility(View.VISIBLE);
                    backpot.setVisibility(View.VISIBLE);
                    read_and_writeFile2(POTEN);
                }});
            prac_im.setImageResource(R.drawable.ress);
            prac_im.setVisibility(View.VISIBLE);
        }
        else if(num_prac.equals("fifth_med")){
            rise_med.setVisibility(View.VISIBLE);
            readFile2(RISEM5);
            rise_med.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    potimg.setImageResource(R.drawable.dich10);
                    imgpot.setVisibility(View.VISIBLE);
                    backpot.setVisibility(View.VISIBLE);
                    read_and_writeFile2(POTEN);
                }});
            prac_text.setText(R.string.fif_prac_sl);
        }
        else if(num_prac.equals("first_hard")){
            rise_high.setVisibility(View.VISIBLE);
            readFile3(RISEH1);
            rise_high.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    potimg.setImageResource(R.drawable.dich11);
                    imgpot.setVisibility(View.VISIBLE);
                    backpot.setVisibility(View.VISIBLE);
                    read_and_writeFile3(POTEN);
                }});
            prac_text.setText(R.string.f_prac_thl);
        }
        else if(num_prac.equals("second_hard")){
            rise_high.setVisibility(View.VISIBLE);
            readFile3(RISEH2);
            rise_high.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    potimg.setImageResource(R.drawable.dich12);
                    imgpot.setVisibility(View.VISIBLE);
                    backpot.setVisibility(View.VISIBLE);
                    read_and_writeFile3(POTEN);
                }});
            prac_text.setText(R.string.s_prac_thl);
        }
        else if(num_prac.equals("third_hard")){
            rise_high.setVisibility(View.VISIBLE);
            readFile3(RISEH3);
            rise_high.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    potimg.setImageResource(R.drawable.dich13);
                    imgpot.setVisibility(View.VISIBLE);
                    backpot.setVisibility(View.VISIBLE);
                    read_and_writeFile3(POTEN);
                }});
            prac_text.setText(R.string.th_prac_thl);
        }
        else if(num_prac.equals("fourth_hard")){
            rise_high.setVisibility(View.VISIBLE);
            readFile3(RISEH4);
            rise_high.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    potimg.setImageResource(R.drawable.dich14);
                    imgpot.setVisibility(View.VISIBLE);
                    backpot.setVisibility(View.VISIBLE);
                    read_and_writeFile3(POTEN);
                }});
            prac_text.setText(R.string.fth_prac_thl);
        }
        else if(num_prac.equals("fifth_hard")){
            rise_high.setVisibility(View.VISIBLE);
            readFile3(RISEH5);
            rise_high.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    potimg.setImageResource(R.drawable.dich15);
                    imgpot.setVisibility(View.VISIBLE);
                    backpot.setVisibility(View.VISIBLE);
                    read_and_writeFile3(POTEN);
                }});
            prac_text.setText(R.string.fif_prac_thl);


            prac_im.setImageResource(R.drawable.absolut);
            prac_im.setVisibility(View.VISIBLE);
        }


        crosspot.setOnClickListener(OnClickListener);
        backpot.setOnClickListener(OnClickListener1);
    }

    View.OnClickListener OnClickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            imgpot.setVisibility(View.INVISIBLE);
            backpot.setVisibility(View.INVISIBLE);
        }
    };
    View.OnClickListener OnClickListener1  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            imgpot.setVisibility(View.INVISIBLE);
        }
    };

    void writeFile(String poten,String file) {
        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(file, MODE_PRIVATE)));
            // пишем данные
            bw.write(poten);
            // закрываем поток
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void read_and_writeFile(String file) {

        try {

            BufferedReader brr = new BufferedReader(new InputStreamReader(
                    openFileInput(file)));
            String str = "";
            String  res="";
Integer res_pot;
            Integer res_potpl;

            while ((str = brr.readLine()) != null) {
                res = res + str;

            }

            res_pot = Integer.parseInt(res);
            res_potpl = res_pot +1;
    String ress = Integer.toString(res_potpl);
    if(res_potpl<201){
    writeFile(ress,file);}
            rise_easy.setVisibility(View.INVISIBLE);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
        }

    }
    void read_and_writeFile2(String file) {

        try {

            BufferedReader brr = new BufferedReader(new InputStreamReader(
                    openFileInput(file)));
            String str = "";
            String  res="";
            Integer res_pot;
            Integer res_potpl;

            while ((str = brr.readLine()) != null) {
                res = res + str;

            }

            res_pot = Integer.parseInt(res);
            res_potpl = res_pot +2;
            String ress = Integer.toString(res_potpl);
            writeFile(ress,file);
            rise_med.setVisibility(View.INVISIBLE);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    void read_and_writeFile3(String file) {

        try {

            BufferedReader brr = new BufferedReader(new InputStreamReader(
                    openFileInput(file)));
            String str = "";
            String  res="";
            Integer res_pot;
            Integer res_potpl;

            while ((str = brr.readLine()) != null) {
                res = res + str;

            }

            res_pot = Integer.parseInt(res);
            res_potpl = res_pot +3;
            String ress = Integer.toString(res_potpl);
            writeFile(ress,file);
            rise_high.setVisibility(View.INVISIBLE);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    void readFile(String file) {
        String pot ="1";
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(file)));
            String str = "";
            String  res="";

            // читаем содержимое
            while ((str = br.readLine()) != null) {
                res = res + str;
            }



                if(res.equals("")){ writeFile(pot,file);
                }
                else{rise_easy.setVisibility(View.INVISIBLE);}




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void readFile2(String file) {
        String pot ="1";
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(file)));
            String str = "";
            String  res="";

            // читаем содержимое
            while ((str = br.readLine()) != null) {
                res = res + str;
            }



            if(res.equals("")){ writeFile(pot,file);
            }

            else{rise_med.setVisibility(View.INVISIBLE);}




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void readFile3(String file) {
        String pot ="1";
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(file)));
            String str = "";
            String  res="";

            // читаем содержимое
            while ((str = br.readLine()) != null) {
                res = res + str;
            }



            if(res.equals("")){ writeFile(pot,file);
            }
            else{rise_high.setVisibility(View.INVISIBLE);}




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}