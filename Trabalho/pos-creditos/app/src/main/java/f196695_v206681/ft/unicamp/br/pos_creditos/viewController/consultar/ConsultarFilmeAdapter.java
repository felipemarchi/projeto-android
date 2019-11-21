package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.consultar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;

public class ConsultarFilmeAdapter extends RecyclerView.Adapter {
    private List<Filme> filmes;

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
        notifyDataSetChanged();
    }

    public void ordenarFilmesPorData() {
        Collections.sort(filmes, new Comparator<Filme>() {
            @Override
            public int compare(Filme f1, Filme f2) {
                return f2.getDataAvaliacao().compareTo(f1.getDataAvaliacao());
            }
        });
    }

    ConsultarFilmeAdapter() {
        filmes = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_consultar_filme, parent,false);
        final ConsultarViewHolder viewHolder = new ConsultarFilmeAdapter.ConsultarViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ConsultarFilmeAdapter.ConsultarViewHolder) holder).onBind(filmes.get(position));
    }

    @Override
    public int getItemCount() {
        return filmes.size();
    }

    private class ConsultarViewHolder extends RecyclerView.ViewHolder {
        private TextView textData;
        private TextView textComentario;
        private RatingBar ratingBar;

        public ConsultarViewHolder(View itemView) {
            super(itemView);
            textData = itemView.findViewById(R.id.consultar_filme_data);
            textComentario = itemView.findViewById(R.id.consultar_filme_comentario);
            ratingBar = itemView.findViewById(R.id.consultar_filme_estrelas);
        }

        public void onBind(final Filme filme) {
            textData.setText(DateFormat.getDateInstance(DateFormat.SHORT).format(filme.getDataAvaliacao()));
            textComentario.setText(filme.getComentario());
            ratingBar.setRating((float) filme.getAvaliacao());
        }
    }
}