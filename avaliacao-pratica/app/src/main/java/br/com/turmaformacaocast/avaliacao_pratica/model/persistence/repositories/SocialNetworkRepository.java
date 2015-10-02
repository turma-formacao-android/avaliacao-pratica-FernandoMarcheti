package br.com.turmaformacaocast.avaliacao_pratica.model.persistence.repositories;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.SocialNetwork;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.DataBaseHelper;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.contracts.SocialNetworkContract;

public final class SocialNetworkRepository {

    public SocialNetworkRepository() {
        super();
    }

    public static void save(SocialNetwork socialNetwork) {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = SocialNetworkContract.getContentValues(socialNetwork);
        if (socialNetwork.getId() == null) {
            db.insert(SocialNetworkContract.TABLE, null, values);
        } else {
            String where = SocialNetworkContract.ID + " = ? ";
            String[] params = {socialNetwork.getId().toString()};
            db.update(SocialNetworkContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static List<SocialNetwork> getAll() {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(SocialNetworkContract.TABLE, SocialNetworkContract.COLUMNS, null, null, null, null, SocialNetworkContract.ID);
        List<SocialNetwork> values = SocialNetworkContract.getSocialNetworks(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }
}
