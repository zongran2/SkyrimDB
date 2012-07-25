package com.sivanov.skyrimdb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sivanov.skyrimdb.db.PerksProvider;

public class DataDisplayFragment extends ListFragment {
    private final static int cursor_loader_id = 0;
    private SimpleCursorAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_dispay_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        setEmptyText("No data");

        adapter = new SimpleCursorAdapter(getActivity(),
                                          android.R.layout.simple_list_item_2,
                                          null,
                                          new String[] { "form_id", "editor_id" },
                                          new int[] { android.R.id.text1, android.R.id.text2 },
                                          0);
        setListAdapter(adapter);

        setListShown(false);

        getLoaderManager().initLoader(cursor_loader_id, null, new LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle arg) {
                return new CursorLoader(getActivity(),
                                        PerksProvider.CONTENT_URI,
                                        new String[] { "rowid as _id", "form_id", "editor_id" },
                                        null,
                                        null,
                                        "editor_id");
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                adapter.changeCursor(cursor);

                if (isResumed()) {
                    setListShown(true);
                } else {
                    setListShownNoAnimation(true);
                }
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                adapter.changeCursor(null);
            }
        });
    }
}
