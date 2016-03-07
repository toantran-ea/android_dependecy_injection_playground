package mobi.toan.locationupdatersample;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * This main screen will display the latest coordinates received from GPS receiver.
 * It will also display the number of location updates pushed to the app.
 */
public class MainActivity extends AppCompatActivity implements LocationListener {
    private TextView lastCoordTextView;
    private TextView numberOfLocationUpdates;

    private LocationManager locationManager;
    private String provider;

    private List<LocationUpdate> locationUpdates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lastCoordTextView = (TextView) findViewById(R.id.last_coord_textview);
        numberOfLocationUpdates = (TextView) findViewById(R.id.update_number_textview);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        // Define the criteria how to select the location provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onReceiveLocation(fromRawUpdate(location));
        } else {
            lastCoordTextView.setText("N/A");
        }
    }

    private void onReceiveLocation(LocationUpdate locationUpdate) {
        locationUpdates.add(locationUpdate);
        numberOfLocationUpdates.setText(String.format("There are %s location updates received",
                locationUpdates.size()));
        lastCoordTextView.setText(String.format("Last coordinates received %s/%s",
                locationUpdate.getLongitude(),
                locationUpdate.getLattitude()));
    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }


    private LocationUpdate fromRawUpdate(Location location) {
        return new LocationUpdate(location.getLongitude(),
                location.getLatitude(),
                location.getAltitude(),
                "" + SystemClock.currentThreadTimeMillis());
    }

    @Override
    public void onLocationChanged(Location location) {
        onReceiveLocation(fromRawUpdate(location));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
