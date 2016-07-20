package ec.com.appmusic;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
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
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import ec.com.appmusic.servicios.ServicioArtista;
import ec.com.appmusic.vo.ArtistaVO;

public class Buscar extends FragmentActivity implements Serializable {

    //objetos de la vista
    private EditText edtBuscar;
    private ListView lstArtistas;
    private Button btnBuscar;
    private Button btnNuevoArtista;
    List<ArtistaVO> artistasVO = new ArrayList<ArtistaVO>();

//servicios
    private ServicioArtista servicioArtista;
    //cvariables
    public ArtistaVO artistaSelected;

    List<ArtistaVO> listaArtistasBase= new ArrayList<ArtistaVO>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        cargarComponentes();
        this.setTitle("Buscar artista");
        servicioArtista = new ServicioArtista(getApplicationContext());

        // evento de ususario
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String parametro="";
                if (!edtBuscar.getText().toString().equals(""))
                    parametro=edtBuscar.getText().toString();
                else
                parametro="-1";
                BuscarArtistasAsin bla = new BuscarArtistasAsin();
                bla.execute(parametro);


            }
        });
        // evento de ususario
        btnNuevoArtista.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent = new Intent(Buscar.this,
                        CrearNuevoArtista.class);
                Bundle b = new Bundle();
                b.putInt("idArtista",0);
                intent.putExtras(b);
                startActivity(intent);

            }
        });

        //seleccionar un elemento de la lista
        lstArtistas.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                artistaSelected = (ArtistaVO) a.getItemAtPosition(position);
                if (!artistaSelected.getDescripcion().toString().equals("")) {

                    FragmentManager fragmentManager1 = getSupportFragmentManager();
                    DialogoSeleccionMedidor dialogo = new DialogoSeleccionMedidor();
                    dialogo.show(fragmentManager1, "tagSeleccion");
                } else {
                    Toast.makeText(getApplicationContext(),
                            "No existe un artista registrado..",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void cargarComponentes() {
        lstArtistas = (ListView) findViewById(R.id.lstArtista);
        edtBuscar = (EditText) findViewById(R.id.edtBuscar);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnNuevoArtista = (Button) findViewById(R.id.btnNuevoArtista);


    }
    public class BuscarArtistasAsin extends AsyncTask<String,Void,List<ArtistaVO>> {
        @Override
        protected List<ArtistaVO> doInBackground(String... param) {
            final String url = "http://192.168.0.209:8080/ProyectoRestMov/rest/WSRestArtista/consultarArtistaList/nombre/{v1}";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add( new MappingJackson2HttpMessageConverter());
            ArtistaVO[] lstArtistas = restTemplate.getForObject(url,ArtistaVO[].class,param[0].toString());
            listaArtistasBase=Arrays.asList(lstArtistas);
            return artistasVO;
        }
        @Override
        protected void onPostExecute(List<ArtistaVO> artistas) {
            super.onPostExecute(artistas);
            AdaptadorArtista adaptador = new AdaptadorArtista(Buscar.this);
            lstArtistas.setAdapter(adaptador);
        }
    }
    class AdaptadorArtista extends ArrayAdapter<ArtistaVO> {

        Activity context;

        public AdaptadorArtista(Activity context) {
            super(context, R.layout.activity_lista_artistas, listaArtistasBase);
            this.context = context;

        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater
                    .inflate(R.layout.activity_lista_artistas, null);
            // cargamos el nombre de la inspeccion

            TextView txtNombre = (TextView) item
                    .findViewById(R.id.txtNombreArtista);
            txtNombre.setText(listaArtistasBase.get(position).getNombreArtista());

            TextView txtGenero = (TextView) item
                    .findViewById(R.id.txtGenero);
            txtGenero.setText(listaArtistasBase.get(position).getGeneroMusical());

            TextView txtFecha = (TextView) item
                    .findViewById(R.id.txtFechaNacimiento);
            txtFecha.setText(listaArtistasBase.get(position)
                    .getFechaNacimiento());

            TextView txtDescripcion = (TextView) item
                    .findViewById(R.id.txtDescripcion);
            txtDescripcion.setText(listaArtistasBase.get(position)
                    .getDescripcion());
            return (item);
        }
    }


//menu sobre el listado
@SuppressLint("ValidFragment")
class DialogoSeleccionMedidor extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String[] items = {  "Listar canciones","Registrar Cancion","Mostrar fotografia","Eliminar artista","Cancelar" };

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());

        builder.setTitle("Seleccione una operación").setItems(items,
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int item) {
                        Log.i("Dialogos", "Opción elegida: " + items[item]);
                        if (item == 1) {

                          //Nuevo artista
                            Intent intent = new Intent(Buscar.this,
                                    CrearNuevaCancion.class);

                            intent.putExtra("selectedArt",artistaSelected);
                            startActivity(intent);

                        }
                       else if (item == 0) {
                            Intent intent = new Intent(Buscar.this,
                                    ListaCanciones.class);
                            Bundle b = new Bundle();

                            b.putInt("idArtista", artistaSelected.getIdArtista());
                            //   b.putInt("idUsuario", idUsuarioActual);
                            // b.putInt("idUsuario", medi.getIdUsuario());
                            intent.putExtras(b);
                            startActivity(intent);
                        }
                       else if (item == 2) {
                            Intent intent = new Intent(Buscar.this,
                                    MostrarFotografia.class);
                            Bundle b = new Bundle();

                            b.putInt("idArtista", artistaSelected.getIdArtista());
                            intent.putExtras(b);
                            startActivity(intent);
                        }
                        else if (item == 3) {
                            servicioArtista.abrirBD();
                            servicioArtista.eliminar(artistaSelected.getIdArtista());
                            servicioArtista.cerrarDB();
                            Toast.makeText(getApplicationContext(),
                                    "Artista eliminado",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if (item == 4) {
                            Toast.makeText(getApplicationContext(),
                                    "Operacion cancelada",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        return builder.create();
    }

}

}
