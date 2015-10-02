package br.com.turmaformacaocast.avaliacao_pratica.model.persistence.contracts;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.SocialNetwork;

public final class SocialNetworkContract {

    public static final String TABLE = "contact_social_network";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String URL = "url";
    public static final String CONTACT_ID = "contact_id";

    public static final String[] COLUMNS = { ID, NAME, URL, CONTACT_ID };

    public SocialNetworkContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, " );
        create.append(NAME + " TEXT NOT NULL, " );
        create.append(URL + " TEXT NOT NULL, " );
        create.append(CONTACT_ID + " INTEGER ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(SocialNetwork socialNetwork) {
        ContentValues values = new ContentValues();
        values.put(SocialNetworkContract.ID, socialNetwork.getId());
        values.put(SocialNetworkContract.NAME, socialNetwork.getName());
        values.put(SocialNetworkContract.URL, socialNetwork.getUrl());
        values.put(SocialNetworkContract.CONTACT_ID, socialNetwork.getContactId());

        return values;
    }

    public static SocialNetwork getSocialNetwork(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            SocialNetwork socialNetwork = new SocialNetwork();
            socialNetwork.setId(cursor.getInt(cursor.getColumnIndex(SocialNetworkContract.ID)));
            socialNetwork.setName(cursor.getString(cursor.getColumnIndex(SocialNetworkContract.NAME)));
            socialNetwork.setUrl(cursor.getString(cursor.getColumnIndex(SocialNetworkContract.URL)));
            socialNetwork.setContactId(cursor.getInt(cursor.getColumnIndex(SocialNetworkContract.CONTACT_ID)));

            return socialNetwork;
        }
        return null;
    }

    public static List<SocialNetwork> getSocialNetworks(Cursor cursor) {
        ArrayList<SocialNetwork> socialNetworks = new ArrayList<>();
        while (cursor.moveToNext()) {
            socialNetworks.add(getSocialNetwork(cursor));
        }
        return socialNetworks;
    }
}
