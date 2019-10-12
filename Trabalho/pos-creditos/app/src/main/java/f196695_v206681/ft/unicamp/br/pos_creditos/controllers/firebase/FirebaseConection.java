package f196695_v206681.ft.unicamp.br.pos_creditos.controllers.firebase;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;

public abstract class FirebaseConection {

    public void salvarAvaliacao(Filme filme) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("filmes")
                .add(filme)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        FirebaseConection.this.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        FirebaseConection.this.onFailure();
                    }
                });
    }

    public abstract void onSuccess();
    public abstract void onFailure();

}
