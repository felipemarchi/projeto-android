package f196695_v206681.ft.unicamp.br.aulas.games.frases;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FrasesAsyncTask extends AsyncTask<Void, Void, JogoFrases> {
    private final FrasesFragment frasesFragment;

    public FrasesAsyncTask(FrasesFragment fragment) {
        this.frasesFragment = fragment;
    }

    @Override
    protected JogoFrases doInBackground(Void... voids) {
        HttpURLConnection httpURLConnection;
        try {
            /*
               Endereço que será acessado.
             */
            String HOST = "https://sa4a4dtiv4.execute-api.eu-west-1.amazonaws.com/default/PythonHTTP1?kind=alunos&num_outros=3";

            URL url = new URL(HOST);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return makeObject(sb.toString());
        } catch (IOException e) {
            Log.v("Erro", e.getMessage());
            return null;
        }
    }

    private JogoFrases makeObject(String args) {
        JogoFrases jogo = new JogoFrases();
        try {
            System.out.println("json");
            System.out.println(args);
            JSONObject jsonObject = new JSONObject(args);

            jogo.setFrase(jsonObject.getString("frase"));
            jogo.setNome(jsonObject.getString("nome"));
            ArrayList<String> arrayList = new ArrayList<>();
            JSONArray jsonArray = jsonObject.getJSONArray("outros");
            for (int i = 0; i < jsonArray.length(); i++)
                arrayList.add(jsonArray.getString(i));
            jogo.setNomes(arrayList);
        } catch (JSONException e) {
        }

        return jogo;
    }

    @Override
    protected void onPostExecute(JogoFrases jogo) {
        frasesFragment.newGame(jogo);
    }
}
