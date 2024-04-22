package com.example.ariel6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText a;
    private EditText b;
    private EditText c;
    private double av;
    private double bv;
    private double cv;
    private Button calc;
    private Button rand;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        a= (EditText) findViewById(R.id.A);
        b= (EditText) findViewById(R.id.B);
        c= (EditText) findViewById(R.id.C);
        calc= (Button) findViewById(R.id.calc);
        rand= (Button) findViewById(R.id.rand);
        result= (TextView) findViewById(R.id.result);


    }
    public void randomize(View view){
        Random rand = new Random();
        av = rand.nextInt(1000);
        bv = rand.nextInt(1000);
        cv = rand.nextInt(1000);
        a.setText(av +"");
        b.setText(bv +"");
        c.setText(cv +"");
    }
    public void calculate(View view){
        try {
            av = Double.parseDouble(a.getText().toString());
            bv = Double.parseDouble(b.getText().toString());
            cv = Double.parseDouble(c.getText().toString());
        }   catch (Exception e){
            a.setText("Something Went Wrong");
            b.setText("Something Went Wrong");
            c.setText("Something Went Wrong");
            return;
        }
        Intent si = new Intent(this, MainActivity2.class);
        si.putExtra("n",av);
        si.putExtra("nn",bv);
        si.putExtra("nnn",cv);
        startActivityForResult(si,1);
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==1){
            if (resultCode== Activity.RESULT_OK)
            {
                String result1 = data.getStringExtra("result1");
                String result2 = data.getStringExtra("result2");
                if (result1.equals("Complex Root")||result2.equals("Complex Root"))
                    result.setText("Complex Root");
                else
                    result.setText(result1+"\n"+result2);

            }
        }
    }
}