package Practicas.Quiz;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import Practicas.Quiz.Room.DatabaseService;

public class StartMenu extends AppCompatActivity {

    DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseService = DatabaseService.get(this);
        RadioGroup p = findViewById(R.id.perfiles);

        for (int i = 0; i< databaseService.getPlayers().size();i++){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(databaseService.getPlayers().get(i).getNick());
            p.addView(radioButton);

        }
    }

    public void startGame(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void newPlayer(View v) {
        startActivity(new Intent(this, NewPlayer.class));
    }


}
