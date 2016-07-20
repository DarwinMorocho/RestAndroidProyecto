package ec.com.appmusic;

import android.R.bool;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.Configuration;


import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.Spinner;
import android.view.View.OnClickListener;

import java.io.File;
import java.util.Calendar;

import ec.com.appmusic.servicios.ServicioArtista;
import ec.com.appmusic.vo.ArtistaVO;

public class CrearNuevoArtista extends Activity implements OnClickListener {

    ServicioArtista servicioArtista;
    // para colocar el calendario en el edittext
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    //componentes visuales
    private Spinner cmbTipoGenero = null;
    private EditText edtNombreArtista;
    private EditText edtFechanacimi;
    private EditText edtDescripcion;
    private Button guardar;
    private Button btnFotografia;

    // fotografia
    private ImageView imgFoto;
    private Bitmap bmap1;
    private final String carpeta = "moviles";
    private String nombreFoto = "MKY_IMG"
            + String.valueOf(Calendar.getInstance().getTimeInMillis()) + ".jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nuevo_artista);
        this.setTitle("Nuevo artista");
        cargarComponentes();
        servicioArtista= new ServicioArtista(getApplicationContext());
        // inicializar calendario
        // calendario
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        edtFechanacimi.setKeyListener(null);
        edtFechanacimi.setOnClickListener(this);



        imgFoto = (ImageView) findViewById(R.id.imgRegistroFoto);

            bmap1 = BitmapFactory.decodeResource(getResources(),
                    R.drawable.vacio2);
        imgFoto.setImageBitmap(bmap1);

        // Creamos el adaptador
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.tipoGenero, android.R.layout.simple_spinner_item);
        // Añadios el layout para el menú
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Le indicamos al spinner el adaptador a usar
        cmbTipoGenero.setAdapter(adapter);

        // evento de ususario
        guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

            insertaArtista();
                finish();

            }
        });

        // evento de ususario
        btnFotografia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                boolean sdDisponible = false;
                boolean sdAccesoEscritura = false;
                // Comprobamos el estado de la memoria externa (tarjeta SD)
                String estado = Environment.getExternalStorageState();
                if (estado.equals(Environment.DIRECTORY_DOWNLOADS)) {
                    sdDisponible = true;
                    sdAccesoEscritura = true;
                } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
                    sdDisponible = true;
                    sdAccesoEscritura = false;
                } else {
                    sdDisponible = false;
                    sdAccesoEscritura = false;
                }
                if (sdDisponible == true && sdAccesoEscritura == true) {
                    // Toast.makeText(getApplicationContext(),
                    // "Disponible para guardar", Toast.LENGTH_SHORT).show();
                    // Creamos el Intent para llamar a la Camara
                    Intent cameraIntent = new Intent(
                            android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    // Creamos una carpeta en la memeria del terminal
                    File imagesFolder = new File(
                            Environment.getExternalStorageDirectory(), carpeta);
                    imagesFolder.mkdirs();
                    // añadimos el nombre de la imagen
                    File image = new File(imagesFolder, nombreFoto);
                    Uri uriSavedImage = Uri.fromFile(image);
                    // Le decimos al Intent que queremos grabar la imagen
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                    // Lanzamos la aplicacion de la camara con retorno (forResult)
                    startActivityForResult(cameraIntent, 0);
                } else {

                    Toast.makeText(getApplicationContext(),
                            "Verifique la camara ", Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });
    }

    private void cargarComponentes() {
        cmbTipoGenero = (Spinner) findViewById(R.id.cmbNueGeneroMusical);
        edtNombreArtista = (EditText) findViewById(R.id.edtNombreArtistaNue);
        edtFechanacimi = (EditText) findViewById(R.id.edtNueFecha);
        edtDescripcion = (EditText) findViewById(R.id.edtNueDescripcion);
        guardar=(Button)findViewById(R.id.btnGrabarArtista);
        btnFotografia=(Button)findViewById(R.id.btnFotografia);

    }

    // metodo del calendario
    @Override
    public void onClick(View v) {
        showDialog(0);
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            edtFechanacimi.setText(selectedDay + "/" + (selectedMonth + 1) + "/"
                    + selectedYear);
        }
    };


    //metodos servicio
    private void insertaArtista() {
        String selec = cmbTipoGenero
                .getSelectedItem().toString();
        servicioArtista.abrirBD();
        servicioArtista.insertar(new ArtistaVO(0, edtNombreArtista.getText()
                .toString(), selec, nombreFoto,edtFechanacimi.getText().toString(),edtDescripcion.getText().toString()));
        servicioArtista.cerrarDB();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Comprovamos que la foto se a realizado
        if (resultCode == RESULT_OK) {
            // Creamos un bitmap con la imagen recientemente
            // almacenada en la memoria
            // Bundle bundle= data.getExtras();
            // bmp=(Bitmap)bundle.get("data");

            bmap1.recycle();
            ImageView imgFoto = (ImageView) findViewById(R.id.imgRegistroFoto);
            bmap1 = BitmapFactory.decodeFile(Environment
                    .getExternalStorageDirectory()
                    + "/"
                    + carpeta
                    + "/"
                    + nombreFoto);
            // Añadimos el bitmap al imageView para
            // //mostrarlo por pantalla
            imgFoto.setImageBitmap(bmap1);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


}
