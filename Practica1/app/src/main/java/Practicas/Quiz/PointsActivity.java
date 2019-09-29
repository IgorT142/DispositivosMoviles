package Practicas.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PointsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        TextView puntosTotales = findViewById(R.id.puntosTotal);
        puntosTotales.setText(getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE));
        Button button = findViewById(R.id.restart);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
