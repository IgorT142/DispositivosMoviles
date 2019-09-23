package Practicas.Quiz;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_MESSAGE = "Practicas.Quiz.MESSAGE";
    private int numPregunta = 0;
    private int puntos = 0;
    private String[] preguntas = {"¿Quién escribio la Odisea?","¿Cómo se llama la capital de Mongolia?",
                                    "Si 50 es el 100%, ¿cuánto es el 90%?","¿Cuál es el único mamífero capaz de volar?"};
    private String[] preguntas1={"Homero","Ulan Bator","2","Pingüino"};
    private String[] preguntas2={"Bart","Coruscant","45","Paloma"};
    private String[] preguntas3={"Joaquín Sabina","Endor","90","Gato"};
    private String[] preguntas4={"George R.R. Martin","Alcorcón","40","Murciélago"};
    private String[][] respuestas ={preguntas1,preguntas2,preguntas3,preguntas4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button enviar = findViewById(R.id.enviar);
        enviar.setOnClickListener(this);
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
        initializeQuestions();
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

    protected void initializeQuestions(){
        RadioGroup opciones = findViewById(R.id.opciones);
        opciones.clearCheck();
        ((TextView) opciones.getChildAt(0)).setText(numPregunta+1 + "." + preguntas[numPregunta]);
        for(int i = 0;i<4;i++){
            ((RadioButton) opciones.getChildAt(i+1)).setText(respuestas[i][numPregunta]);
        }
        TextView p = findViewById(R.id.puntos);
        p.setText(Integer.toString(puntos));
        numPregunta++;
    }

    @Override
    public void onClick(View v) {
        if(numPregunta==4){
            Intent intent = new Intent(this, PointsActivity.class);
            String message = ((Integer.toString(puntos)));
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
        RadioGroup opciones = findViewById(R.id.opciones);
        int idRespuestaEscogida = opciones.getCheckedRadioButtonId();
        if(numPregunta<4){
            if (checkAnswer(idRespuestaEscogida)){
                Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                puntos= puntos+3;
            }else{
                Toast.makeText(this, "¡Fallaste!", Toast.LENGTH_SHORT).show();
                puntos= puntos-2;
            }
            initializeQuestions();
        }
        else
            Toast.makeText(this,"Enhorabuena",Toast.LENGTH_LONG).show();
    }
    protected boolean checkAnswer(int id) {
        switch (numPregunta) {
            case 0:
                if (id == findViewById(R.id.opc1).getId())
                    return true;
            case 1:
                if (id == findViewById(R.id.opc1).getId())
                    return true;
            case 2:
                if (id == findViewById(R.id.opc2).getId())
                    return true;
            case 3:
                if (id == findViewById(R.id.opc4).getId())
                    return true;
        }
        return false;
    }
    public void sendMessage(View view){

    }
}
