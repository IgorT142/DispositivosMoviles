package Practicas.Quiz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import Practicas.Quiz.Audio.AndroidAudio;
import Practicas.Quiz.Audio.Interfaces.Musica;
import Practicas.Quiz.Room.DatabaseService;

public class StartMenu extends AppCompatActivity {
    private RadioGroup p;
    private DatabaseService databaseService;
    int lastAdded;
    private static AndroidAudio androidAudio;
    private static Musica musica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseService = DatabaseService.get(this);
        p = findViewById(R.id.perfiles);

        for (int i = 0; i < databaseService.getPlayers().size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(databaseService.getPlayers().get(i).getNick());
            radioButton.setPadding(0, 5, 0, 5);
            p.addView(radioButton);
            lastAdded = databaseService.getPlayers().size();
        }
        if (androidAudio==null)
            androidAudio = new AndroidAudio(this);
            musica = androidAudio.nuevaMusica("stella.mp3");
            musica.play();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void startGame(View v) {
        if (p.getCheckedRadioButtonId() == -1)
            Toast.makeText(this, "Selecciona un perfil primero", Toast.LENGTH_SHORT).show();
        else {
            RadioButton r = findViewById(p.getCheckedRadioButtonId());
            if(musica.isPlaying())
            musica.dispose();
            startActivity(new Intent(this, MainActivity.class).putExtra("nick", r.getText()));
        }
    }

    public void newPlayer(View v) {
        startActivity(new Intent(this, NewPlayer.class));
    }

    public void viewRanking(View v) {
        startActivity(new Intent(this, Ranking.class));
    }

    public void startCamera(View v){
        if (p.getCheckedRadioButtonId() == -1)
            Toast.makeText(this, "Selecciona un perfil primero", Toast.LENGTH_SHORT).show();
        else {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            musica.pause();
            startActivityForResult(i, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        musica.play();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        for (int i = lastAdded; i < databaseService.getPlayers().size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(databaseService.getPlayers().get(i).getNick());
            radioButton.setPadding(0, 5, 0, 5);
            p.addView(radioButton);
            lastAdded++;
        }
    }

}
