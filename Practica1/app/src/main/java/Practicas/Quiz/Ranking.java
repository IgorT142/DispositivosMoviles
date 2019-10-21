package Practicas.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import Practicas.Quiz.Room.DatabaseService;

public class Ranking extends AppCompatActivity {
    DatabaseService databaseService = DatabaseService.get(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        TextView t = findViewById(R.id.ranking_points);
        TextView t1 = findViewById(R.id.ranking_points2);
        t.setSingleLine(false);
        t1.setSingleLine(false);
        for (int i = 0; i < databaseService.getPlayers().size(); i++) {
            t.append(databaseService.getPlayers().get(i).getNick());
            t.append("\n");
            t1.append("" + databaseService.getPlayers().get(i).getPoints());
            t1.append("\n");
        }

    }
}
