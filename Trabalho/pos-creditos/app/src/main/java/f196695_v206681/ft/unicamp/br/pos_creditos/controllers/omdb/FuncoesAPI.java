package f196695_v206681.ft.unicamp.br.pos_creditos.controllers.omdb;

import com.google.gson.Gson;

import java.io.IOException;

import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Retorno;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FuncoesAPI {

    private static String apikey = "ed9ff193";
    private static String urlBase = "https://www.omdbapi.com/?apikey=" + apikey;

    public static Retorno buscarPorFilme(String titulo, String ano, int indexTipo) {
        final Retorno[] retorno = new Retorno[1];

        String urlRequest = urlBase + "&s=" + titulo;
        if (ano != "")
            urlRequest += "&y=" + ano;
        if (indexTipo == 1)
            urlRequest += "&type=movie";
        else if (indexTipo == 2)
            urlRequest += "&type=series";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlRequest)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    retorno[0] = new Gson().fromJson(json, Retorno.class);
                }
            }
        });

        return retorno[0];
    }
}
