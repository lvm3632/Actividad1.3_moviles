// Actividad 1.3
// Michel Lujano
// A01636172
// MainActivity.java



package mx.tec.actividad13;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static TextView txtViewSaludo1;
    public static TextView txtViewSaludo2;

    private static SharedPreferences prefs; // Reference Object
    private static final String filenamePrefs = "activity13"; //filename

    // Keys hashtable
    private static final String KEY_SALUDO1 = "saludo1";
    private static final String KEY_SALUDO2 = "saludo2";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtViewSaludo1 = findViewById(R.id.txtViewSaludo1);
        txtViewSaludo2 = findViewById(R.id.txtViewSaludo2);


        MainActivity.setTxtViewSaludo1(txtViewSaludo1);
        MainActivity.setTxtViewSaludo2(txtViewSaludo2);



        // Loading filename - prefs
        prefs = getSharedPreferences(filenamePrefs, MODE_PRIVATE);
        loadPrefs(MainActivity.getTxtViewSaludo1(), MainActivity.getTxtViewSaludo2());
        //Toast.makeText(this, "ENTRO A DATOS ", Toast.LENGTH_SHORT).show();

    }

    public static void loadPrefs(TextView saludo1, TextView saludo2)
    {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_SALUDO1, saludo1.getText().toString());
        editor.putString(KEY_SALUDO2, saludo2.getText().toString());
        editor.commit();

        MainActivity.txtViewSaludo1.setText(saludo1.getText().toString());
        MainActivity.txtViewSaludo2.setText(saludo2.getText().toString());

        //Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show();

    }


    public static TextView getTxtViewSaludo1() {
        return txtViewSaludo1;
    }

    public static void setTxtViewSaludo1(TextView txtViewSaludo1) {
        MainActivity.txtViewSaludo1 = txtViewSaludo1;
    }

    public static TextView getTxtViewSaludo2() {
        return txtViewSaludo2;
    }

    public static void setTxtViewSaludo2(TextView txtViewSaludo2) {
        MainActivity.txtViewSaludo2 = txtViewSaludo2;
    }


    public void goToGreetingsActivity(View v){
        Intent goToGreetingsActivity = new Intent(this, GreetingsActivity.class);
        this.startActivity(goToGreetingsActivity);
    }

    public void goToFriendsActivity(View v){
        Intent goToFriendsActivity = new Intent(this, FriendsActivity.class);
        this.startActivity(goToFriendsActivity);
    }

}