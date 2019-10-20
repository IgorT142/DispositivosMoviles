package Practicas.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Practicas.Quiz.Room.DatabaseService;
import Practicas.Quiz.Room.Player;

public class NewPlayer extends AppCompatActivity {

    private DatabaseService databaseService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);
        databaseService = DatabaseService.get(this);
    }

    public void saveNewPlayer(View v){
        EditText et1 = findViewById(R.id.newPlayerNick);
        Player player = new Player(et1.getText().toString());
        databaseService.addPlayer(player);
        Toast.makeText(this, "Nuevo jugador " + et1.getText() + " añadido", Toast.LENGTH_SHORT).show();
    }
}
