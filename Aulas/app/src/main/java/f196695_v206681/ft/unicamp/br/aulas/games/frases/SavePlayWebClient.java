package f196695_v206681.ft.unicamp.br.aulas.games.frases;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class SavePlayWebClient extends AsyncTask<String, Void, Boolean> {
    private final FrasesFragment frasesFragment;


    public SavePlayWebClient(FrasesFragment statsJogo3Fragment) {
        this.frasesFragment = statsJogo3Fragment;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Boolean doInBackground(String... args) {

        String nome = args[0];
        boolean acerto = args[1].equals("acerto");

        JSONObject jsonAluno = new JSONObject();
        JSONObject atributos = getJsonAluno(nome);

        try {
            if (atributos == null) {
                jsonAluno = createAlunoJson(nome);
                atributos = jsonAluno.getJSONObject(nome);
            }

            if (acerto) {
                atributos.put("Acertos", (int) atributos.get("Acertos") + 1);
            } else {
                atributos.put("Erros", (int) atributos.get("Erros") + 1);
            }

            jsonAluno.put(nome, atributos);

            updateDatabase(jsonAluno, nome, acerto);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private JSONObject createAlunoJson(String nome) throws JSONException {
        JSONObject atributos = new JSONObject();
        atributos.put("Acertos", 0);
        atributos.put("Erros", 0);

        JSONObject jsonAluno = new JSONObject();
        jsonAluno.put(nome, atributos);

        return jsonAluno;
    }

    private void updateDatabase(JSONObject jsonAluno, String nome, boolean acerto) {
        HttpURLConnection httpURLConnection;

        try {
            URL url = new URL("https://aulas-a1728.firebaseio.com/.json");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("PATCH");

            httpURLConnection.addRequestProperty("Content-Type", "application/json");

            OutputStreamWriter osw = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
            osw.write(jsonAluno.toString());
            osw.flush();
            osw.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            Log.v("Erro", e.getMessage());
        }
    }

    private JSONObject getJsonAluno(String nome) {
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

            try {
                JSONObject jsonObject = new JSONObject(dbJson);
                if (jsonObject.has(nome)) {
                    return jsonObject.getJSONObject(nome);
                }
            } catch (JSONException e) {
            }
        } catch (IOException e) {
            Log.v("Erro", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean bool) {
        frasesFragment.enableCheckButton();
    }
}
