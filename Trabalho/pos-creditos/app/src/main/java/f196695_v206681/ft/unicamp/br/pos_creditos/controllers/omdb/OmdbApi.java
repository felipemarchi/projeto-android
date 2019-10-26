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

public abstract class OmdbApi {
    public abstract void onSuccess();
    public abstract void onFailure();

    private static String urlBase = "https://www.omdbapi.com/?apikey=" + Utils.getOmdbApiKey();
    private Retorno retorno;

    public Retorno getRetorno() {
        return retorno;
    }

    private String montaUrl(String titulo, String ano, int indexTipo, int pagina) {
        String url = urlBase;

        url += "&s=" + titulo.replaceAll(" ", "+");

        if (!ano.isEmpty())
            url += "&y=" + ano;

        if (indexTipo == 1)
            url += "&type=movie";
        else if (indexTipo == 2)
            url += "&type=series";

        url += "&page=" + pagina;

        return url;
    }

    public void buscarFilme(String titulo, String ano, int indexTipo, int pagina) {
        Request request = new Request.Builder().url(montaUrl(titulo,ano,indexTipo,pagina)).build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                OmdbApi.this.onFailure();
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
