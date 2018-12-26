package boisestate.edu.a06_locations

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

        if( permissionCheck == PackageManager.PERMISSION_GRANTED){

            // Get location
            startReportingLocations()
        }
        else{
            requestPermissions()
        }


    }

    private fun requestPermissions(){

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 444)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if( grantResults[0] == PackageManager.PERMISSION_GRANTED){
            startReportingLocations()
        }
    }

    private fun startReportingLocations(){
        val locationManager:LocationManager = this.getSystemService(android.content.Context.LOCATION_SERVICE) as LocationManager

        val provider = locationManager.getProvider(LocationManager.GPS_PROVIDER)


        // Retrieve a list of location providers that have fine accuracy, no monetary cost, etc
        val criteria = Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setCostAllowed(false);

        val providerName = locationManager.getBestProvider(criteria, true);

        if (providerName != null) {
            val permission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)

            if (permission != PackageManager.PERMISSION_GRANTED) {
                Log.i("BSU", "Permission to denied")
                requestPermissions()
            }
            else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 1000.0f, locationListener)
            }
        }
    }


    private var locationListener:LocationListener = object : LocationListener{
        override fun onLocationChanged(provider: Location?) {

            if(provider != null  ) {
                Log.d("BSU", "${provider.latitude}, ${provider.longitude}")
            }

        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

        }

        override fun onProviderEnabled(p0: String?) {

        }

        override fun onProviderDisabled(p0: String?) {

        }
    }
}
