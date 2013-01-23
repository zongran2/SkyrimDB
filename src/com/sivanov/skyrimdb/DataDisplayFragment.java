package com.sivanov.skyrimdb;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;

public class DataDisplayFragment extends ListFragment {
    private final static int cursor_loader_id = 0;
    private SimpleCursorAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        setEmptyText("No data");

        adapter = new SimpleCursorAdapter(getActivity(),
                                          android.R.layout.two_line_list_item,
                                          null,
                                          new String[] { "form_id", "editor_id" },
                                          new int[] { android.R.id.text1, android.R.id.text2 },
                                          0);
        setListAdapter(adapter);

        setListShown(false);
    }

    private class CursorCallbacks implements LoaderCallbacks<Cursor> {
        private final Uri provider;

        public CursorCallbacks(Uri provider) {
            this.provider = provider;
        }

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle arg) {
            return new CursorLoader(getActivity(), provider, new String[] { "rowid as _id", "form_id", "editor_id" },
                    null, null, "editor_id");
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
    }

    public void setProvider(Uri provider) {
        getLoaderManager().restartLoader(cursor_loader_id, null, new CursorCallbacks(provider));
    }
}
