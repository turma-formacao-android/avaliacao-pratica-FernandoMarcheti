package br.com.turmaformacaocast.avaliacao_pratica.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

public class Phone implements Parcelable {

    private Integer id;
    private String phone;
    private  Integer contactId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone1 = (Phone) o;

        if (id != null ? !id.equals(phone1.id) : phone1.id != null) return false;
        if (phone != null ? !phone.equals(phone1.phone) : phone1.phone != null) return false;
        return !(contactId != null ? !contactId.equals(phone1.contactId) : phone1.contactId != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (contactId != null ? contactId.hashCode() : 0);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id == null ? -1 : this.id);
        dest.writeString(this.phone == null ? "" : this.phone);
        dest.writeValue(this.contactId == null ? -1 : this.contactId);
    }

    public Phone() {
    }

    protected Phone(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.phone = in.readString();
        this.contactId = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Phone> CREATOR = new Parcelable.Creator<Phone>() {
        public Phone createFromParcel(Parcel source) {
            return new Phone(source);
        }

        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };
}
