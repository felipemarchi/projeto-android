package f196695_v206681.ft.unicamp.br.aulas.games.frases;


import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class SavePlayWebClient extends AsyncTask<String, Void, String> {
    private final FrasesFragment frasesFragment;
    private Map<String, String> sublinks = new HashMap<>();


    public SavePlayWebClient(FrasesFragment frasesFragment) {
        this.frasesFragment = frasesFragment;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... args) {
        if (args.length == 0)
            return "No Parameter";

        HttpURLConnection httpURLConnection;

        try {
            URL url = new URL("https://aulas-a1728.firebaseio.com/.json");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            //httpURLConnection.setRequestMethod("POST");

            /*
            OutputStreamWriter osw = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
            osw.write("{" + args[1] + "}");
            osw.flush();
            osw.close();
            */
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));


            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            String retorno = sb.toString();

            System.out.println("aqui");
            System.out.println(args[0]);
            System.out.println(args[1]);

            return retorno;
        } catch (IOException e) {
            Log.v("Erro", e.getMessage());
            return "Exception\n" + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String args) {
        frasesFragment.getNewFrase();
    }
}
