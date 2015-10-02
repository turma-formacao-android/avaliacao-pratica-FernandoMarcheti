package br.com.turmaformacaocast.avaliacao_pratica.model.persistence.repositories;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Address;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.DataBaseHelper;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.contracts.AddressContract;

public final class AddressRepository {

    public AddressRepository() {
        super();
    }

    public static void save(Address address) {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = AddressContract.getContentValues(address);
        if (address.getId() == null) {
            db.insert(AddressContract.TABLE, null, values);
        } else {
            String where = AddressContract.ID + " = ? ";
            String[] params = {address.getId().toString()};
            db.update(AddressContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static List<Address> getAll() {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(AddressContract.TABLE, AddressContract.COLUMNS, null, null, null, null, AddressContract.ID);
        List<Address> values = AddressContract.getAddresses(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }
}
