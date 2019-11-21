package f196695_v206681.ft.unicamp.br.pos_creditos.controllers.firebase;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.FilmeAssistido;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Utils;

import static android.view.View.SCROLLBARS_OUTSIDE_INSET;
import static androidx.constraintlayout.widget.Constraints.TAG;

public abstract class FirebaseBuscar {
    private List<FilmeAssistido> filmes = new ArrayList<>();

    public List<FilmeAssistido> getFilmes() {
        return filmes;
    }

    public abstract  void onSuccess();

    public void buscarAvaliacoes() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(FirebaseAuth.getInstance().getUid())
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            FilmeAssistido filme = new FilmeAssistido();
                            filme.setId((long)document.get("id"));
                            filme.setPoster_path((String) document.get("poster_path"));
                            filme.setTitle((String) document.get("title"));
                            filme.setRelease_date((String) document.get("release_date"));
                            filme.setAvaliacao((double) document.get("avaliacao"));
                            filme.setComentario((String) document.get("comentario"));
                            filme.setDataAvaliacao((Timestamp) document.get("dataAvaliacao"));
                            filmes.add(filme);
                        }

                        FirebaseBuscar.this.onSuccess();
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                }
            });
    }

    public static void carregarUtils() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("utils")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Utils.setEmailPassword((String) document.get("email_password"));
                                Utils.setOmdbApiKey((String) document.get("omdb_api_key"));
                                Utils.setTmdbApiKey((String) document.get("tmdb_api_key"));
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void buscarFilmeAvaliado(final String tituloFilme) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(FirebaseAuth.getInstance().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.get("title").toString().equals(tituloFilme)) {
                                    FilmeAssistido filme = new FilmeAssistido();
                                    filme.setId((long)document.get("id"));
                                    filme.setPoster_path((String) document.get("poster_path"));
                                    filme.setBack_path((String) document.get("backdrop_path"));
                                    filme.setTitle((String) document.get("title"));
                                    filme.setRelease_date((String) document.get("release_date"));
                                    filme.setAvaliacao((double) document.get("avaliacao"));
                                    filme.setComentario((String) document.get("comentario"));
                                    filme.setDataAvaliacao((Timestamp) document.get("dataAvaliacao"));
                                    filmes.add(filme);
                                }
                            }

                            FirebaseBuscar.this.onSuccess();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}