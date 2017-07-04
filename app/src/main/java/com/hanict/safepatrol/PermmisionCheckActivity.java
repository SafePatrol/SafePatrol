package com.hanict.safepatrol;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by answo on 2017-07-02.
 */

public class PermmisionCheckActivity extends Activity {
    final String TAG = "Permmision_Activity";
    final int MY_LOCATION_REQUEST_CODE = 100;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toast.makeText(this, "권한동의가 필요합니다.", Toast.LENGTH_SHORT);
        permissionCheck();

    }
    private  void permissionCheck(){

       if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG,"GPS Permission Allow");
           Intent i = new Intent(this,MainActivity.class);
           startActivity(i);
           finish();


        } else {
            // Show rationale and request permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_LOCATION_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if ( permissions.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e(TAG,"Permission Allow");
                Toast.makeText(this, "GPS 권한이 허용되었습니다.", Toast.LENGTH_SHORT);
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
                finish();
            }
            else {
                // Permission was denied. Display an error message.
                Log.e(TAG,"Permission denied");
                Toast.makeText(this,"GPS 권한이 거부되었습니다.",Toast.LENGTH_SHORT);
                finish();
            }
        }

    }
}
