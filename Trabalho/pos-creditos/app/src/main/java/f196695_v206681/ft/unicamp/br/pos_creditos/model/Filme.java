package f196695_v206681.ft.unicamp.br.pos_creditos.model;

import android.widget.ImageView;
import java.util.Date;
import java.util.List;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.omdb.DownloadImageTask;

public class Filme {
    private double avaliacao;
    private String comentario;
    private Date dataAvaliacao;

    private String backdrop_path;
    private List<Long> genre_ids;
    private long id;
    private String overview;
    private String poster_path;
    private String title;
    private String release_date;
    private double vote_average;

    public double getAvaliacao() {
        return avaliacao;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public long getId() {
        return id;
    }


    public String getBackdrop_path() {
        return backdrop_path;
    }

    public List<Long> getGenre_ids() {
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

    public void setPosterImage(ImageView imagePoster) {
        if (this.getPoster_path() == null || this.getPoster_path().equals("N/A"))
            imagePoster.setImageResource(R.drawable.default_poster);
        else {
            String url = "https://image.tmdb.org/t/p/w342/" + this.getPoster_path();
            new DownloadImageTask(imagePoster).execute(url);
        }
    }

    public void setBackdropImage(ImageView imagePoster) {
        if (this.getBackdrop_path() == null || this.getBackdrop_path().equals("N/A"))
            imagePoster.setImageResource(R.drawable.default_poster);
        else {
            String url = "https://image.tmdb.org/t/p/w780/" + this.getBackdrop_path();
            new DownloadImageTask(imagePoster).execute(url);
        }
    }

    public void setAvaliacao(double estrelas, Date data, String comentario) {
        this.avaliacao = estrelas;
        this.dataAvaliacao = data;
        this.comentario = comentario;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setGenre_ids(List<Long> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public static Filme getClone(Filme filme) {
        Filme clone = new Filme();

        clone.setBackdrop_path(filme.backdrop_path);
        clone.setGenre_ids(filme.genre_ids);
        clone.setId(filme.id);
        clone.setOverview(filme.overview);
        clone.setPoster_path(filme.poster_path);
        clone.setRelease_date(filme.release_date);
        clone.setTitle(filme.title);
        clone.setVote_average(filme.vote_average);

        return clone;
    }
}