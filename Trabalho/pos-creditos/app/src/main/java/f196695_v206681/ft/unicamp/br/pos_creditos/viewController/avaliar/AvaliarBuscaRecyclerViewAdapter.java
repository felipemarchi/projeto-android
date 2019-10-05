package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;

public class AvaliarBuscaRecyclerViewAdapter extends RecyclerView.Adapter {
    private ArrayList<Filme> filmes;
    private SearchResultOnClickListener onClickListener;

    AvaliarBuscaRecyclerViewAdapter(ArrayList<Filme> filmes) {
        this.filmes = filmes;
    }

    public interface SearchResultOnClickListener {
        void abrirTelaAvaliacao();
    }

    public void setOnClickListener(SearchResultOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_avaliar_busca, parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.abrirTelaAvaliacao();
                }
            }
        });

        final SearchResultViewHolder viewHolder = new SearchResultViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SearchResultViewHolder) holder).onBind(filmes.get(position), position);
    }

    @Override
    public int getItemCount() {
        return filmes.size();
    }

    private class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagePoster;
        private TextView textTitulo;
        private TextView textAno;
        public int position;

        public SearchResultViewHolder(View itemView) {
            super(itemView);
            imagePoster = itemView.findViewById(R.id.avaliar_busca_poster);
            textTitulo = itemView.findViewById(R.id.avaliar_busca_titulo);
            textAno = itemView.findViewById(R.id.avaliar_busca_ano);
        }

        public void onBind(final Filme filme, final int position) {
            this.position = position;
            imagePoster.setImageURI(Uri.parse(filme.getPoster()));
            textTitulo.setText(filme.getTitle());
            textAno.setText(filme.getYear());
        }

    }
}
