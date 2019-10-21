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
import android.widget.Toast;

import Practicas.Quiz.Room.DatabaseService;

public class StartMenu extends AppCompatActivity {
    private RadioGroup p;
    private DatabaseService databaseService;
    int lastAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseService = DatabaseService.get(this);
        p = findViewById(R.id.perfiles);

        for (int i = 0; i< databaseService.getPlayers().size();i++){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(databaseService.getPlayers().get(i).getNick());
            radioButton.setPadding(0,5,0,5);
            p.addView(radioButton);
            lastAdded = databaseService.getPlayers().size();
        }
    }

    public void startGame(View v) {
        if(p.getCheckedRadioButtonId() == -1 )
            Toast.makeText(this, "Selecciona un perfil primero", Toast.LENGTH_SHORT).show();
        else {
            RadioButton r = findViewById(p.getCheckedRadioButtonId());
            startActivity(new Intent(this, MainActivity.class).putExtra("nick", r.getText()));
        }
    }

    public void newPlayer(View v) {
        startActivity(new Intent(this, NewPlayer.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        for (int i = lastAdded; i< databaseService.getPlayers().size();i++){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(databaseService.getPlayers().get(i).getNick());
            radioButton.setPadding(0,5,0,5);
            p.addView(radioButton);
            lastAdded++;
        }
    }
}
