package io.exzorzio.pentascan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class EasyPrac extends AppCompatActivity {
     String num_prac;
    Button first_easy;
    Button second_easy;
    Button third_easy;
    Button fourth_easy;
    Button fifth_easy;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easyprac);
first_easy = (Button) findViewById(R.id.first_easy);
second_easy = (Button) findViewById(R.id.second_easy);
        third_easy = (Button) findViewById(R.id.third_easy);
        fourth_easy = (Button) findViewById(R.id.fourth_easy);
        fifth_easy = (Button) findViewById(R.id.fifth_easy);
first_easy.setOnClickListener(OnClickListener1);
        second_easy.setOnClickListener(OnClickListener2);
        third_easy.setOnClickListener(OnClickListener3);
        fourth_easy.setOnClickListener(OnClickListener4);
        fifth_easy.setOnClickListener(OnClickListener5);
    }
    View.OnClickListener OnClickListener1  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num_prac ="first_easy";
            Intent intent = new Intent(EasyPrac.this, PracText.class);
            intent.putExtra("num_prac",num_prac);
            startActivity(intent);

        }

    };
    View.OnClickListener OnClickListener2  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num_prac ="second_easy";
            Intent intent = new Intent(EasyPrac.this, PracText.class);
            intent.putExtra("num_prac",num_prac);
            startActivity(intent);

        }

    };
    View.OnClickListener OnClickListener3  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num_prac ="third_easy";
            Intent intent = new Intent(EasyPrac.this, PracText.class);
            intent.putExtra("num_prac",num_prac);
            startActivity(intent);

        }

    };
    View.OnClickListener OnClickListener4  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num_prac ="fourth_easy";
            Intent intent = new Intent(EasyPrac.this, PracText.class);
            intent.putExtra("num_prac",num_prac);
            startActivity(intent);

        }

    };
    View.OnClickListener OnClickListener5  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num_prac ="fifth_easy";
            Intent intent = new Intent(EasyPrac.this, PracText.class);
            intent.putExtra("num_prac",num_prac);
            startActivity(intent);

        }

    };
}