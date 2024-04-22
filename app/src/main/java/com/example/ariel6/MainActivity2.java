package com.example.ariel6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    private Button back;
    private ImageView smiling;
    private ImageView crying;
    private Intent gi;
    private TextView result1;
    private TextView result2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        result1 = (TextView) findViewById(R.id.result1);
        result2 = (TextView) findViewById(R.id.result2);
        crying = (ImageView) findViewById(R.id.crying2);
        smiling = (ImageView) findViewById(R.id.smiling);
        back = (Button)findViewById(R.id.back);
        crying.setVisibility(View.INVISIBLE);
        smiling.setVisibility(View.INVISIBLE);
    }

    public void calculate() {
        gi = getIntent();
        double a = gi.getDoubleExtra("n", 1);
        double b = gi.getIntExtra("nn", 1);
        double c = gi.getIntExtra("nnn", 1);
        if (a > 0)
            smiling.setVisibility(View.VISIBLE);
        else
            crying.setVisibility(View.VISIBLE);
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            result1.setText(root1 + "");
            result2.setText(root2 + "");
        } else if (discriminant == 0) {
            // One real root (repeated)
            double root = -b / (2 * a);
            result2.setText(root + "");
            result1.setText("Only 1 solution");
        } else {
            result1.setText("Complex Root");
            result2.setText("Complex Root");
        }
    }
    public void back(){
        gi.putExtra("result1", result1.getText().toString());
        gi.putExtra("result2", result2.getText().toString());
        setResult(RESULT_OK,gi);
        finish();

    }
}