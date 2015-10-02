package br.com.turmaformacaocast.avaliacao_pratica.model.persistence.contracts;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Address;

public final class AddressContract {

    public static final String TABLE = "address_contact";
    public static final String ID = "id";
    public static final String ZIPE_CODE = "zipe_code";
    public static final String STREET_TYPE = "street_type";
    public static final String STREET = "street";
    public static final String NEIGHBORHOOD = "neighborhood";
    public static final String CITY = "city";
    public static final String STATE = "state";

    public static final String[] COLUMNS = { ID, ZIPE_CODE, STREET_TYPE, STREET, NEIGHBORHOOD, CITY, STATE };

    public AddressContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, " );
        create.append(ZIPE_CODE + " TEXT NOT NULL, " );
        create.append(STREET_TYPE + " TEXT, " );
        create.append(STREET + " TEXT, " );
        create.append(NEIGHBORHOOD + " TEXT, " );
        create.append(CITY + " TEXT, " );
        create.append(STATE + " TEXT ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Address address) {
        ContentValues values = new ContentValues();
        values.put(AddressContract.ID, address.getId());
        values.put(AddressContract.ZIPE_CODE, address.getZipeCode());
        values.put(AddressContract.STREET_TYPE, address.getStreetType());
        values.put(AddressContract.STREET, address.getStreet());
        values.put(AddressContract.NEIGHBORHOOD, address.getNeighborhood());
        values.put(AddressContract.CITY, address.getCity());
        values.put(AddressContract.STATE, address.getState());

        return values;
    }

    public static Address getAddress(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Address address = new Address();
            address.setId(cursor.getInt(cursor.getColumnIndex(AddressContract.ID)));
            address.setZipeCode(cursor.getString(cursor.getColumnIndex(AddressContract.ZIPE_CODE)));
            address.setStreetType(cursor.getString(cursor.getColumnIndex(AddressContract.STREET_TYPE)));
            address.setStreet(cursor.getString(cursor.getColumnIndex(AddressContract.STREET)));
            address.setNeighborhood(cursor.getString(cursor.getColumnIndex(AddressContract.NEIGHBORHOOD)));
            address.setCity(cursor.getString(cursor.getColumnIndex(AddressContract.CITY)));
            address.setState(cursor.getString(cursor.getColumnIndex(AddressContract.STATE)));

            return address;
        }
        return null;
    }

    public static List<Address> getAddresses(Cursor cursor) {
        ArrayList<Address> addresses = new ArrayList<>();
        while (cursor.moveToNext()) {
            addresses.add(getAddress(cursor));
        }
        return addresses;
    }

}
