package com.desarrollomovil.angel.xkcdcomicpicasso.Presenter;

import android.graphics.Bitmap;

import com.desarrollomovil.angel.xkcdcomicpicasso.Model.ComicModel;
import com.desarrollomovil.angel.xkcdcomicpicasso.Service.ComicAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.desarrollomovil.angel.xkcdcomicpicasso.MainActivity.cliente;

/**
 * Created by angel on 05/02/2017.
 */

public class ComicPresenter {

    private final ComicPresenterListener mListener;
    Bitmap bmp = null;

    public interface ComicPresenterListener{
        void comicReady(ComicModel comic);
        void comicError(String message);
    }

    // TODO Llamamos a la Activity a traves del listener (WeatherPresenterListerer)
    public ComicPresenter(ComicPresenterListener listener){
        this.mListener = listener;
    }

    public void getComic(String numComic) {
        //TODO Aquí llamamos al método de countryService, encolamos y implementamos el callback
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ComicAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(cliente)
                .build();

        ComicAPI comicApi = retrofit.create(ComicAPI.class);
        // TODO: obtener weatherModel a través del weatherService
        Call<ComicModel> call = comicApi.getComic(numComic);
        call.enqueue(new Callback<ComicModel>() {

            @Override
            public void onResponse(Call<ComicModel> call, Response<ComicModel> response) {
                if(response.isSuccessful()){//TODO Si todo es correcto enviamos el weatherModel con la respuesta a la Activity
                    ComicModel respuesta = response.body();//TODO cuerpo de la respuesta
                    mListener.comicReady(respuesta);

                }else {     //TODO Enviar a UI un mensaje especificando error, se mostrará en un Toast
                    mListener.comicError(response.message());

                }
            }

            //TODO fallo / exception al obtener el cuerpo de la respuesta, enviamos el mensaje a la activity, mensaje se mostrará en un Toast
            @Override
            public void onFailure(Call<ComicModel> call, Throwable t) {
                t.printStackTrace();
                mListener.comicError(t.getMessage());

            }
        });
    }
}
