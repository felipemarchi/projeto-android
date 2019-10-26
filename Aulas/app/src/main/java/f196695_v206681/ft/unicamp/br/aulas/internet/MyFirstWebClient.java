package f196695_v206681.ft.unicamp.br.aulas.internet;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyFirstWebClient extends AsyncTask<String, Void, String> {

    TextView textView;

    public MyFirstWebClient(TextView textView) {
        this.textView = textView;
    }


    @Override
    protected void onPreExecute() {
        textView.setText("####################### \n ");
        textView.append("Iniciando Requisição \n ");
    }

    @Override
    protected String doInBackground(String... args) {
        if (args.length == 0) {
            return "No Parameter";
        }

        HttpURLConnection httpURLConnection;
        try {


            URL url = new URL(args[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod(args[1]);

            if (args.length == 3) {
                OutputStreamWriter osw = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
                osw.write(args[2]);
                osw.flush();
                osw.close();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));


            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            Log.v("Erro", e.getMessage());
            return "Exception\n" + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String args) {
        textView.setText(args);


    }
}
