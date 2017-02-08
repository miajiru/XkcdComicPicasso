package com.desarrollomovil.angel.xkcdcomicpicasso.Service;

import com.desarrollomovil.angel.xkcdcomicpicasso.Model.ComicModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by angel on 05/02/2017.
 */

public interface ComicAPI {

    final String BASE_URL = "https://xkcd.com/";

    // TODO: Completar
    @GET("{numero}/info.0.json")
    Call<ComicModel> getComic(
            @Path("numero") String numComic);
}
