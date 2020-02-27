package id.putraprima.skorbola.model;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class DataTim implements Parcelable {
    private String homeName;
    private String awayName;
    private String homeLogo;
    private String awayLogo;


    public DataTim(String homeName, String awayName, String homeLogo, String awayLogo) {
        this.homeName = homeName;
        this.awayName = awayName;
        this.homeLogo = homeLogo;
        this.awayLogo = awayLogo;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public void setAwayName(String awayName) {
        this.awayName = awayName;
    }

    public String getHomeLogo() {
        return homeLogo;
    }

    public void setHomeLogo(String homeLogo) {
        this.homeLogo = homeLogo;
    }

    public String getAwayLogo() {
        return awayLogo;
    }

    public void setAwayLogo(String awayLogo) {
        this.awayLogo = awayLogo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.homeName);
        dest.writeString(this.awayName);
        dest.writeString(this.homeLogo);
        dest.writeString(this.awayLogo);
    }

    protected DataTim(Parcel in) {
        this.homeName = in.readString();
        this.awayName = in.readString();
        this.homeLogo = in.readString();
        this.awayLogo = in.readString();
    }

    public static final Creator<DataTim> CREATOR = new Creator<DataTim>() {
        @Override
        public DataTim createFromParcel(Parcel source) {
            return new DataTim(source);
        }

        @Override
        public DataTim[] newArray(int size) {
            return new DataTim[size];
        }
    };
}
