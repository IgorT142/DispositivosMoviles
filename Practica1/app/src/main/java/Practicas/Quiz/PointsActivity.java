package Practicas.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PointsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        TextView puntosTotales = findViewById(R.id.puntosTotal);
        puntosTotales.setText(getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE));
    }
}
