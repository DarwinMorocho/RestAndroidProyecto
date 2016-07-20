package ec.com.appmusic.servicios;

import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ec.com.appmusic.basedatos.HelperBD;
import ec.com.appmusic.vo.ArtistaVO;

/**
 * Created by gato on 06/06/2016.
 */
public class ServicioArtista<SQLiteDataBase>  {

    private String columnas[] = { "idArtista", "nombreArtista", "generoMusical", "fotoArtista",
            "fechaNacimiento", "descripcion" };

    private HelperBD helperBD;
    private SQLiteDatabase sqLiteDatabase;


    public void abrirBD() {
        sqLiteDatabase = helperBD.getWritableDatabase();
    }

    public void cerrarDB() {
        helperBD.close();
    }

    public ServicioArtista(Context context) {
        helperBD = new HelperBD(context);
    }

    public void insertar(ArtistaVO artistaVO) {
        ContentValues valores = new ContentValues();
        valores.put("nombreArtista", artistaVO.getNombreArtista());
        valores.put("generoMusical", artistaVO.getGeneroMusical());
        valores.put("fotoArtista", artistaVO.getFotoArtista());
        valores.put("fechaNacimiento", artistaVO.getFechaNacimiento());
        valores.put("descripcion", artistaVO.getDescripcion());
        sqLiteDatabase.insert("ARTISTA", null, valores);
        Log.i("INSERTA", "coooooooooorrescto el artista");
    }


    public void modificar(ArtistaVO artistaVO){
        ContentValues valores = new ContentValues();
        valores.put("nombreArtista", artistaVO.getNombreArtista());
        valores.put("generoMusical", artistaVO.getGeneroMusical());
        valores.put("fotoArtista", artistaVO.getFotoArtista());
        valores.put("fechaNacimiento", artistaVO.getFechaNacimiento());
        valores.put("descripcion", artistaVO.getDescripcion());
        sqLiteDatabase.update("ARTISTA", valores, "idArtista=" + artistaVO.getIdArtista(), null);
        System.out.println("actualiza la inspeccion");
        Log.i("ACTUALIZA el artista", "cooooooooooooooooorresvto");

    }


    public List<ArtistaVO> recuperarIspecciones() {
        List<ArtistaVO> lista_Inspecciones = new ArrayList<ArtistaVO>();
        Cursor c = sqLiteDatabase.query("ARTISTA", columnas,
                null, null, null, null, null);
        int id, nom, genero, foto, fecha, descrip;

        id = c.getColumnIndex("idArtista");
        nom = c.getColumnIndex("nombreArtista");
        genero = c.getColumnIndex("generoMusical");
        foto = c.getColumnIndex("fotoArtista");
        fecha = c.getColumnIndex("fechaNacimiento");
        descrip = c.getColumnIndex("descripcion");

        if (c.getCount()!=0) {
            c.moveToFirst();
            do {
                ArtistaVO reg = new ArtistaVO(c.getInt(id), c.getString(nom), c.getString(genero), c.getString(foto),c.getString(fecha),c.getString(descrip));
                lista_Inspecciones.add(reg);
            } while (c.moveToNext());


        }else{

            lista_Inspecciones.add(new ArtistaVO(0,
                    "No existe el artista", "","","",""));
        }
        return lista_Inspecciones;
    }


    public List<ArtistaVO> recuperarForId(Integer idArtista) {
        List<ArtistaVO> lista_Inspecciones = new ArrayList<ArtistaVO>();
//		Cursor c = sqLiteDatabase.query("Inspeccion", columnas,
//				null, null, null, null, null);
        Cursor c = sqLiteDatabase.query(true, "ARTISTA", columnas,
                "idArtista" + "=" + idArtista, null, null, null,
                null, null);
        int id, nom, genero, foto, fecha, descrip;

        id = c.getColumnIndex("idArtista");
        nom = c.getColumnIndex("nombreArtista");
        genero = c.getColumnIndex("generoMusical");
        foto = c.getColumnIndex("fotoArtista");
        fecha = c.getColumnIndex("fechaNacimiento");
        descrip = c.getColumnIndex("descripcion");

        if (c.getCount()!=0) {
            c.moveToFirst();
            do {
                ArtistaVO reg = new ArtistaVO(c.getInt(id), c.getString(nom), c.getString(genero), c.getString(foto),c.getString(fecha),c.getString(descrip));
                lista_Inspecciones.add(reg);
            } while (c.moveToNext());


        }else{

            lista_Inspecciones.add(new ArtistaVO(0,
                    "No existe el artista", "","","",""));
        }
        return lista_Inspecciones;
    }

    public List<ArtistaVO> recuperarForNombre(String nombreArtista) {
        List<ArtistaVO> lista_Inspecciones = new ArrayList<ArtistaVO>();
//		Cursor c = sqLiteDatabase.query("Inspeccion", columnas,
//				null, null, null, null, null);
        Cursor c;
        if (!nombreArtista.equals("")){
             c = sqLiteDatabase.query(true, "ARTISTA", columnas,
                    "nombreArtista" + "='" + nombreArtista+"'", null, null, null,
                    null, null);

        }else{
           c = sqLiteDatabase.query("ARTISTA", columnas,
                    null, null, null, null, null);

        }
        int id, nom, genero, foto, fecha, descrip;

        id = c.getColumnIndex("idArtista");
        nom = c.getColumnIndex("nombreArtista");
        genero = c.getColumnIndex("generoMusical");
        foto = c.getColumnIndex("fotoArtista");
        fecha = c.getColumnIndex("fechaNacimiento");
        descrip = c.getColumnIndex("descripcion");

        if (c.getCount()!=0) {
            c.moveToFirst();
            do {
                ArtistaVO reg = new ArtistaVO(c.getInt(id), c.getString(nom), c.getString(genero), c.getString(foto),c.getString(fecha),c.getString(descrip));
                lista_Inspecciones.add(reg);
            } while (c.moveToNext());


        }else{

            lista_Inspecciones.add(new ArtistaVO(0,
                    "No existe el artista", "","","",""));
        }
        return lista_Inspecciones;
    }
    public void eliminar(Integer idArtista) {
        ContentValues values = new ContentValues();
        values.put("idArtista", idArtista);
        sqLiteDatabase.delete("ARTISTA",
                "idArtista=" + idArtista, null);
    }
}
