package br.com.turmaformacaocast.avaliacao_pratica.model.persistence.repositories;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Phone;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.DataBaseHelper;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.contracts.PhoneContract;

public final class PhoneRepository {

    public PhoneRepository() {
        super();
    }

    public static void save(List<Phone> phones, long contactId) {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        for(Phone phone : phones) {
            phone.setContactId((int)contactId);
            ContentValues values = PhoneContract.getContentValues(phone);
            if (phone.getId() == null) {
                db.insert(PhoneContract.TABLE, null, values);
            } else {
                String where = PhoneContract.ID + " = ? ";
                String[] params = {phone.getId().toString()};
                db.update(PhoneContract.TABLE, values, where, params);
            }
        }

        db.close();
        databaseHelper.close();
    }

    public static List<Phone> getAll() {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(PhoneContract.TABLE, PhoneContract.COLUMNS, null, null, null, null, PhoneContract.ID);
        List<Phone> values = PhoneContract.getPhones(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }

    public static List<Phone> getByContactId(int contactId){
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = PhoneContract.CONTACT_ID + " = ? ";
        String[] params = {String.valueOf(contactId)};

        Cursor cursor = db.query(PhoneContract.TABLE, PhoneContract.COLUMNS, where, params, null, null, null);
        List<Phone> values = PhoneContract.getPhones(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }
}
