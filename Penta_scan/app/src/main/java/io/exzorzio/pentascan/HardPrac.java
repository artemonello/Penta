package io.exzorzio.pentascan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HardPrac extends AppCompatActivity {
    String num_prac;
    Button first_hard;
    Button second_hard;
    Button third_hard;
    Button fourth_hard;
    Button fifth_hard;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hardprac);
        first_hard = (Button) findViewById(R.id.first_hard);
        second_hard = (Button) findViewById(R.id.second_hard);
        third_hard = (Button) findViewById(R.id.third_hard);
        fourth_hard = (Button) findViewById(R.id.fourth_hard);
        fifth_hard = (Button) findViewById(R.id.fifth_hard);
        first_hard.setOnClickListener(OnClickListener1);
        second_hard.setOnClickListener(OnClickListener2);
        third_hard.setOnClickListener(OnClickListener3);
        fourth_hard.setOnClickListener(OnClickListener4);
        fifth_hard.setOnClickListener(OnClickListener5);



    }
    View.OnClickListener OnClickListener1  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num_prac ="first_hard";
            Intent intent = new Intent(HardPrac.this, PracText.class);
            intent.putExtra("num_prac",num_prac);
            startActivity(intent);

        }

    };
    View.OnClickListener OnClickListener2  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num_prac ="second_hard";
            Intent intent = new Intent(HardPrac.this, PracText.class);
            intent.putExtra("num_prac",num_prac);
            startActivity(intent);

        }

    };
    View.OnClickListener OnClickListener3  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num_prac ="third_hard";
            Intent intent = new Intent(HardPrac.this, PracText.class);
            intent.putExtra("num_prac",num_prac);
            startActivity(intent);

        }

    };
    View.OnClickListener OnClickListener4  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num_prac ="fourth_hard";
            Intent intent = new Intent(HardPrac.this, PracText.class);
            intent.putExtra("num_prac",num_prac);
            startActivity(intent);

        }

    };
    View.OnClickListener OnClickListener5  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num_prac ="fifth_hard";
            Intent intent = new Intent(HardPrac.this, PracText.class);
            intent.putExtra("num_prac",num_prac);
            startActivity(intent);

        }

    };

}