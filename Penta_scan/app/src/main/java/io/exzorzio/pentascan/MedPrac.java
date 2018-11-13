package io.exzorzio.pentascan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MedPrac extends AppCompatActivity {
    String num_prac;
    Button first_med;
    Button second_med;
    Button third_med;
    Button fourth_med;
    Button fifth_med;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medprac);
        first_med = (Button) findViewById(R.id.first_med);
        second_med = (Button) findViewById(R.id.second_med);
        third_med = (Button) findViewById(R.id.third_med);
        fourth_med = (Button) findViewById(R.id.fourth_med);
        fifth_med = (Button) findViewById(R.id.fifth_med);
        first_med.setOnClickListener(OnClickListener1);
        second_med.setOnClickListener(OnClickListener2);
        third_med.setOnClickListener(OnClickListener3);
        fourth_med.setOnClickListener(OnClickListener4);
        fifth_med.setOnClickListener(OnClickListener5);
    }
    View.OnClickListener OnClickListener1  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num_prac ="first_med";
            Intent intent = new Intent(MedPrac.this, PracText.class);
            intent.putExtra("num_prac",num_prac);
            startActivity(intent);

        }

    };
    View.OnClickListener OnClickListener2  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num_prac ="second_med";
            Intent intent = new Intent(MedPrac.this, PracText.class);
            intent.putExtra("num_prac",num_prac);
            startActivity(intent);

        }

    };
    View.OnClickListener OnClickListener3  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num_prac ="third_med";
            Intent intent = new Intent(MedPrac.this, PracText.class);
            intent.putExtra("num_prac",num_prac);
            startActivity(intent);

        }

    };
    View.OnClickListener OnClickListener4  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num_prac ="fourth_med";
            Intent intent = new Intent(MedPrac.this, PracText.class);
            intent.putExtra("num_prac",num_prac);
            startActivity(intent);

        }

    };
    View.OnClickListener OnClickListener5  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num_prac ="fifth_med";
            Intent intent = new Intent(MedPrac.this, PracText.class);
            intent.putExtra("num_prac",num_prac);
            startActivity(intent);

        }

    };
}