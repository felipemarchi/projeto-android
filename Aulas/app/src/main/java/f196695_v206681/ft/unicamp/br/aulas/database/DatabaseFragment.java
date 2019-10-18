package f196695_v206681.ft.unicamp.br.aulas.database;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import f196695_v206681.ft.unicamp.br.aulas.R;

public class DatabaseFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private EditText edtId;
    private EditText edtTexto;
    private TextView txtOutputId;
    private TextView txtOutputMens;

    private View view;

    public DatabaseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_database, container, false);

        edtId = view.findViewById(R.id.edtId);
        edtTexto = view.findViewById(R.id.edtTexto);
        txtOutputId = view.findViewById(R.id.txtOutputId);
        txtOutputMens = view.findViewById(R.id.txtOutputMens);

        /*
        Configurando os Listeners
         */
        view.findViewById(R.id.btnInserir).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onInserir();
                    }
                }
        );

        view.findViewById(R.id.btnRemover).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onRemover();
                    }
                }
        );

        view.findViewById(R.id.btnSelecionar).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onSelecionar();
                    }
                }
        );

        view.findViewById(R.id.btnAtualizar).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onAtualizar();
                    }
                }
        );


        return view;
    }

    public void onStart() {
        super.onStart();
        dbHelper = new DatabaseHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();
    }

    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
        dbHelper.close();
    }

    public void onInserir() {
        int id = Integer.parseInt(edtId.getText().toString());
        String texto = edtTexto.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", id);
        contentValues.put("texto", texto);

        try {
            sqLiteDatabase.insert("tabela", null, contentValues);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Identificador já cadastrado!", Toast.LENGTH_LONG).show();
        }
    }

    public void onAtualizar() {
        int id = Integer.parseInt(edtId.getText().toString());
        String texto = edtTexto.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put("texto", texto);

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{Integer.toString(id)};

        sqLiteDatabase.update("tabela", contentValues, whereClause, whereArgs);
    }

    public void onRemover() {
        int id = Integer.parseInt(edtId.getText().toString());

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{Integer.toString(id)};

        sqLiteDatabase.delete("tabela", whereClause, whereArgs);
    }

    public void onSelecionar() {
        String sql = "Select * from tabela";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            String strId = "";
            String strMens = "";
            do {
                int id = cursor.getInt(0);
                String texto = cursor.getString(1);

                strId += id + "\n";
                strMens += texto + "\n";

            } while (cursor.moveToNext());
            txtOutputId.setText(strId);
            txtOutputMens.setText(strMens);
        }
        cursor.close();
    }

}
