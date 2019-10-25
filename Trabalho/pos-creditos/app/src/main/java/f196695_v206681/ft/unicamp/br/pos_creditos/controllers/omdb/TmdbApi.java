package f196695_v206681.ft.unicamp.br.pos_creditos.controllers.omdb;

import com.google.gson.Gson;

import java.io.IOException;

import f196695_v206681.ft.unicamp.br.pos_creditos.model.Retorno;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class TmdbApi {
    public abstract void onSuccess();
    public abstract void onFailure();

    private static String apikey = "166e5c2be7dc1189ef89bc84bf04831b";
    private static String urlBase = "http://api.themoviedb.org/3/search/";
    private Retorno retorno;

    public Retorno getRetorno() {
        return retorno;
    }

    private String montaUrl(String titulo, String ano, int indexTipo, int pagina) {
        String url = urlBase;
        String query = titulo.replaceAll(" ", "+");
        String language = "pt-BR";
        url += "movie?api_key=" + apikey + "&language=" + language + "&query=" + query;

        /*
        if (!ano.isEmpty())
            url += "&y=" + ano;


        if (indexTipo == 1)
        else if (indexTipo == 2) {
            url += "&type=series";
        }
        */

        //url += "&page=" + pagina;
        return url;
    }

    public void buscarFilme(String titulo, String ano, int indexTipo, int pagina) {
        Request request = new Request.Builder().url(montaUrl(titulo,ano,indexTipo,pagina)).build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                TmdbApi.this.onFailure();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    retorno = new Gson().fromJson(json, Retorno.class);
                    onSuccess();
                }
            }
        });
    }
}

