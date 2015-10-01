package br.com.turmaformacaocast.avaliacao_pratica.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable {

    private Integer id;
    private String zipeCode;
    private String streetType;
    private String street;
    private String neighborhood;
    private String city;
    private String state;


    public Address() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZipeCode() {
        return zipeCode;
    }

    public void setZipeCode(String zipeCode) {
        this.zipeCode = zipeCode;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (id != null ? !id.equals(address.id) : address.id != null) return false;
        if (zipeCode != null ? !zipeCode.equals(address.zipeCode) : address.zipeCode != null)
            return false;
        if (streetType != null ? !streetType.equals(address.streetType) : address.streetType != null)
            return false;
        if (street != null ? !street.equals(address.street) : address.street != null)
            return false;
        if (neighborhood != null ? !neighborhood.equals(address.neighborhood) : address.neighborhood != null)
            return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        return !(state != null ? !state.equals(address.state) : address.state != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (zipeCode != null ? zipeCode.hashCode() : 0);
        result = 31 * result + (streetType != null ? streetType.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (neighborhood != null ? neighborhood.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.zipeCode);
        dest.writeString(this.streetType);
        dest.writeString(this.street);
        dest.writeString(this.neighborhood);
        dest.writeString(this.city);
        dest.writeString(this.state);
    }

    protected Address(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.zipeCode = in.readString();
        this.streetType = in.readString();
        this.street = in.readString();
        this.neighborhood = in.readString();
        this.city = in.readString();
        this.state = in.readString();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
