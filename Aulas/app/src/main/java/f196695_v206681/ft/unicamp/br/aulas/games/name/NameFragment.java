package f196695_v206681.ft.unicamp.br.aulas.games.name;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import f196695_v206681.ft.unicamp.br.aulas.R;
import f196695_v206681.ft.unicamp.br.aulas.alunos.Aluno;
import f196695_v206681.ft.unicamp.br.aulas.alunos.Alunos;
import f196695_v206681.ft.unicamp.br.aulas.alunos.OnBiografiaRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NameFragment extends Fragment {

    private View lview;

    private Random random = new Random();
    private String nomeCorreto;
    private int positionAluno;
    private int numTentativas;

    private ImageView imageView;
    private TextView txtTentativas;
    private TextView txtFeedback;
    private ArrayList<Button> arrayListButton;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private int totalCount;
    private int errosCount;
    private Map<String, AtributoAlunos> alunosTable;

    private OnBiografiaRequest onBiografiaRequest;

    public void setOnBiografiaRequest(OnBiografiaRequest onBiografiaRequest) {
        this.onBiografiaRequest = onBiografiaRequest;
    }

    public NameFragment() {
        totalCount = 0;
        errosCount = 0;
        alunosTable = new HashMap<>();
    }

    public void onStart() {
        super.onStart();
        dbHelper = new DatabaseHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        carregarDados();
    }

    public void onStop() {
        super.onStop();
        salvarDados();
        sqLiteDatabase.close();
        dbHelper.close();
    }

    private void salvarDados() {

        for (Map.Entry<String, AtributoAlunos> aluno : alunosTable.entrySet()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("erros", aluno.getValue().getErros());
            contentValues.put("chutes", aluno.getValue().getChutes());

            String whereClause = "nome = ?";
            String[] whereArgs = new String[]{aluno.getKey()};
            sqLiteDatabase.update("Aluno", contentValues, whereClause, whereArgs);
        }

        String cmd = "UPDATE Jogadas SET total = " + totalCount + ", erros = " + errosCount;
        sqLiteDatabase.execSQL(cmd);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null)
            lview = inflater.inflate(R.layout.fragment_name, container, false);

        imageView = lview.findViewById(R.id.imageFoto);
        txtTentativas = lview.findViewById(R.id.txtTentativas);
        txtFeedback = lview.findViewById(R.id.txtFeedback);

        arrayListButton = new ArrayList<>();
        arrayListButton.add((Button) lview.findViewById(R.id.button1));
        arrayListButton.add((Button) lview.findViewById(R.id.button2));
        arrayListButton.add((Button) lview.findViewById(R.id.button3));
        arrayListButton.add((Button) lview.findViewById(R.id.button4));
        arrayListButton.add((Button) lview.findViewById(R.id.button5));
        arrayListButton.add((Button) lview.findViewById(R.id.button6));
        arrayListButton.add((Button) lview.findViewById(R.id.button7));
        arrayListButton.add((Button) lview.findViewById(R.id.button8));
        arrayListButton.add((Button) lview.findViewById(R.id.button9));

        startGame();

        View.OnClickListener guessButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalCount++;

                String nomeEscolhido = ((Button) v).getText().toString();
                if (nomeEscolhido.equals(nomeCorreto) ){
                    txtFeedback.setText("Certo!");
                    new Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    startGame();
                                }
                            }, 2000);
                } else {
                    errosCount++;
                    updateAlunosTable(nomeCorreto, nomeEscolhido);
                    txtFeedback.setText("Errado!");
                    numTentativas--;
                    txtTentativas.setText("Tentativas: " + numTentativas);

                    if (numTentativas == 0) {
                        txtFeedback.setText("Você Perdeu!");

                        new Handler().postDelayed(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        if (onBiografiaRequest != null) {
                                            onBiografiaRequest.onRequest(positionAluno);
                                        }
                                    }
                                }, 2000);
                    }
                }
                System.out.println("Total: " + totalCount);
                System.out.println("Erros: " + errosCount);
            }
        };

        for (int i = 0; i < 9; i++)
            arrayListButton.get(i).setOnClickListener(guessButtonListener);

        return lview;
    }

    private void updateAlunosTable(String nomeCorreto, String nomeEscolhido) {
        alunosTable.get(nomeCorreto).setErros(alunosTable.get(nomeCorreto).getErros() + 1);
        alunosTable.get(nomeEscolhido).setChutes(alunosTable.get(nomeEscolhido).getChutes() + 1);
    }

    private void startGame() {
        int guess = random.nextInt(Alunos.alunos.length);
        positionAluno = guess;
        Aluno aluno = Alunos.alunos[guess];
        nomeCorreto = aluno.getNome().split(" ")[0].toLowerCase();
        imageView.setImageResource(aluno.getFoto());
        numTentativas = 3;
        txtTentativas.setText("Tentativas: " + numTentativas);
        txtFeedback.setText("");

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 9;) {
            Aluno candidate = Alunos.alunos[(guess + i) % Alunos.alunos.length];
            String name = candidate.getNome();
            //if (!arrayList.contains(name)) {
                arrayList.add(name);
                i++;
            //}
        }

        Collections.shuffle(arrayList);

        for (int i = 0; i < 9; i++) {
            arrayListButton.get(i).setText(arrayList.get(i).split(" ")[0].toLowerCase());
        }
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
                alunosTable.put(nome, new AtributoAlunos(erros, chutes));

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

        public void setChutes(int chutes) {
            this.chutes = chutes;
        }

        public int getErros() {
            return erros;
        }

        public void setErros(int erros) {
            this.erros = erros;
        }
    }
}
