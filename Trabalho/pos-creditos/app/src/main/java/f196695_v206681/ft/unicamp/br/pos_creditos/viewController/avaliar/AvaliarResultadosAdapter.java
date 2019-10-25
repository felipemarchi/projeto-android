package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;

public class AvaliarResultadosAdapter extends RecyclerView.Adapter {
    private List<Filme> filmes;
    private SearchResultOnClickListener onClickListener;

    public void setFilmes(List<Filme> filmes) {
        if (this.filmes.size() > 0) {
            this.filmes.addAll(filmes);
            notifyItemInserted(this.filmes.size() - filmes.size());
        } else {
            this.filmes = filmes;
            notifyDataSetChanged();
        }
    }

    public void setOnClickListener(SearchResultOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    AvaliarResultadosAdapter() {
        this.filmes = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_avaliar_resultados, parent,false);
        final SearchResultViewHolder viewHolder = new SearchResultViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(filmes.get(viewHolder.getIndex()));
                }
            }
        });
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
        private int index;
        private ImageView imagePoster;
        private TextView textTitulo;
        private TextView textAno;

        public int getIndex() {
            return index;
        }

        public SearchResultViewHolder(View itemView) {
            super(itemView);
            imagePoster = itemView.findViewById(R.id.avaliar_resultados_poster);
            textTitulo = itemView.findViewById(R.id.avaliar_resultados_titulo);
            textAno = itemView.findViewById(R.id.avaliar_resultados_ano);
        }

        public void onBind(final Filme filme, final int position) {
            this.index = position;
            filme.getSetPoster(imagePoster);
            textTitulo.setText(filme.getTitle());
            textAno.setText(filme.getRelease_date().substring(0, 4));
        }
    }

    public interface SearchResultOnClickListener {
        void onClick(Filme filme);
    }
}
