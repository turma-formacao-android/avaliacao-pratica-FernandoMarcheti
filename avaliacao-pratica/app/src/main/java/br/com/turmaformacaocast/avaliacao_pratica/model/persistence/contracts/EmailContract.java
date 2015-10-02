package br.com.turmaformacaocast.avaliacao_pratica.model.persistence.contracts;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Email;

public final class EmailContract {

    public static final String TABLE = "email_contact";
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String CONTACT_ID = "contact_id";

    public static final String[] COLUMNS = { ID, EMAIL, CONTACT_ID };

    public EmailContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, " );
        create.append(EMAIL + " TEXT NOT NULL, " );
        create.append(CONTACT_ID + " INTEGER ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Email email) {
        ContentValues values = new ContentValues();
        values.put(EmailContract.ID, email.getId());
        values.put(EmailContract.EMAIL, email.getEmail());
        values.put(EmailContract.CONTACT_ID, email.getContactId());

        return values;
    }

    public static Email getEmail(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Email email= new Email();
            email.setId(cursor.getInt(cursor.getColumnIndex(EmailContract.ID)));
            email.setEmail(cursor.getString(cursor.getColumnIndex(EmailContract.EMAIL)));
            email.setContactId(cursor.getInt(cursor.getColumnIndex(EmailContract.CONTACT_ID)));

            return email;
        }
        return null;
    }

    public static List<Email> getEmails(Cursor cursor) {
        ArrayList<Email> emails = new ArrayList<>();
        while (cursor.moveToNext()) {
            emails.add(getEmail(cursor));
        }
        return emails;
    }
}
