package br.com.turmaformacaocast.avaliacao_pratica.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Contact implements Parcelable {

    private Integer id;
    private String name;
    private List<Phone> phones;
    private List<Email> emails;
    private List<SocialNetwork> socialNetworks;
    private Address address;
    private Integer webId;

    public Contact() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<SocialNetwork> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(List<SocialNetwork> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getWebId() {
        return webId;
    }

    public void setWebId(Integer webId) {
        this.webId = webId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != null ? !id.equals(contact.id) : contact.id != null) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (phones != null ? !phones.equals(contact.phones) : contact.phones != null) return false;
        if (emails != null ? !emails.equals(contact.emails) : contact.emails != null) return false;
        if (socialNetworks != null ? !socialNetworks.equals(contact.socialNetworks) : contact.socialNetworks != null)
            return false;
        if (address != null ? !address.equals(contact.address) : contact.address != null)
            return false;
        return !(webId != null ? !webId.equals(contact.webId) : contact.webId != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phones != null ? phones.hashCode() : 0);
        result = 31 * result + (emails != null ? emails.hashCode() : 0);
        result = 31 * result + (socialNetworks != null ? socialNetworks.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (webId != null ? webId.hashCode() : 0);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id == null ? -1 : this.id);
        dest.writeString(this.name == null ? "" : this.name);
        dest.writeTypedList(phones == null ? null : phones);
        dest.writeTypedList(emails == null ? null : emails);
        dest.writeTypedList(socialNetworks == null ? null : socialNetworks);
        dest.writeParcelable(this.address, flags);
        dest.writeValue(this.webId == null ? -1 : this.webId);
    }

    protected Contact(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.phones = in.createTypedArrayList(Phone.CREATOR);
        this.emails = in.createTypedArrayList(Email.CREATOR);
        this.socialNetworks = in.createTypedArrayList(SocialNetwork.CREATOR);
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.webId = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
