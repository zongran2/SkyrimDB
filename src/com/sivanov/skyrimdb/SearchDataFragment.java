package com.sivanov.skyrimdb;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class SearchDataFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        EditText editor = (EditText) getActivity().findViewById(R.id.filter_edit);
        if (null != editor) {
            editor.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

                @Override
                public void afterTextChanged(Editable s) {
                    DataDisplayFragment f = (DataDisplayFragment) getFragmentManager().findFragmentById(R.id.data_fragment);
                    Uri provider = getActivity().getIntent().getParcelableExtra("provider");
                    if (null != f && null != provider) {
                        Uri filter = provider.buildUpon().appendPath(s.toString()).build();
                        f.setProvider(filter);
                    }
                }
            });
        }
    }
}
