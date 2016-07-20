package ec.com.appmusic;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.Date;
import java.util.List;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ec.com.appmusic.servicios.ServicioCancion;
import ec.com.appmusic.vo.ArtistaVO;
import ec.com.appmusic.vo.CancionVO;

public class CrearNuevaCancion extends AppCompatActivity {
    //servicios
    ServicioCancion servicioCancion;
    public Integer idArtistaRecu;
    private EditText txtNombreCancion;
    private EditText txtAlbum;
    private EditText numAnio;
    private Spinner cmbFormato;
    private Button btnGuardar;
    private ArtistaVO recupArt;
    private CancionVO cancion= new CancionVO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nueva_cancion);
        this.setTitle("Nueva cancion");
        cargarComponentes();
        servicioCancion = new ServicioCancion(getApplicationContext());
        // Creamos el adaptador
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.formatoCancion, android.R.layout.simple_spinner_item);
        // Añadios el layout para el menú
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Le indicamos al spinner el adaptador a usar
        cmbFormato.setAdapter(adapter);

        recupArt = (ArtistaVO) getIntent().getSerializableExtra("selectedArt");
        // evento de ususario
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                if (!txtNombreCancion.getText()
                        .toString().equals("")&&
                        !numAnio.getText().toString().equals("")) {
                    EditarAsync bla = new EditarAsync();
                    bla.execute(cancion);
                    finish();
                }else{

                    Toast.makeText(getApplicationContext(),
                            "Verifique los datos ingresados",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void cargarComponentes() {
        txtNombreCancion = (EditText) findViewById(R.id.ETNombreCancion);
        txtAlbum = (EditText) findViewById(R.id.ETAlbum);
        numAnio = (EditText) findViewById(R.id.ETAnio);
        cmbFormato = (Spinner) findViewById(R.id.cmbFormato);
        btnGuardar = (Button) findViewById(R.id.buttonGuardarCancion);

    }
    public class EditarAsync extends AsyncTask<CancionVO, Void, List<CancionVO>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            String selec = cmbFormato
                    .getSelectedItem().toString();
            cancion.setIdArtista(recupArt.getIdArtista());
            cancion.setNombreCancion(txtNombreCancion.getText()
                    .toString());
            cancion.setFechaRegistro(new Date().toString());
            cancion.setDuracion(numAnio.getText().toString());
            cancion.setFormato(selec);


        }

        @Override
        protected List<CancionVO> doInBackground(CancionVO... param) {

            // final String url = "http://172.30.97.110:8080/WSRestMoviles/rest/WSRestLibro/consultarLibro?varId="+param[0];
            //Forma de concatenar

            // final String url = "http://172.30.96.14:8080/WSRestMoviles/rest/WSRestLibro/actualizarLibro/id/"+param[0].getId()+"/autor/Ejemplo autor/titulo/"+param[0].getTitulo()+"/stock/4";
            final String url = "http://192.168.0.209:8080/ProyectoRestMov/rest/WSRestCancion/insertarCancion/idArtista/{v1}/nombre/{v2}/fecha/{v3}/duracion/{v4}/formato/{v5}";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            String mensaje = restTemplate.getForObject(url, String.class,
                    param[0].getIdArtista(),
                    param[0].getNombreCancion(),
                    param[0].getFechaRegistro(),
                    param[0].getDuracion(),
                    param[0].getFormato());

            return null;
        }


    }
}