package f196695_v206681.ft.unicamp.br.pos_creditos.model;

import android.widget.ImageView;

import com.google.firebase.Timestamp;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.omdb.DownloadImageTask;

public class FilmeAssistido {
    private double avaliacao;
    private String comentario;
    private Timestamp dataAvaliacao;

    private long id;
    private String title;
    private String release_date;
    private String poster_path;
    private String backdrop_path;

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Timestamp getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Timestamp dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBack_path() {
        return backdrop_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setBack_path(String back_path) {
        this.backdrop_path = back_path;
    }

    public void getSetPoster(ImageView imagePoster) {
        if (this.getPoster_path() == null || this.getPoster_path().equals("N/A"))
            imagePoster.setImageResource(R.drawable.default_poster);
        else {
            String url = "https://image.tmdb.org/t/p/w185/" + this.getPoster_path();
            new DownloadImageTask(imagePoster).execute(url);
        }
    }

    public void getSetBackdrop(ImageView imagePoster) {
        if (this.getBack_path() == null || this.getBack_path().equals("N/A"))
            imagePoster.setImageResource(R.drawable.default_poster);
        else {
            String url = "https://image.tmdb.org/t/p/w185/" + this.getBack_path();
            new DownloadImageTask(imagePoster).execute(url);
        }
    }
}
