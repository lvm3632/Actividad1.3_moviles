// Actividad 1.3
// Michel Lujano
// A01636172
// FriendsActivity.java

package mx.tec.actividad13;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FriendsActivity extends AppCompatActivity {

    private DBHelperFriends db_friends;

    private EditText editTextName, editTextHobby;
    private Button btnSaveNameAndHobby,
                   btnFindByName,
                   btnDeleteByName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        editTextName = findViewById(R.id.editTextName);
        editTextHobby = findViewById(R.id.editTextHobby);
        btnSaveNameAndHobby = findViewById(R.id.btnSaveNameAndHobby);
        btnFindByName = findViewById(R.id.btnFindByName);
        btnDeleteByName = findViewById(R.id.btnDeleteByName);


        db_friends = new DBHelperFriends(this);

        btnSaveNameAndHobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregar(v);
            }
        });

        btnFindByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar(v);
            }
        });

        btnDeleteByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                borrar(v);
                editTextName.setText("");
                editTextHobby.setText("");
            }
        });
    }



    public void agregar(View v){

        if (TextUtils.isEmpty(editTextName.getText().toString()) ||
                TextUtils.isEmpty(editTextHobby.getText().toString()))
        {
            Toast.makeText(this, "Ingresa los dos campos", Toast.LENGTH_SHORT).show();
        }else if(!DBHelperFriends.FriendsHT.containsKey(editTextName.getText().toString())){
            db_friends.guardar(editTextName.getText().toString(), editTextHobby.getText().toString());
            Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
            editTextName.setText("");
            editTextHobby.setText("");

        }else{
            Toast.makeText(this, "El nombre ya ha sido registrado anteriormente", Toast.LENGTH_SHORT).show();
        }

    }

    public void borrar(View v){

        int filasBorradas = db_friends.borrar(editTextName.getText().toString());
        Toast.makeText(this, filasBorradas + " Dato(s) borrado(s)", Toast.LENGTH_SHORT).show();
    }

    public void buscar(View v){
        String nombre = editTextName.getText().toString();

        int idQuery = db_friends.buscar(nombre);

        if(idQuery == 1){
            Toast.makeText(this, "Dato encontrado", Toast.LENGTH_SHORT).show();
            String hobby = DBHelperFriends.FriendsHT.get(nombre);
            editTextHobby.setText(hobby);
        }else{
            editTextName.setText("");
            editTextHobby.setText("");
            Toast.makeText(this, "No se encontró", Toast.LENGTH_SHORT).show();
        }
    }







}