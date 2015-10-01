package br.com.turmaformacaocast.avaliacao_pratica.model.persistence;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Contact;

public final class ContactRepository {

    public ContactRepository() {
        super();
    }

    public static void save(Contact contact) {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = ContactContract.getContentValues(contact, 1, 1, 1);
        if (contact.getId() == null) {
            db.insert(ContactContract.TABLE, null, values);
        } else {
            String where = ContactContract.ID + " = ? ";
            String[] params = {contact.getId().toString()};
            db.update(ContactContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }
}
