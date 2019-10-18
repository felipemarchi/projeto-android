package f196695_v206681.ft.unicamp.br.aulas.games.name;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import f196695_v206681.ft.unicamp.br.aulas.alunos.Aluno;
import f196695_v206681.ft.unicamp.br.aulas.alunos.Alunos;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "NamesDb";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreateDatabase");
        String tableAlunos = "CREATE TABLE Aluno (\n";
        tableAlunos += "nome NVARCHAR(50) NOT NULL PRIMARY KEY,\n";
        tableAlunos += "erros int not null,\n";
        tableAlunos += "chutes int not null);";
        db.execSQL(tableAlunos);

        String tableJogadas = "CREATE TABLE Jogadas (\n";
        tableJogadas += "total int NOT NULL,\n";
        tableJogadas += "erros int not null);";
        db.execSQL(tableJogadas);

        initializeTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE tabela " +
                    "ADD COLUMN texto;");
        }
    }


    private void initializeTables(SQLiteDatabase db) {
        ContentValues values;

        for (Aluno aluno : Alunos.alunos) {
            values = new ContentValues();
            values.put("nome", aluno.getNome().split(" ")[0].toLowerCase());
            values.put("erros", 0);
            values.put("chutes", 0);

            try {
                db.insert("Aluno", null, values);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        values = new ContentValues();
        values.put("total", 0);
        values.put("erros", 0);
        db.insert("Jogadas", null, values);
    }

}

