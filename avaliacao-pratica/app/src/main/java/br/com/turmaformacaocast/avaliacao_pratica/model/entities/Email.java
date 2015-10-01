package br.com.turmaformacaocast.avaliacao_pratica.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

public class Email implements Parcelable {

    private Integer id;
    private String email;
    private  Integer contactId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

        Email email1 = (Email) o;

        if (id != null ? !id.equals(email1.id) : email1.id != null) return false;
        if (email != null ? !email.equals(email1.email) : email1.email != null) return false;
        return !(contactId != null ? !contactId.equals(email1.contactId) : email1.contactId != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
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
        dest.writeString(this.email == null ? "" : this.email);
        dest.writeValue(this.contactId == null ? -1 : this.contactId);
    }

    public Email() {
    }

    protected Email(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.email = in.readString();
        this.contactId = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Email> CREATOR = new Parcelable.Creator<Email>() {
        public Email createFromParcel(Parcel source) {
            return new Email(source);
        }

        public Email[] newArray(int size) {
            return new Email[size];
        }
    };
}
