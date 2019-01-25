package com.sunland.xsparkmobile.view_controller.tow_car;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.view_controller.Ac_base;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Ac_Nav extends Ac_base implements AMapLocationListener, AMap.InfoWindowAdapter {

    @BindView(R.id.address)
    public TextView tv_address;
    private PoiOverlay poiOverlay;
    private AMap mMap;
    private PoiSearch mPoiSearch;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private Marker mLocationMarker;
    private Circle mLocationCircle;
    private AMapLocation mCurrentLocation;
    private PoiSearch.Query query;
    private final int REQUEST_ADDRESS_CODE = 0;
    private String city_code;
    private Marker mPoiMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac__nav);
        showToolBar(false);
        setUpMapIfNeeded();
        initLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    @OnClick({R.id.task_icon, R.id.address})
    public void OnClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.task_icon:
                hopToActivityForResult(Ac_task_list.class, REQUEST_ADDRESS_CODE);
                break;
            case R.id.address:
                Bundle bundle = new Bundle();
                bundle.putString("city_code", city_code);
                hopToActivityForResult(Ac_address_tips.class, bundle, REQUEST_ADDRESS_CODE);
                break;
        }
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map)).getMap();
            mMap.setInfoWindowAdapter(this);
        }
    }


    private void destroyLocation() {
        if (mLocationClient != null) {
            mLocationClient.unRegisterLocationListener(this);
            mLocationClient.onDestroy();
        }
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationClient = new AMapLocationClient(this.getApplicationContext());
        mLocationClient.setLocationListener(this);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

        if (aMapLocation == null || aMapLocation.getErrorCode() != AMapLocation.LOCATION_SUCCESS) {
            Toast.makeText(this, aMapLocation.getErrorInfo() + "  " + aMapLocation.getErrorCode(), Toast.LENGTH_LONG).show();
            return;
        }

        mCurrentLocation = aMapLocation;
        city_code = aMapLocation.getCityCode();
        LatLng curLatLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
        if (mLocationMarker == null) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(curLatLng);
            markerOptions.anchor(0.5f, 0.5f);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.navi_map_gps_locked));
            mLocationMarker = mMap.addMarker(markerOptions);
        }
        if (mLocationCircle == null) {
            CircleOptions circleOptions = new CircleOptions();
            circleOptions.center(curLatLng);
            circleOptions.radius(aMapLocation.getAccuracy());
            circleOptions.strokeWidth(2);
            circleOptions.strokeColor(getResources().getColor(R.color.stroke));
            circleOptions.fillColor(getResources().getColor(R.color.fill));
            mLocationCircle = mMap.addCircle(circleOptions);
        }
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(curLatLng, 18f, 0, 0)));

    }

    private void doPoiSearch(String keyword) {
        query = new PoiSearch.Query(keyword, "", city_code);
        query.setPageNum(1);
        query.setPageSize(10);
        mPoiSearch = new PoiSearch(this, query);
        mPoiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                if (i == 1000) {
                    if (poiResult != null && poiResult.getQuery() != null) {
                        if (poiResult.getQuery().equals(query)) {
                            poiResult = poiResult;
                            // 取得搜索到的poiitems有多少页
                            List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                            List<SuggestionCity> suggestionCities = poiResult
                                    .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息

                            if (poiItems != null && poiItems.size() > 0) {
//                                mMap.clear();// 清理之前的图标
                                poiOverlay = new PoiOverlay(mMap, poiItems);
                                poiOverlay.removeFromMap();
                                poiOverlay.addToMap();
                                poiOverlay.zoomToSpan();
                            } else if (suggestionCities != null
                                    && suggestionCities.size() > 0) {
//                                showSuggestCity(suggestionCities);
                            } else {
//                                ToastUtil.show(MainActivity.this,
//                                        R.string.no_result);
                            }
                        }
                    }

                } else {
//                    ToastUtil.showerror(this, i);
                }
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {

            }
        });
        mPoiSearch.searchPOIAsyn();
    }

    /**
     * 用marker展示输入提示list选中数据
     *
     * @param tip
     */
    private void addTipMarker(Tip tip) {
        if (tip == null) {
            return;
        }

        mPoiMarker = mMap.addMarker(new MarkerOptions());
        LatLonPoint point = tip.getPoint();
        if (point != null) {
            LatLng markerPosition = new LatLng(point.getLatitude(), point.getLongitude());
            mPoiMarker.setPosition(markerPosition);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerPosition, 17));
        }
//        mPoiMarker.setTitle(tip.getName());
//        mPoiMarker.setSnippet(tip.getAddress());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADDRESS_CODE) {
            switch (resultCode) {
                case Constants.RESULT_OF_KEYWORD:
                    String keyWord = data.getStringExtra(Constants.KEY_WORDS_NAME);
                    tv_address.setText(keyWord);
                    if (keyWord != null && !keyWord.isEmpty()) {
                        doPoiSearch(keyWord);
                    }
                    break;
                case Constants.RESULT_OF_TIP:
                    Tip tip = data.getParcelableExtra(Constants.EXTRA_TIP);
                    tv_address.setText(tip.getName() + tip.getAddress());
                    String id = tip.getPoiID();
                    if (id == null || id.isEmpty()) {
                        doPoiSearch(tip.getName());
                    } else {
                        addTipMarker(tip);
                    }
                    break;
            }
        }
    }

    /**
     * 自定义marker点击弹窗内容
     *
     * @param marker
     * @return
     */
    @Override
    public View getInfoWindow(final Marker marker) {
        View view = getLayoutInflater().inflate(R.layout.poikeywordsearch_uri,
                null);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(marker.getTitle());
        TextView snippet = (TextView) view.findViewById(R.id.snippet);
        int index = poiOverlay.getPoiIndex(marker);
        float distance = poiOverlay.getDistance(index);
//        String showDistance = Utils.getFriendlyDistance((int) distance);
//        snippet.setText("距当前位置" + showDistance);
        ImageButton button = (ImageButton) view
                .findViewById(R.id.start_amap_app);
        // 调起导航
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAMapNavi(marker);
            }
        });
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    /**
     * 点击一键导航按钮跳转到导航页面
     *
     * @param marker
     */
    private void startAMapNavi(Marker marker) {
        if (mCurrentLocation == null) {
            return;
        }
        Intent intent = new Intent(this, RouteNaviActivity.class);
        intent.putExtra("gps", false);
        intent.putExtra("start", new NaviLatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()));
        intent.putExtra("end", new NaviLatLng(marker.getPosition().latitude, marker.getPosition().longitude));
        startActivity(intent);
    }
}
