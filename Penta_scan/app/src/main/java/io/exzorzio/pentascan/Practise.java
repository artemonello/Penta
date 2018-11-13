package io.exzorzio.pentascan;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Practise extends AppCompatActivity {
    Button easyprac;
    Button medprac;
    Button hardprac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practise);
        easyprac = (Button) findViewById(R.id.easy_prac);
        medprac = (Button) findViewById(R.id.med_prac);
        hardprac = (Button) findViewById(R.id.hard_prac);
        easyprac.setOnClickListener(OnClickListener1);
        medprac.setOnClickListener(OnClickListener2);
        hardprac.setOnClickListener(OnClickListener3);

    }
    View.OnClickListener OnClickListener1  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Practise.this, EasyPrac.class);

            startActivity(intent);
        }
    };
    View.OnClickListener OnClickListener2  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Practise.this, MedPrac.class);

            startActivity(intent);
        }
    };
    View.OnClickListener OnClickListener3  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Practise.this, HardPrac.class);

            startActivity(intent);
        }
    };
}
