package br.com.turmaformacaocast.avaliacao_pratica.model.persistence.repositories;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Contact;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.DataBaseHelper;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.contracts.ContactContract;

public final class ContactRepository {

    private static List<Contact> all;

    public ContactRepository() {
        super();
    }

    public static long save(Contact contact) {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long id = 0;
        ContentValues values = ContactContract.getContentValues(contact);
        if (contact.getId() == null) {
            id = db.insert(ContactContract.TABLE, null, values);
        } else {
            String where = ContactContract.ID + " = ? ";
            String[] params = {contact.getId().toString()};
            db.update(ContactContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();

        return id;
    }

    public static List<Contact> getAll() {

        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(ContactContract.TABLE, ContactContract.COLUMNS, null, null, null, null, null);
        List<Contact> values = ContactContract.getContacts(cursor);

        db.close();
        databaseHelper.close();
        return values;

    }

    public static void delete(Integer id) {

        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = ContactContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(ContactContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }
}
