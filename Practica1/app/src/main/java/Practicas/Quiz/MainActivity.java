package Practicas.Quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static final String EXTRA_MESSAGE = "Practicas.Quiz.MESSAGE";
    private ArrayList<Question> questions = new ArrayList<>(5);
    private int numPregunta = 0;
    private int puntos = 0;
    private String[] preguntas = {"¿Quién escribio la Odisea?", "¿Cómo se llama la capital de Mongolia?",
            "¿Qué libros están escritos por Stephen King?", "¿Cuál es el único mamífero capaz de volar?","¿A qué país corresponde esta imagen?"};
    private String[] preguntas1 = {"Homero", "Bart", "Joaquin Sabina", "George R.R. Martin"};
    private String[] preguntas2 = {"Ulan Bator", "Coruscant", "Endor", "Alcorcón"};
    private String[] preguntas3 = {"IT", "Carrie", "El resplandor", "Posesión infernal"};
    private String[] preguntas4 = {"Pingüino", "Paloma", "Gato", "Murciélago"};
    private String[] preguntas5={"Suecia","Irlanda","Japón","Francia"};
    private String[][] respuestas = {preguntas1, preguntas2, preguntas3, preguntas4,preguntas5};
    private int[] correctAnswers;
    private Long spinnerSelectedItem;
    private Spinner spinner;
    private RadioGroup[] tipoPregunta;
    private int textSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button enviar = findViewById(R.id.enviar);
        enviar.setOnClickListener(this);

        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        findViewById(R.id.mainLayout).setBackgroundColor(Color.rgb(preferences.getInt("r",255),preferences.getInt("g",255),
                preferences.getInt("b",255)));
        textSize=preferences.getInt("textSize",16);
    }

    @Override
    protected void onStart() {
        super.onStart();
        RadioGroup radioButtonType = findViewById(R.id.radioButtons);
        RadioGroup[] tipoPregunta = {radioButtonType, findViewById(R.id.spinner), findViewById(R.id.checkBoxes), radioButtonType,radioButtonType};
        this.tipoPregunta = tipoPregunta;
        int[] c = {findViewById(R.id.opc1).getId(), findViewById(R.id.opc1).getId(), findViewById(R.id.opc2).getId(),
                findViewById(R.id.opc4).getId(),findViewById(R.id.opc4).getId()};
        correctAnswers = c;
        for (int i = 0; i < 5; i++) {
            Question question = new Question(preguntas[i], correctAnswers[i]);
            question.setAnswers(respuestas[i]);
            questions.add(question);
        }
        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opcionesSpinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        this.spinner=spinner;
        clearCheckBoxes();
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
        int r=255,g=255,b=255;
        if (id == R.id.black){
            r= 87; g=76; b= 75;
            findViewById(R.id.mainLayout).setBackgroundColor(Color.rgb(r,g,b));
        }
        if (id == R.id.white){
            r=255;g=255;b=255;
            findViewById(R.id.mainLayout).setBackgroundColor(Color.rgb(r,g,b));
        }
        if (id == R.id.purple){
            r=154;g=67;b=161;
            findViewById(R.id.mainLayout).setBackgroundColor(Color.rgb(r,g,b));
        }
        if( id== R.id.textNormal){
            textSize=16;
            initializeQuestions();
        }
        if(id== R.id.textBig){
            textSize=22;
            initializeQuestions();
        }
        SharedPreferences preferences = getSharedPreferences("preferencias",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("r",r);
        editor.putInt("g",g);
        editor.putInt("b",b);
        editor.putInt("textSize",textSize);
        editor.commit();

        return super.onOptionsItemSelected(item);
    }

    protected void initializeQuestions() {
        if (numPregunta < 5) {
            if (numPregunta==4)
                findViewById(R.id.img).setVisibility(View.VISIBLE);
            tipoPregunta[numPregunta].setVisibility(View.VISIBLE);
            RadioGroup general = findViewById(R.id.opciones);
            RadioGroup opciones = tipoPregunta[numPregunta];
            opciones.clearCheck();
            ((TextView) general.getChildAt(0)).setText(numPregunta + 1 + "." + questions.get(numPregunta).getText());
            ((TextView) general.getChildAt(0)).setTextSize(textSize);
            switch (numPregunta) {
                case 2:
                    for (int i = 0; i < 4; i++) {
                        ((CheckBox) tipoPregunta[2].getChildAt(i)).setText(questions.get(numPregunta).getAnswers()[i]);
                        ((CheckBox) tipoPregunta[2].getChildAt(i)).setTextSize(textSize);

                    }
                    break;
                case 0:
                case 3:
                case 4:
                    for (int i = 0; i < 4; i++) {
                        ((RadioButton) opciones.getChildAt(i)).setText(questions.get(numPregunta).getAnswers()[i]);
                        ((RadioButton) opciones.getChildAt(i)).setTextSize(textSize);
                    }
                    break;
                case 1:
                    for(int i=0;i<spinner.getChildCount();i++){
                        ((TextView)spinner.getChildAt(i)).setTextSize(textSize);
                    }
            }
            TextView p = findViewById(R.id.puntos);
            p.setText(Integer.toString(puntos));
        }
    }

    @Override
    public void onClick(View v) {
        if (numPregunta != 4)
            tipoPregunta[numPregunta].setVisibility(View.GONE);
        RadioGroup opciones = tipoPregunta[numPregunta];
        int idRespuestaEscogida = opciones.getCheckedRadioButtonId();
            if (numPregunta < 5) {
                if (checkAnswer(idRespuestaEscogida)) {
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                    puntos = puntos + 3;
                } else {
                    Toast.makeText(this, "¡Fallaste!", Toast.LENGTH_SHORT).show();
                    puntos = puntos - 2;
                }
                numPregunta++;
                initializeQuestions();
            } else {
                Toast.makeText(this, "Enhorabuena", Toast.LENGTH_LONG).show();
                numPregunta++;
                initializeQuestions();
            }
        if (numPregunta == 5) {
            findViewById(R.id.img).setVisibility(View.GONE);
            Intent intent = new Intent(this, PointsActivity.class);
            String message = ((Integer.toString(puntos)));
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
            numPregunta = 0;
            puntos = 0;
        }
    }

    protected boolean checkAnswer(int id) {
        switch (numPregunta) {
            case 1:
                Spinner spinner = findViewById(R.id.spinner1);
                if (spinner.getSelectedItemPosition() == 0) {
                    return true;
                } else {
                    return false;
                }
            case 2:
                if (((CheckBox) findViewById(R.id.check1)).isChecked() && ((CheckBox) findViewById(R.id.check2)).isChecked()
                        && ((CheckBox) findViewById(R.id.check3)).isChecked() && !((CheckBox) findViewById(R.id.check4)).isChecked())
                    return true;
                else
                    return false;

            default:
                if (id == questions.get(numPregunta).getCorrectAnswer())
                    return true;
                else
                    return false;
        }
    }

    private void clearCheckBoxes(){
        for(int i =0;i<4;i++) {
            CheckBox ch = (CheckBox) ((RadioGroup) findViewById(R.id.checkBoxes)).getChildAt(i);
            ch.setChecked(false);
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerSelectedItem = id;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
