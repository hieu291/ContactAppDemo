package app.contact.com.contactapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
    private boolean isMale;

    public Contact(boolean isMale, String mName, String mPhone) {
        this.isMale = isMale;
        this.mName = mName;
        this.mPhone = mPhone;
    }

    private String mName;

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    private String mPhone;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isMale ? (byte) 1 : (byte) 0);
        dest.writeString(this.mName);
        dest.writeString(this.mPhone);
    }

    protected Contact(Parcel in) {
        this.isMale = in.readByte() != 0;
        this.mName = in.readString();
        this.mPhone = in.readString();
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
