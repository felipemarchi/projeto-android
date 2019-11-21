package f196695_v206681.ft.unicamp.br.pos_creditos.model;

import android.widget.ImageView;
import java.util.Date;
import java.util.List;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.omdb.DownloadImageTask;

public class Filme {
    private double avaliacao;
    private Date dataAvaliacao;
    private String comentario;
    private double popularity;
    private int vote_count;
    private boolean video;
    private String poster_path;
    private int id;
    private boolean adult;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    private List<Integer> genre_ids;
    private String title;
    private double vote_average;
    private String overview;
    private String release_date;

    public double getAvaliacao() {
        return avaliacao;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public int getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getTitle() {
        return title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void getSetPoster(ImageView imagePoster) {
        if (this.getPoster_path() == null || this.getPoster_path().equals("N/A"))
            imagePoster.setImageResource(R.drawable.default_poster);
        else {
            String url = "https://image.tmdb.org/t/p/w342/" + this.getPoster_path();
            new DownloadImageTask(imagePoster).execute(url);
        }
    }

    public void setAvaliacao(double estrelas, Date data, String comentario) {
        this.avaliacao = estrelas;
        this.dataAvaliacao = data;
        this.comentario = comentario;
    }

}