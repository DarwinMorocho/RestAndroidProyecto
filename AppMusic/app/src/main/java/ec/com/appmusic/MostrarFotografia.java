package ec.com.appmusic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.List;

import ec.com.appmusic.servicios.ServicioArtista;
import ec.com.appmusic.vo.ArtistaVO;

public class MostrarFotografia extends AppCompatActivity {
    //servicio

    ServicioArtista servicioArtista;
    // fotografia
    private ImageView imgFotoMostrar;
    private Bitmap bmap1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_fotografia);
        servicioArtista= new ServicioArtista(getApplicationContext());

        Bundle bundle = this.getIntent().getExtras();
        Integer idArtistaBus=bundle.getInt("idArtista");
        imgFotoMostrar = (ImageView) findViewById(R.id.imgMostrarFoto);
     /*
       if (idArtistaBus!=null){

          List<ArtistaVO> lstArtistas= servicioArtista.recuperarForId(idArtistaBus);

           bmap1 = BitmapFactory.decodeFile(Environment
                   .getExternalStorageDirectory() + lstArtistas.get(0).getFotoArtista());

       }else{
           bmap1 = BitmapFactory.decodeResource(getResources(),
                   R.drawable.vacio2);

       }*/
        bmap1 = BitmapFactory.decodeResource(getResources(),
                R.drawable.alert);

        imgFotoMostrar.setImageBitmap(bmap1);
    }
}
