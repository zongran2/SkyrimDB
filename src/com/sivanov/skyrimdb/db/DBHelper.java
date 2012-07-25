package com.sivanov.skyrimdb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "test_db";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table perks (form_id varchar(8) not null primary key, editor_id varchar(50));");

        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('000EB24A', 'InvulnerableActorZeroIncomingDamage');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('000E997B', 'MQ304IncreaseAlduinDamage');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('001041A9', 'MQ101IncreaseDetection');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('00106092', 'doomRitualPerk');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('00105874', 'TGNightingaleShadowPerk');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('000CF788', 'PerkSkillBoosts');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('0010F21E', 'TG00Pickpockethelper');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('000C44C8', 'RestorationAdept50');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('000C44B8', 'AlterationAdept50');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('000C44C4', 'IllusionAdept50');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('000C44BC', 'ConjurationAdept50');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('000C44C0', 'DestructionAdept50');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('000BE127', 'Alchemist00');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('000C07CA', 'Alchemist20');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('000C07CB', 'Alchemist40');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('000C07CC', 'Alchemist60');");
        db.execSQL("INSERT INTO perks(form_id,editor_id) VALUES('000C07CD', 'Alchemist80');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists perks");
        onCreate(db);
    }

}
