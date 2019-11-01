package f196695_v206681.ft.unicamp.br.aulas.stats;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import f196695_v206681.ft.unicamp.br.aulas.R;
import f196695_v206681.ft.unicamp.br.aulas.games.name.DatabaseHelper;

public class StatsJogo2Fragment extends Fragment {
    View view;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private int totalCount;
    private int errosCount;
    private Map<String, StatsJogo2Fragment.AtributoAlunos> alunosTable;

    public StatsJogo2Fragment() {
        System.out.println("Construtor");
        totalCount = 0;
        errosCount = 0;
        alunosTable = new HashMap<>();
    }

    public void onStart() {
        super.onStart();
        dbHelper = new DatabaseHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        carregarDados();
        setViewData();
    }

    private void setViewData() {
        String text = "Total de tentativas: " + Integer.toString(totalCount);
        ((TextView) view.findViewById(R.id.total_count)).setText(text);

        text = "Porcentagem de erros: ";
        if (errosCount > 0)
            text += String.format("%.2f", ((double) errosCount * 100 / totalCount)) + "%";
        else
            text += "0%";

        ((TextView) view.findViewById(R.id.erros_count)).setText(text);

        String nomeMaiorErro = "";
        String nomeMaiorChute = "";
        int qtdMaiorErro = 0;
        int qtdMaiorChute = 0;

        for (Map.Entry<String, AtributoAlunos> entry : alunosTable.entrySet()) {
            if (entry.getValue().getErros() > qtdMaiorErro) {
                qtdMaiorErro = entry.getValue().getErros();
                nomeMaiorErro = entry.getKey();
            }

            if (entry.getValue().getChutes() > qtdMaiorChute) {
                qtdMaiorChute = entry.getValue().getChutes();
                nomeMaiorChute = entry.getKey();
            }
        }
        System.out.println("AQUI");
        System.out.println(qtdMaiorErro);
        System.out.println(qtdMaiorChute);
        if (qtdMaiorErro > 0)
            ((TextView) view.findViewById(R.id.nome_erro)).setText(nomeMaiorErro);

        if (qtdMaiorChute > 0)
            ((TextView) view.findViewById(R.id.nome_chute)).setText(nomeMaiorChute);
    }

    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
        dbHelper.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_stats, container, false);


        return view;
    }

    public void carregarDados() {
        System.out.println("Alunos:");
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from Aluno", null);

        if (cursor.moveToFirst()) {
            String nome;
            int erros, chutes;

            do {
                nome = cursor.getString(0);
                erros = cursor.getInt(1);
                chutes = cursor.getInt(2);
                alunosTable.put(nome, new StatsJogo2Fragment.AtributoAlunos(erros, chutes));

                System.out.println(nome + " Erros: "  + erros + " Chutes: " + chutes);
            } while (cursor.moveToNext());
        }
        cursor.close();

        System.out.println("Jogadas:");
        cursor = sqLiteDatabase.rawQuery("Select * from Jogadas", null);

        if (cursor.moveToFirst()) {
            totalCount = cursor.getInt(0);
            errosCount = cursor.getInt(1);
            System.out.println("Total: " + totalCount + " Erros: " + errosCount);
        }
        cursor.close();
        System.out.println("Acabou");
    }

    class AtributoAlunos {
        private int erros;
        private int chutes;

        public AtributoAlunos(int erros, int chutes) {
            this.erros = erros;
            this.chutes = chutes;
        }

        public int getChutes() {
            return chutes;
        }

        public int getErros() {
            return erros;
        }

    }

}
