package br.com.turmaformacaocast.avaliacao_pratica.model.persistence.repositories;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Email;
import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Phone;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.DataBaseHelper;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.contracts.ContactContract;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.contracts.EmailContract;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.contracts.PhoneContract;

public final class EmailRepository {

    public EmailRepository() {
        super();
    }

    public static void save(List<Email> emails, long contactId) {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        for(Email email : emails) {
            email.setContactId((int)contactId);
            ContentValues values = EmailContract.getContentValues(email);
            if (email.getId() == null) {
                db.insert(EmailContract.TABLE, null, values);
            } else {
                String where = EmailContract.ID + " = ? ";
                String[] params = {email.getId().toString()};
                db.update(EmailContract.TABLE, values, where, params);
            }
        }

        db.close();
        databaseHelper.close();
    }

    public static List<Email> getAll() {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(EmailContract.TABLE, EmailContract.COLUMNS, null, null, null, null, EmailContract.ID);
        List<Email> values = EmailContract.getEmails(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }

    public static List<Email> getByContactId(int contactId){
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = EmailContract.CONTACT_ID + " = ? ";
        String[] params = {String.valueOf(contactId)};

        Cursor cursor = db.query(EmailContract.TABLE, EmailContract.COLUMNS, where, params, null, null, null);
        List<Email> values = EmailContract.getEmails(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }
}
