package com.hanict.safepatrol;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends Activity implements OnMapReadyCallback { //NoTitleBar 테마를 사용할경우 APPCOMPATACTIVITY와 충돌, 그냥 ACTIVITY 상속
    private static final String TAG = "Main_job";
    LatLng myLocation;
    GoogleMap mMap;
    View view;
    ImageView img;
    ClusterManager<MarkItem> mClusterManager;
    final int MY_LOCATION_REQUEST_CODE = 100;
    MyLocation.LocationResult locationResult = new MyLocation.LocationResult() { //MyLocation.java 활용한 위치값 받아오기.
        @Override
        public void gotLocation(Location location) {
            Log.e(TAG,"hear is gotLocation");
            myLocation = new LatLng(location.getLatitude(),location.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 14));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionCheck();
        // 2017년 SeonWooHan 2017년 07월 03일 부분 수정 - 버튼 이벤트 넣기
        Button GoSetting = (Button)findViewById(R.id.go_setting_button);
        GoSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        //2017년 SeonWooHan 수정 종료 지점

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    private  void permissionCheck(){

        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG,"GPS Permission Allow");

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                    100, // 통지사이의 최소 시간간격 (miliSecond)
                    1, // 통지사이의 최소 변경거리 (m)
                    mLocationListener);
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
                Toast.makeText(this,"GPS 권한이 허용되었습니다.",Toast.LENGTH_SHORT);

            }
            else {
                // Permission was denied. Display an error message.
                Log.e(TAG,"Permission denied");
                Toast.makeText(this,"GPS 권한이 거부되었습니다.",Toast.LENGTH_SHORT);
               finish();
            }
        }

    }


    //GPS 리스너
    private final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
                //위치가 바뀌면 여기서 처리
            myLocation = new LatLng(location.getLatitude(),location.getLongitude());
            Toast.makeText(MainActivity.this, "위치 바뀜", Toast.LENGTH_SHORT).show();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 16));
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
    };

    //지도 초기값.
    @Override
    public void onMapReady(final GoogleMap map) {
        mMap = map;
        setCustomMarkView();
        mClusterManager = new ClusterManager<MarkItem>(this,mMap);
        mMap.setOnCameraChangeListener(mClusterManager); //이거 대신에 setOnCameraIdle~~~ 쓰라는데 왜 안써지는지 모르겠다.
        mMap.setMaxZoomPreference(16);
        mMap.setMinZoomPreference(10);
        mMap.getUiSettings().setRotateGesturesEnabled(false);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG,"myLocation is saved");
            mMap.setMyLocationEnabled(true);
            MyLocation ml = new MyLocation();
            ml.getLocation(this, locationResult);
            getSampleMarkerItems();

        }
        else if(myLocation == null){
            Log.e(TAG,"myLocation is null!!");
            myLocation = new LatLng(37.565,126.975);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 14));
            getSampleMarkerItems();
        }
    }

    private void setCustomMarkView() {
        view = LayoutInflater.from(this).inflate(R.layout.marker, null);
        img = (ImageView) view.findViewById(R.id.map_mark);
    }

    private void getSampleMarkerItems() {
        ArrayList<MarkItem> list = new ArrayList<>();
        Random rnd = new Random();


        for(int i = 0 ; i < 20 ; i++){

            double j = rnd.nextInt(1000)*0.0001;
            list.add(new MarkItem(37.057774+j, 127.072765-j, 1));
            list.add(new MarkItem(37.057774+j, 127.072765+j, 2));
            list.add(new MarkItem(37.057774-j, 127.072765+j, 3));
        }

        for (MarkItem m : list) {
           // addMark(new LatLng(m.lat, m.lon), m.level);
            mClusterManager.addItem(new MarkItem(m.lat,m.lon,m.level));
            mClusterManager.setRenderer(new CustomMarkRendered(this,mMap,mClusterManager));
        }

    }


    //마커 찍기. 경위도 위치 + 사고레벨
    public void addMark(LatLng location, int level) {
        switch (level) {
            case 1:
                img.setImageResource(R.drawable.clean);
                break;
            case 2:
                img.setImageResource(R.drawable.caution);
                break;
            case 3:
                img.setImageResource(R.drawable.warning);
                break;
        }

        MarkerOptions options = new MarkerOptions();
        options.position(location);
        options.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this,view)));

        mMap.addMarker(options);

    }

    private Bitmap createDrawableFromView(Context context, View view) {
        //뷰를 비트맵으로 변환시키는 작업이래. 마크를 하나의 뷰에다가 만든담에 그걸 비트맵이미지로 변환시켜서 마크에 추가
        //컨텍스트, 마크 작업한 View
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();

    }


}
