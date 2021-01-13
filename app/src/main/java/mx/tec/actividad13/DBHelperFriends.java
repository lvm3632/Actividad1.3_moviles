// Actividad 1.3
// Michel Lujano
// A01636172
// DBHelperFriends.java

package mx.tec.actividad13;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Hashtable;

public class DBHelperFriends extends SQLiteOpenHelper {

    private static final String DB_FILE ="FriendsDatabase.db";
    private static final String TABLE ="FRIENDS";
    private static final String FIELD_NAME ="name";
    private static final String FIELD_HOBBY ="hobby";

    public static Hashtable<String, String> FriendsHT=new Hashtable<String, String>(); ;



    public DBHelperFriends(Context context){
        super(context, DB_FILE, null, 1);

        SQLiteDatabase db = getReadableDatabase();


        Cursor c = db.query(TABLE, new String[] {"name", "hobby"}, null, null,null,null,null);

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                String name = c.getString(0);
                String hobby = c.getString(1);

                FriendsHT.put(name, hobby);

            } while(c.moveToNext());


        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a new databaseDB
        String query = "CREATE TABLE " + TABLE + "(" +
                FIELD_NAME + " TEXT PRIMARY KEY, " +

                FIELD_HOBBY + " TEXT)";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS ?";
        String[] params = {TABLE};
        db.execSQL(query, params);
        onCreate(db);
    }

    public void guardar(String nombre, String hobby){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();


        valores.put(FIELD_NAME, nombre);
        valores.put(FIELD_HOBBY, hobby);
        FriendsHT.put(nombre, hobby);
        db.insert(TABLE, null, valores);



    }

    public int borrar(String nombre){
        SQLiteDatabase db = getWritableDatabase();
        String clause = FIELD_NAME + " = ?";
        String[] params = {nombre};
        FriendsHT.remove(nombre);
        return db.delete(TABLE, clause, params);
    }

    public int buscar(String nombre){

        SQLiteDatabase db = getReadableDatabase();

        String clause = FIELD_NAME + " = ?";
        String[] params = {nombre};

        Cursor c = db.query(TABLE, null, clause, params,null,null,null);

        int resultado  = -1;
        int count=0;

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                String name = c.getString(0);
                String hobby = c.getString(1);

                FriendsHT.put(name, hobby);

            } while(c.moveToNext());

            count++;
        }



        return count != 0 ? count : resultado;
    }







}
