package com.hanict.safepatrol;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MainActivity extends Activity implements OnMapReadyCallback { //NoTitleBar 테마를 사용할경우 APPCOMPATACTIVITY와 충돌, 그냥 ACTIVITY 상속
    private static final String TAG = "MainActivity";

    GoogleMap mMap;
    View view;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //지도 초기값.
    @Override
    public void onMapReady(final GoogleMap map) {
        mMap = map;
        LatLng SEOUL = new LatLng(37.56, 126.97);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 14));
        setCustomMarkView();
        getSampleMarkerItems();
    }

    private void setCustomMarkView() {
        view = LayoutInflater.from(this).inflate(R.layout.marker, null);
        img = (ImageView) view.findViewById(R.id.map_mark);
    }

    private void getSampleMarkerItems() {
        ArrayList<MarkItem> list = new ArrayList<>();
        list.add(new MarkItem(37.565, 126.975, 1));
        list.add(new MarkItem(37.564, 126.975, 2));
        list.add(new MarkItem(37.563, 126.975, 3));

        for (MarkItem m : list) {
            addMark(new LatLng(m.lat, m.lon), m.level);
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


}
