package br.com.turmaformacaocast.avaliacao_pratica.model.persistence.contracts;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Address;
import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Contact;

public final class ContactContract {

    public static final String TABLE = "contact";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHONE_ID = "phone_id";
    public static final String EMAIL_ID = "email_id";
    public static final String SOCIALNETWORK_ID = "socialnetwork_id";
    public static final String ADDRESS_ID = "address_id";
    public static final String WEB_ID = "web_id";

    public static final String[] COLUMNS = { ID, NAME, ADDRESS_ID, WEB_ID };//PHONE_ID, EMAIL_ID, SOCIALNETWORK_ID,

    public ContactContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, " );
        create.append(NAME + " TEXT NOT NULL, " );
        create.append(ADDRESS_ID + " INTEGER, " );
        create.append(WEB_ID + " INTEGER ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(ContactContract.ID, contact.getId());
        values.put(ContactContract.NAME, contact.getName());
        //values.put(ContactContract.ADDRESS_ID, contact.getAddress().getId());

        return values;
    }

    public static Contact getContact(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setId(cursor.getInt(cursor.getColumnIndex(ContactContract.ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(ContactContract.NAME)));

            int idAddress = cursor.getInt(cursor.getColumnIndex(ContactContract.ADDRESS_ID));
            if(idAddress > 0){
                Address address = new Address();
                address.setId(idAddress);
                contact.setAddress(address);
            }

            return contact;
        }
        return null;
    }

    public static List<Contact> getContacts(Cursor cursor) {
        ArrayList<Contact> contacts = new ArrayList<>();
        while (cursor.moveToNext()) {
            contacts.add(getContact(cursor));
        }
        return contacts;
    }
}
