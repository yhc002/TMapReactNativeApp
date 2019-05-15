package com.t_map_app;

import android.content.Context;
import android.util.Log;

import com.facebook.react.bridge.ReadableArray;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;


public class MapView extends TMapView {

    private double centerLat=37.4000;//중심좌표 latitude
    private double centerLong=127.5000;//중심좌표 longtitude

    public void markCoordinate(ReadableArray coordinate)
    {
        Log.d("mark","called from mark:"+coordinate.getDouble(0)+", "+coordinate.getDouble(1) );
        TMapPoint tMapPoint = new TMapPoint(coordinate.getDouble(0),coordinate.getDouble(1));
        TMapMarkerItem markerItem = new TMapMarkerItem();
        markerItem.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
        markerItem.setTMapPoint( tMapPoint ); // 마커의 좌표 지정
        markerItem.setName("Luggage"); // 마커의 타이틀 지정

        markerItem.setCalloutTitle("Marker");//풍선뷰에 표시될 메세지
        markerItem.setCanShowCallout(true); //풍선뷰 사용

        this.addMarkerItem("Luggage", markerItem); // 지도에 마커 추가
    }

    public void setMapCenter(ReadableArray coordinate)
    {
        this.centerLat=coordinate.getDouble(0);
        this.centerLong=coordinate.getDouble(1);
        Log.d("setCenter","called from setMapCenter: "+this.centerLat);
        this.setCenterPoint(this.centerLat,this.centerLong);
    }
    public void addMarker(ReadableArray orderList)
    {
        Log.d("view","called from view(coord)"+orderList.getMap(0).getDouble("startLat")+", "+orderList.getMap(0).getDouble("startLong") );
        Log.d("view2","called from view(name)"+orderList.getMap(0).getString("startAddr") );
        for(int i=0;i<orderList.size();i++)
        {
            TMapPoint tMapPointS = new TMapPoint(orderList.getMap(i).getDouble("startLong"),orderList.getMap(i).getDouble("startLat"));
            TMapMarkerItem markerItemS = new TMapMarkerItem();
            markerItemS.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
            markerItemS.setTMapPoint( tMapPointS ); // 마커의 좌표 지정
            markerItemS.setName("orderStart"+i); // 마커의 타이틀 지정

            markerItemS.setCalloutTitle(orderList.getMap(i).getString("startAddr"));//풍선뷰에 표시될 메세지
            markerItemS.setCanShowCallout(true); //풍선뷰 사용

            this.addMarkerItem("orderStart"+i, markerItemS); // 지도에 마커 추가

            TMapPoint tMapPointE = new TMapPoint(orderList.getMap(i).getDouble("endLong"), orderList.getMap(i).getDouble("endLat"));
            TMapMarkerItem markerItemE = new TMapMarkerItem();
            markerItemE.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
            markerItemE.setTMapPoint( tMapPointE ); // 마커의 좌표 지정
            markerItemE.setName("orderEnd"+i); // 마커의 타이틀 지정

            markerItemE.setCalloutTitle(orderList.getMap(i).getString("endAddr"));//풍선뷰에 표시될 메세지
            markerItemE.setCanShowCallout(true); //풍선뷰 사용
            this.addMarkerItem("orderEnd"+i, markerItemE); // 지도에 마커 추가
        }

    }

    public void addRoute(ReadableArray coordinates)
    {
        final TMapView tMapView=this;
        Log.d("addRoute","addRoute initiated");
        TMapPoint tMapPointStart = new TMapPoint(coordinates.getDouble(0), coordinates.getDouble(1));
        TMapPoint tMapPointEnd = new TMapPoint(coordinates.getDouble(2), coordinates.getDouble(3));

        TMapData tmapdata = new TMapData();

        tmapdata.findPathData(tMapPointStart, tMapPointEnd, new TMapData.FindPathDataListenerCallback() {

            @Override
            public void onFindPathData(TMapPolyLine polyLine) {
                tMapView.addTMapPath(polyLine);
            }
        });

    }
    public MapView(Context context) {
        super(context);
        this.setSKTMapApiKey("map_api_key");
        Log.d("construct","called from constructor: "+this.centerLat);
        this.setZoom(12);
        this.setCenterPoint(this.centerLat,this.centerLong);
    }

}

