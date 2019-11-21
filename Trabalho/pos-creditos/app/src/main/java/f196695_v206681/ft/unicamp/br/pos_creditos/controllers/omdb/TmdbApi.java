package f196695_v206681.ft.unicamp.br.pos_creditos.controllers.omdb;

import com.google.gson.Gson;

import java.io.IOException;

import f196695_v206681.ft.unicamp.br.pos_creditos.model.Retorno;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Utils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class TmdbApi {
    public abstract void onSuccess();
    public abstract void onFailure();

    private static String urlBase = "http://api.themoviedb.org/3/search/";
    private Retorno retorno;

    public Retorno getRetorno() {
        return retorno;
    }

    private String montaUrl(String titulo, String ano, int indexLingua, int pagina) {
        String query = titulo.replaceAll(" ", "+");
        String language;
        if (indexLingua == 0) language = "pt-BR";
        else if (indexLingua == 1) language = "en-us";
        else language = "es-es";

        String url = String.format("%smovie?api_key=%s&language=%s&query=%s&page=%s", urlBase, Utils.getTmdbApiKey(), language, query, pagina);

        if (!ano.isEmpty())
            url += "&primary_release_year=" + ano;

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

