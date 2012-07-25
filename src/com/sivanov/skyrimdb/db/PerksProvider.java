package com.sivanov.skyrimdb.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class PerksProvider extends ContentProvider {
    private static final String AUTHORITY = "com.sivanov.skyrimdb.db.PerksProvider";
    private static final String BASE_PATH = "perks";

    public final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    public static final int ALL_PERKS = 100;
    public static final int PERK_FILTER = 110;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, ALL_PERKS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", PERK_FILTER);
    }

    private DBHelper dbHelper;

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();
        int rowsAffected = 0;
        switch (uriType) {
            case ALL_PERKS:
                rowsAffected = sqlDB.delete("perks", selection, selectionArgs);
                break;
            case PERK_FILTER:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsAffected = sqlDB.delete("perks", "rowid =" + id, null);
                } else {
                    rowsAffected = sqlDB.delete("perks", selection + " and " + "rowid =" + id, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown or Invalid URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsAffected;
    }

    @Override
    public String getType(Uri arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri arg0, ContentValues arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables("perks");

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case PERK_FILTER:
                queryBuilder.appendWhere("editor_id contains" + uri.getLastPathSegment());
                break;
            case ALL_PERKS:
                // no filter
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }

        Cursor cursor = queryBuilder.query(dbHelper.getReadableDatabase(),
                                           projection,
                                           selection,
                                           selectionArgs,
                                           null,
                                           null,
                                           sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
        // TODO Auto-generated method stub
        return 0;
    }

}
