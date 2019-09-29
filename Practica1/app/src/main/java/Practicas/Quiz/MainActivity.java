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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_MESSAGE = "Practicas.Quiz.MESSAGE";
    private ArrayList<Question> questions = new ArrayList<>(5);
    private int numPregunta = 0;
    private int puntos = 0;
    private String[] preguntas = {"¿Quién escribio la Odisea?","¿Cómo se llama la capital de Mongolia?",
                                    "Si 50 es el 100%, ¿cuánto es el 90%?","¿Cuál es el único mamífero capaz de volar?"};
    private String[] preguntas1={"Homero","Bart","Joaquin Sabina","George R.R. Martin"};
    private String[] preguntas2={"Ulan Bator","Coruscant","Endor","Alcorcón"};
    private String[] preguntas3={"2","45","90","40"};
    private String[] preguntas4={"Pingüino","Paloma","Gato","Murciélago"};
    private String[][] respuestas ={preguntas1,preguntas2,preguntas3,preguntas4};
    private int[] correctAnswers;

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
        int[] c = {findViewById(R.id.opc1).getId(),findViewById(R.id.opc1).getId(),findViewById(R.id.opc2).getId(),
                findViewById(R.id.opc4).getId()};
        correctAnswers = c;
        for(int i = 0; i<4;i++){
            Question question = new Question( preguntas[i],correctAnswers[i]);
            question.setAnswers(respuestas[i]);
            questions.add(question);
        }
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
        if (numPregunta<4) {
            RadioGroup general = findViewById(R.id.opciones);
            RadioGroup opciones = findViewById(R.id.radioButtons);
            opciones.clearCheck();
            ((TextView) general.getChildAt(0)).setText(numPregunta + 1 + "." + questions.get(numPregunta).getText());
            for (int i = 0; i < 4; i++) {
                ((RadioButton) opciones.getChildAt(i)).setText(questions.get(numPregunta).getAnswers()[i]);
            }
            TextView p = findViewById(R.id.puntos);
            p.setText(Integer.toString(puntos));
        }
    }

    @Override
    public void onClick(View v) {
        RadioGroup opciones = findViewById(R.id.radioButtons);
        int idRespuestaEscogida = opciones.getCheckedRadioButtonId();
        if(numPregunta<4){
            if (checkAnswer(idRespuestaEscogida)){
                Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                puntos= puntos+3;
            }else{
                Toast.makeText(this, "¡Fallaste!", Toast.LENGTH_SHORT).show();
                puntos= puntos-2;
            }
            numPregunta++;
            initializeQuestions();
        }
        else {
            Toast.makeText(this, "Enhorabuena", Toast.LENGTH_LONG).show();
            numPregunta++;
            initializeQuestions();
        }
        if(numPregunta==4){
            Intent intent = new Intent(this, PointsActivity.class);
            String message = ((Integer.toString(puntos)));
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
            numPregunta=0;
            puntos=0;
        }
    }
    protected boolean checkAnswer(int id) {
        if (id == questions.get(numPregunta).getCorrectAnswer())
            return true;
        else
            return false;
    }
}
