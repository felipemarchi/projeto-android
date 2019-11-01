package f196695_v206681.ft.unicamp.br.aulas.stats;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import f196695_v206681.ft.unicamp.br.aulas.R;


public class StatsJogo3Fragment extends Fragment {
    TextView statusTextView;

    public StatsJogo3Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats_jogo3, container, false);
        statusTextView = view.findViewById(R.id.text_status);
        new GetPlaysWebClient(StatsJogo3Fragment.this).execute();

        return view;
    }

    public void setStatus(String text) {
        statusTextView.setText(text);
    }

}
