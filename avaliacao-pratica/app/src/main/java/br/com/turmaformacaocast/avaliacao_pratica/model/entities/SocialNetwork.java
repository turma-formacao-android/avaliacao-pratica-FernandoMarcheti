package br.com.turmaformacaocast.avaliacao_pratica.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

public class SocialNetwork implements Parcelable {

    private Integer id;
    private String name;
    private String url;
    private  Integer contactId;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

        SocialNetwork that = (SocialNetwork) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        return !(contactId != null ? !contactId.equals(that.contactId) : that.contactId != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
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
        dest.writeString(this.name == null ? "" : this.name);
        dest.writeString(this.url == null ? "" : this.url);
        dest.writeValue(this.contactId == null ? -1 : this.contactId);
    }

    public SocialNetwork() {
    }

    protected SocialNetwork(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.url = in.readString();
        this.contactId = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<SocialNetwork> CREATOR = new Parcelable.Creator<SocialNetwork>() {
        public SocialNetwork createFromParcel(Parcel source) {
            return new SocialNetwork(source);
        }

        public SocialNetwork[] newArray(int size) {
            return new SocialNetwork[size];
        }
    };
}
