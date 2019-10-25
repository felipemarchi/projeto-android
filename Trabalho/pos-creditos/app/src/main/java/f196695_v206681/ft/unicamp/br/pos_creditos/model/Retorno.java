package f196695_v206681.ft.unicamp.br.pos_creditos.model;

import java.util.List;

public class Retorno {
    private int page;
    private int total_results;
    private int total_pages;
    private List<Filme> results;

    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<Filme> getResults() {
        return results;
    }
}
