package br.com.turmaformacaocast.avaliacao_pratica.model.persistence.contracts;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Phone;

public final class PhoneContract {

    public static final String TABLE = "contact_phone";
    public static final String ID = "id";
    public static final String PHONE = "phone";
    public static final String CONTACT_ID = "contact_id";

    public static final String[] COLUMNS = { ID, PHONE, CONTACT_ID };

    public PhoneContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, " );
        create.append(PHONE + " TEXT NOT NULL, " );
        create.append(CONTACT_ID + " INTEGER ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Phone phone) {
        ContentValues values = new ContentValues();
        values.put(PhoneContract.ID, phone.getId());
        values.put(PhoneContract.PHONE, phone.getPhone());
        values.put(PhoneContract.CONTACT_ID, phone.getContactId());

        return values;
    }

    public static Phone getPhone(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Phone phone = new Phone();
            phone.setId(cursor.getInt(cursor.getColumnIndex(PhoneContract.ID)));
            phone.setPhone(cursor.getString(cursor.getColumnIndex(PhoneContract.PHONE)));
            phone.setContactId(cursor.getInt(cursor.getColumnIndex(PhoneContract.CONTACT_ID)));

            return phone;
        }
        return null;
    }

    public static List<Phone> getPhones(Cursor cursor) {
        ArrayList<Phone> phones = new ArrayList<>();
        while (cursor.moveToNext()) {
            phones.add(getPhone(cursor));
        }
        return phones;
    }
}
