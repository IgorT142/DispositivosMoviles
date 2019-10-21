package Practicas.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Practicas.Quiz.Room.DatabaseService;
import Practicas.Quiz.Room.Player;

public class PointsActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseService databaseService = DatabaseService.get(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        TextView puntosTotales = findViewById(R.id.puntosTotal);
        String nickName = getIntent().getStringExtra("nick");
        String points = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
        puntosTotales.setText(getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE));
        Button button = findViewById(R.id.restart);
        button.setOnClickListener(this);
        TextView t = findViewById(R.id.playerNick);
        t.setText(nickName);
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        findViewById(R.id.points).setBackgroundColor(Color.rgb(preferences.getInt("r", 255), preferences.getInt("g", 255),
                preferences.getInt("b", 255)));
        Player p = databaseService.getPlayerByNick(nickName);
        if(p.getPoints()<Integer.parseInt(points)) {
            p.setPoints(Integer.parseInt(points));
            databaseService.updatePlayer(p);
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    public void home(View v){
        startActivity(new Intent(this, StartMenu.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
