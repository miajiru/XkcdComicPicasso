package com.desarrollomovil.angel.xkcdcomicpicasso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.desarrollomovil.angel.xkcdcomicpicasso.Model.ComicModel;
import com.desarrollomovil.angel.xkcdcomicpicasso.Presenter.ComicPresenter;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements ComicPresenter.ComicPresenterListener{

    private int width;
    private int height;
    ComicPresenter comicPresenter;
    private ProgressBar progreso;
    private ImageView imagen;
    private String ultNumeroComic = "";
    public static OkHttpClient cliente;
    private Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progreso = (ProgressBar) findViewById(R.id.progressBar);
        imagen = (ImageView) findViewById(R.id.imageView);
        progreso.setVisibility(View.VISIBLE); //TODO Se inicia cuando empieza a descargar el comic mas reciente
        comicPresenter = new ComicPresenter(this);

        //TODO vamos a usar la misma libreria combinando OkHttp + Retrofit + Picasso
        cliente = new OkHttpClient.Builder()
                .build();
        picasso = new Picasso.Builder(getApplicationContext())
                .downloader(new OkHttp3Downloader(cliente))
                .build();

        comicPresenter.getComic(ultNumeroComic);//Todo último comic

        //TODO si pulsamos en la imagen cargamos comic aleatorio
        imagen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                progreso.setVisibility(View.VISIBLE);
                int numAleatorio = (int) (Math.random()* Integer.parseInt(ultNumeroComic)+1);
                comicPresenter.getComic(String.valueOf(numAleatorio)); //TODO comic aleatorio
            }
        });
    }

    @Override
    public void comicReady(ComicModel comic) {
        //TODO aquí obtenemos las actualizaciones gracias a WeatherPresenter y actualizamos el interfaz
        tamanyoDispositivo();//TODO adaptamos la imagen teniendo en cuenta el tamaño del dipositivo

        //TODO obtenemos el número del último comic
        if(ultNumeroComic.equals("")){
            ultNumeroComic = String.valueOf(comic.getNum());
        }
        picasso.with(MainActivity.this)
                .load(comic.getImg())
                .placeholder(R.drawable.placeholder1)
                .error(R.drawable.placeholder2)//TODO imagen que se mostrará si ocurre algún error
                .resize(width, height)
                .into(imagen, new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {
                                progreso.setVisibility(View.INVISIBLE);
                            }
                            @Override
                            public void onError() {
                                Toast.makeText(MainActivity.this, "Error al descargar la imagen", Toast.LENGTH_SHORT).show();
                                progreso.setVisibility(View.INVISIBLE);
                            }
                        });
    }

    //TODO procesaremos los mensajes de error
    @Override
    public void comicError(String message) {
        progreso.setVisibility(View.INVISIBLE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //TODO código sacado de 'http://es.stackoverflow.com/questions/1229/c%C3%B3mo-obtener-dimensiones-de-la-pantalla-en-android'
    public void tamanyoDispositivo(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels; //TODO ancho absoluto en pixels
        height = metrics.heightPixels; //TODO alto absoluto en pixels
    }
}
