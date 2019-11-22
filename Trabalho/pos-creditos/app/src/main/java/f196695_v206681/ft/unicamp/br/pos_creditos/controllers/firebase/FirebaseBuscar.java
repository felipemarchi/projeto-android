package f196695_v206681.ft.unicamp.br.pos_creditos.controllers.firebase;

import android.os.SystemClock;
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
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Utils;

import static androidx.constraintlayout.widget.Constraints.TAG;

public abstract class FirebaseBuscar {
    private List<Filme> filmes = new ArrayList<>();

    public List<Filme> getFilmes() {
        return filmes;
    }

    public abstract  void onSuccess();

    public void buscarAvaliacoes() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null)
            return;

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(FirebaseAuth.getInstance().getUid())
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Filme filme = new Filme();
                            filme.setAvaliacao((double) document.get("avaliacao"));
                            filme.setComentario((String) document.get("comentario"));
                            filme.setDataAvaliacao(((Timestamp) document.get("dataAvaliacao")).toDate());

                            filme.setBackdrop_path((String) document.get("backdrop_path"));
                            filme.setGenre_ids((List<Long>) document.get("genre_ids"));
                            filme.setId((long) document.get("id"));
                            filme.setOverview((String) document.get("overview"));
                            filme.setPoster_path((String) document.get("poster_path"));
                            filme.setTitle((String) document.get("title"));
                            filme.setRelease_date((String) document.get("release_date"));
                            filme.setVote_average((double) document.get("vote_average"));
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
        if (FirebaseAuth.getInstance().getCurrentUser() == null)
            return;

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
}