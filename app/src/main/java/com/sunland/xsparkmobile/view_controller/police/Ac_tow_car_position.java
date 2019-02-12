package com.sunland.xsparkmobile.view_controller.police;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.view_controller.Ac_base;

import butterknife.BindView;

public class Ac_tow_car_position extends Ac_base implements AMap.OnMapLoadedListener {

    @BindView(R.id.map)
    public MapView mMapView;
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_tow_car_position);
        showLightStatusBar();
        showToolBar(false);

        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        aMap = mMapView.getMap();
        aMap.setOnMapLoadedListener(this);

    }

    @Override
    public void onMapLoaded() {
        LatLng curLatLng = new LatLng(30.1908191706, 120.2854442596);
        aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(curLatLng, 18f, 0, 0)));
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_tow_truck));
        aMap.addMarker(new MarkerOptions().position(curLatLng).title("拖车").snippet("浙A·TC179")
                .icon(bitmapDescriptor));
    }
}
