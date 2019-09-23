package Practicas.Quiz;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int numPregunta = 0;
    private String[] preguntas = {"¿Quién es mas nazi?"};
    private String[] preguntas1={"Hilter","Pirata","Orco","Troll"};
    private String[] preguntas2={"Hitler","Corsario","Tauren","NoMuerto"};
    private String[] preguntas3={"Garrosh","Grumete","Humano","Enano"};
    private String[] preguntas4={"Raul","Piltrafilla","Nelfo","Gnomo"};
    private String[][] respuestas ={preguntas1,preguntas2,preguntas3,preguntas4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        RadioGroup opciones = findViewById(R.id.opciones);
        ((TextView) opciones.getChildAt(0)).setText(numPregunta+1 + "." + preguntas[numPregunta]);
        for(int i = 0;i<4;i++){
            ((RadioButton) opciones.getChildAt(i+1)).setText(respuestas[i][0]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
