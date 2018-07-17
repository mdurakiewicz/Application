package example.com.application.gps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Marcin on 17.07.2018.
 */

public class GPSManager {

    public static String getLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double lat, lon;
            try {
                lat = location.getLatitude();
                lon = location.getLongitude();
            } catch (NullPointerException e) {
                lat = -1.0;
                lon = -1.0;
            }

            return new StringBuilder().append(lat).append("; ").append(lon).toString();
        }

        return "No permission";
    }
}
