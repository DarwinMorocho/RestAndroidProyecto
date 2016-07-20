package ec.com.appmusic;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ec.com.appmusic.servicios.ServicioCancion;
import ec.com.appmusic.vo.ArtistaVO;
import ec.com.appmusic.vo.CancionVO;

public class ListaCanciones extends AppCompatActivity {
    //servicios
    ServicioCancion servicioCancion;
    public Integer idArtistaRecu;
    //objetos de la vista
    private EditText edtBuscar;
    private EditText anio;
    private ListView lstCancion;
    private Button btnBuscar;


    List<CancionVO> listaCancionBase= new ArrayList<CancionVO>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_canciones);

        servicioCancion= new ServicioCancion(getApplicationContext());
        Bundle bundle = this.getIntent().getExtras();
        idArtistaRecu=bundle.getInt("idArtista");
        cargarComponentes();
        cargarDatosIniciales(idArtistaRecu);

        // evento de ususario
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String parametro="";
                if (!edtBuscar.getText().toString().equals(""))
                    parametro=edtBuscar.getText().toString();
                else
                    parametro="-1";
                BuscarCancionAsin bla = new BuscarCancionAsin();
                bla.execute(parametro);
            }
        });

    }
    private void cargarComponentes() {
        lstCancion = (ListView) findViewById(R.id.lstCanciones);
        edtBuscar = (EditText) findViewById(R.id.edtBuscarCancion);
        btnBuscar = (Button) findViewById(R.id.btnBuscarCancion);


    }
    private void cargarDatosIniciales(Integer idArtista) {

        try {
            String parametro="";
            if (!edtBuscar.getText().toString().equals(""))
                parametro=edtBuscar.getText().toString();
            else
                parametro="-1";
            BuscarCancionAsin bla = new BuscarCancionAsin();
            bla.execute(parametro);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error en la consulta"+e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            Log.i("ESTE ES el eeerror", e.toString());
        }
    }
    class AdaptadorCancion extends ArrayAdapter<CancionVO> {

        Activity context;

        public AdaptadorCancion(Activity context) {
            super(context, R.layout.activity_canciones, listaCancionBase);
            this.context = context;

        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater
                    .inflate(R.layout.activity_canciones, null);
            // cargamos el nombre de la inspeccion

            TextView txtNombre = (TextView) item
                    .findViewById(R.id.txtNombreCancionNue);
            txtNombre.setText(listaCancionBase.get(position).getNombreCancion());

            TextView txtAlbum = (TextView) item
                    .findViewById(R.id.txtAlbumNue);
            txtAlbum.setText(listaCancionBase.get(position).getDuracion());

            TextView txtAnio = (TextView) item
                    .findViewById(R.id.txtFechaRegNueva);
            txtAnio.setText(listaCancionBase.get(position)
                    .getFechaRegistro());

            TextView txtFormato = (TextView) item
                    .findViewById(R.id.txtFormatoNue);
            txtFormato.setText(listaCancionBase.get(position)
                    .getFormato());
            return (item);
        }
    }


    /*inner class
  * Recive, progreso, envia
  * */
    public class BuscarCancionAsin extends AsyncTask<String,Void,List<CancionVO>> {
        @Override
        protected List<CancionVO> doInBackground(String... param) {


            Log.i("valor ",param[0].toString());

            final String url = "http://192.168.0.209:8080/ProyectoRestMov/rest/WSRestCancion/consultarCancionList/nombre/{v1}";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add( new MappingJackson2HttpMessageConverter());
            CancionVO[] lstCancion = restTemplate.getForObject(url,CancionVO[].class,param[0].toString());
            listaCancionBase= Arrays.asList(lstCancion);
            return listaCancionBase;
        }

        @Override

        protected void onPostExecute(List<CancionVO> artistas) {
            super.onPostExecute(artistas);
            AdaptadorCancion adaptador = new AdaptadorCancion(ListaCanciones.this);
            lstCancion.setAdapter(adaptador);
        }
    }
}
