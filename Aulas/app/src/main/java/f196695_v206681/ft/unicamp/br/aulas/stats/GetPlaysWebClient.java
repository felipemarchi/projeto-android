package f196695_v206681.ft.unicamp.br.aulas.stats;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import f196695_v206681.ft.unicamp.br.aulas.games.frases.FrasesFragment;

public class GetPlaysWebClient extends AsyncTask<Void, Void, String> {
    private final StatsJogo3Fragment statsJogo3Fragment;


    public GetPlaysWebClient(StatsJogo3Fragment statsJogo3Fragment) {
        this.statsJogo3Fragment = statsJogo3Fragment;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(Void... voids) {
        String jsonString = getJson();
        System.out.println(jsonString);

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            Iterator<String> keys = jsonObject.keys();

            String text = "";

            while(keys.hasNext()) {
                String key = keys.next();
                int acertos = jsonObject.getJSONObject(key).getInt("Acertos");
                int  erros = jsonObject.getJSONObject(key).getInt("Erros");

                int total = acertos + erros;
                float percentAcertos = acertos * 100 / total;
                float percentErros = erros * 100 / total;

                text += key;
                text += ": Acerto = " + percentAcertos + "%";
                text += " | Erro: " + percentErros + "%";
                text += "\n";
            }

            return text;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }

    private String getJson() {
        HttpURLConnection httpURLConnection;

        try {
            URL url = new URL("https://aulas-a1728.firebaseio.com/.json");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            String dbJson = sb.toString();
            return dbJson;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String text) {
         statsJogo3Fragment.setStatus(text);
    }
}

