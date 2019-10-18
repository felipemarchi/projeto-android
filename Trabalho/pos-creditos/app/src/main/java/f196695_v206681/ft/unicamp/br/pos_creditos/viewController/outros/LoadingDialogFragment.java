package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.outros;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;

public class LoadingDialogFragment extends DialogFragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_loading, container, false);
        }

        return view;
    }

}
