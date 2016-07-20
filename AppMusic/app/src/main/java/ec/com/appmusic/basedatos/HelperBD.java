package ec.com.appmusic.basedatos;

/**
 * Created by gato on 06/06/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class HelperBD extends SQLiteOpenHelper  {

    private static int version = 3;
    private static String nombreBdd = "rockApp";

    public HelperBD(Context context, String name, CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
    }

    public HelperBD(Context context) {
        super(context, nombreBdd, null, version);
    }

    String sentencia = "CREATE TABLE ARTISTA(idArtista INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " nombreArtista TEXT, generoMusical TEXT,fotoArtista TEXT,fechaNacimiento TEXT,descripcion TEXT)";
//duracion registraremos el album
    String sentencia1 = "CREATE TABLE CANCION(idCancion INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "nombreCancion TEXT, fechaRegistro TEXT,duracion TEXT,formato TEXT, idArtista INTEGER,"
            + "FOREIGN KEY (idArtista) REFERENCES ARTISTA (idArtista) ON DELETE CASCADE)";

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sentencia);
        db.execSQL(sentencia1);

        System.out.println("creando tablas...");

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DBAdapter", "Upgrading database from version " + oldVersion
                + " to "

                + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS ARTISTA");
        db.execSQL("DROP TABLE IF EXISTS CANCION");
        System.out.println("Tablas Empleado y Cargo borradas");

        onCreate(db);
        System.out.println("Creando tablas nuevamente....");

    }
}
