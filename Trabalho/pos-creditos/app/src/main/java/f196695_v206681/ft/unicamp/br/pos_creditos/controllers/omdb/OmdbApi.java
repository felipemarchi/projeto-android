package f196695_v206681.ft.unicamp.br.pos_creditos.controllers.omdb;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Retorno;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class OmdbApi {

    private static String apikey = "ed9ff193";
    private static String urlBase = "https://www.omdbapi.com/?apikey=" + apikey;
    private Retorno retorno;

    public Retorno getRetorno() {
        return retorno;
    }

    public void buscarFilme(String titulo, String ano, int indexTipo) {
        String requestUrl = urlBase + "&s=" + titulo.replaceAll(" ", "+");
        if (!ano.isEmpty())
            requestUrl += "&y=" + ano;
        if (indexTipo == 1)
            requestUrl += "&type=movie";
        else if (indexTipo == 2)
            requestUrl += "&type=series";

        Request request = new Request.Builder().url(requestUrl).build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    retorno = new Gson().fromJson(json, Retorno.class);
                    setAdapterFilmes();
                }
            }
        });
    }

    public abstract void setAdapterFilmes();
}
