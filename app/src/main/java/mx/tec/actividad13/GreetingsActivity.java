// Actividad 1.3
// Michel Lujano
// A01636172
// GreetingsActivity.java

package mx.tec.actividad13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GreetingsActivity extends AppCompatActivity {

    private static EditText editTextEmpty1,
                     editTextEmpty2;

    private  Button btnUpdateGreetings;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greetings);

        editTextEmpty1 = findViewById(R.id.editTextEmpty1);
        editTextEmpty2 = findViewById(R.id.editTextEmpty2);
        btnUpdateGreetings = findViewById(R.id.btnUpdateGreetings);

        btnUpdateGreetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.loadPrefs(GreetingsActivity.editTextEmpty1,GreetingsActivity.editTextEmpty2);
                Toast.makeText(GreetingsActivity.this, "Datos actualizados en SharedPrefs", Toast.LENGTH_SHORT).show();
                finish();
            }
        });



    }
}