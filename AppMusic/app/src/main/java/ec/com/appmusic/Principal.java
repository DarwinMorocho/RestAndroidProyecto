package ec.com.appmusic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.widget.ProgressBar;

import android.widget.RelativeLayout;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    public static Activity fa;
    private static int progress;
    private ProgressBar progressBar;
    private TextView txtBienvenida;
    protected static final int TIMER_RUNTIME = 3000; // in ms --> 10s
    protected boolean mbActive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        RelativeLayout r= (RelativeLayout)findViewById(R.id.RelativeLayout1);
        r.setBackgroundColor(Color.argb(160, 200, 200, 200));
        fa=this;

        progress = 0;
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        txtBienvenida = (TextView) findViewById(R.id.txtBienvenida);
        progressBar.setMax(20);

        final Thread timerThread = new Thread() {
            @Override
            public void run() {
                mbActive = true;
                try {
                    int waited = 0;
                    while (mbActive && (waited < TIMER_RUNTIME)) {
                        sleep(200);
                        if (mbActive) {
                            waited += 200;
                            updateProgress(waited);
                        }
                        if (waited == TIMER_RUNTIME) {
                            Intent intent = new Intent(Principal.this,
                                    Buscar.class);
                            startActivity(intent);
                            //finish();
                        }
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    onContinue();
                }
            }
        };
        timerThread.start();
        //txtBienvenida.setText("Bienvenido al sistema");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void updateProgress(final int timePassed) {
        if (null != progressBar) {
            // Ignore rounding error here
            final int progress = progressBar.getMax() * timePassed
                    / TIMER_RUNTIME;
            progressBar.setProgress(progress);
        }
    }

    public void onContinue() {
        // perform any final actions here
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);

        return true;
    }

}
