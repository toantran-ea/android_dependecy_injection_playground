package mobi.toan.locationupdatersample;

/**
 * Created by toantran on 3/7/16.
 */
public class LocationUpdate {
    private double longitude;
    private double lattitude;
    private double altitude;
    private String timestamp;

    public LocationUpdate(double longitude, double lattitude, double altitude, String timestamp) {
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.altitude = altitude;
        this.timestamp = timestamp;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
