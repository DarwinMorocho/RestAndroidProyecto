package ec.com.appmusic.servicios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ec.com.appmusic.basedatos.HelperBD;
import ec.com.appmusic.vo.ArtistaVO;
import ec.com.appmusic.vo.CancionVO;

/**
 * Created by gato on 06/06/2016.
 */
public class ServicioCancion<SQLiteDataBase>  {

    private String columnas[] = { "idCancion", "nombreCancion", "fechaRegistro", "duracion",
            "formato", "idArtista" };

    private HelperBD helperBD;
    private SQLiteDatabase sqLiteDatabase;


    public void abrirBD() {
        sqLiteDatabase = helperBD.getWritableDatabase();
    }

    public void cerrarDB() {
        helperBD.close();
    }

    public ServicioCancion(Context context) {
        helperBD = new HelperBD(context);
    }

    public void insertar(CancionVO cancionVO) {
        ContentValues valores = new ContentValues();
        valores.put("nombreCancion", cancionVO.getNombreCancion());
        valores.put("fechaRegistro", cancionVO.getFechaRegistro());
        valores.put("duracion", cancionVO.getDuracion());
        valores.put("formato", cancionVO.getFormato());
        valores.put("idArtista", cancionVO.getIdArtista());
        sqLiteDatabase.insert("CANCION", null, valores);
        Log.i("INSERTA", "coooooooooorrescto el artista");
    }


    public void modificar(CancionVO cancionVO){
        ContentValues valores = new ContentValues();
        valores.put("nombreCancion", cancionVO.getNombreCancion());
        valores.put("fechaRegistro", cancionVO.getFechaRegistro());
        valores.put("duracion", cancionVO.getDuracion());
        valores.put("formato", cancionVO.getFormato());
        valores.put("idArtista", cancionVO.getIdArtista());
        sqLiteDatabase.insert("CANCION", null, valores);
        sqLiteDatabase.update("CANCION", valores, "idCancion=" + cancionVO.getIdCancion(), null);
        System.out.println("actualiza la inspeccion");
        Log.i("ACTUALIZA el artista", "cooooooooooooooooorresvto");

    }


    public List<CancionVO> recuperarIspecciones() {
        List<CancionVO> lista_Inspecciones = new ArrayList<CancionVO>();
        Cursor c = sqLiteDatabase.query("CANCION", columnas,
                null, null, null, null, null);
        int id, idArtista, nom, formato, duracion, fecha;

        id = c.getColumnIndex("idCancion");
        nom = c.getColumnIndex("nombreCancion");
        fecha = c.getColumnIndex("fechaRegistro");
        duracion = c.getColumnIndex("duracion");
        formato = c.getColumnIndex("formato");
        idArtista = c.getColumnIndex("idArtista");

        if (c.getCount()!=0) {
            c.moveToFirst();
            do {
                CancionVO reg = new CancionVO(c.getInt(id),c.getInt(idArtista), c.getString(nom), c.getString(fecha), c.getString(duracion),c.getString(formato));
                lista_Inspecciones.add(reg);
            } while (c.moveToNext());


        }else{

            lista_Inspecciones.add(new CancionVO(0,0,
                    "No existe un cancion", "","",""));
        }
        return lista_Inspecciones;
    }


    public List<CancionVO> recuperarForId(Integer idCancion) {
        List<CancionVO> lista_Inspecciones = new ArrayList<CancionVO>();
//		Cursor c = sqLiteDatabase.query("Inspeccion", columnas,
//				null, null, null, null, null);
        Cursor c = sqLiteDatabase.query(true, "CANCION", columnas,
                "idArtista" + "=" + idCancion, null, null, null,
                null, null);
        int id, idArtista, nom, formato, duracion, fecha;

        id = c.getColumnIndex("idCancion");
        nom = c.getColumnIndex("nombreCancion");
        fecha = c.getColumnIndex("fechaRegistro");
        duracion = c.getColumnIndex("duracion");
        formato = c.getColumnIndex("formato");
        idArtista = c.getColumnIndex("idArtista");

        if (c.getCount()!=0) {
            c.moveToFirst();
            do {
                CancionVO reg = new CancionVO(c.getInt(id),c.getInt(idArtista), c.getString(nom), c.getString(fecha), c.getString(duracion),c.getString(formato));
                lista_Inspecciones.add(reg);
            } while (c.moveToNext());


        }else{

            lista_Inspecciones.add(new CancionVO(0,0,
                    "No existe un cancion", "","",""));
        }
        return lista_Inspecciones;
    }

    public List<CancionVO> recuperarForIdArtista(Integer idArtistafin) {
        List<CancionVO> lista_Inspecciones = new ArrayList<CancionVO>();
//		Cursor c = sqLiteDatabase.query("Inspeccion", columnas,
//				null, null, null, null, null);
        Cursor c = sqLiteDatabase.query(true, "CANCION", columnas,
                "idArtista" + "=" + idArtistafin, null, null, null,
                null, null);
        int id, idArtista, nom, formato, duracion, fecha;

        id = c.getColumnIndex("idCancion");
        nom = c.getColumnIndex("nombreCancion");
        fecha = c.getColumnIndex("fechaRegistro");
        duracion = c.getColumnIndex("duracion");
        formato = c.getColumnIndex("formato");
        idArtista = c.getColumnIndex("idArtista");

        if (c.getCount()!=0) {
            c.moveToFirst();
            do {
                CancionVO reg = new CancionVO(c.getInt(id),c.getInt(idArtista), c.getString(nom), c.getString(fecha), c.getString(duracion),c.getString(formato));
                lista_Inspecciones.add(reg);
            } while (c.moveToNext());


        }else{

            lista_Inspecciones.add(new CancionVO(0,0,
                    "No existe un cancion", "","",""));
        }
        return lista_Inspecciones;
    }

    public List<CancionVO> recuperarForNombre(String nombreCancion) {
        List<CancionVO> lista_Inspecciones = new ArrayList<CancionVO>();
//		Cursor c = sqLiteDatabase.query("Inspeccion", columnas,
//				null, null, null, null, null);
        Cursor c;
        if (!nombreCancion.equals("")){
             c = sqLiteDatabase.query(true, "CANCION", columnas,
                    "nombreCancion" + "='" + nombreCancion+"'", null, null, null,
                    null, null);

        }else{
           c = sqLiteDatabase.query("CANCION", columnas,
                    null, null, null, null, null);

        }
        int id, idArtista, nom, formato, duracion, fecha;

        id = c.getColumnIndex("idCancion");
        nom = c.getColumnIndex("nombreCancion");
        fecha = c.getColumnIndex("fechaRegistro");
        duracion = c.getColumnIndex("duracion");
        formato = c.getColumnIndex("formato");
        idArtista = c.getColumnIndex("idArtista");

        if (c.getCount()!=0) {
            c.moveToFirst();
            do {
                CancionVO reg = new CancionVO(c.getInt(id),c.getInt(idArtista), c.getString(nom), c.getString(fecha), c.getString(duracion),c.getString(formato));
                lista_Inspecciones.add(reg);
            } while (c.moveToNext());


        }else{

            lista_Inspecciones.add(new CancionVO(0,0,
                    "No existe un cancion", "","",""));
        }
        return lista_Inspecciones;
    }
    public void eliminar(Integer idArtista) {
        ContentValues values = new ContentValues();
        values.put("idCancion", idArtista);
        sqLiteDatabase.delete("CANCION",
                "idCancion=" + idArtista, null);
    }
}
