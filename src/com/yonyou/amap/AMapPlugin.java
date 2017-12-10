package com.yonyou.amap;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.yonyou.uap.um.util.SimpleCallback;

import org.json.JSONObject;

/**
 * Created by houmy on 2017/8/14.
 */

public class AMapPlugin {


    public static void getLocation(Context ctx, JSONObject args, final SimpleCallback callback) {
        AMapLocationClient mLocationClient = null;
        AMapLocationListener mLocationListener = null;

//        if(mLocationClient != null) {
//            if(mLocationListener != null) {
//                mLocationClient.unRegisterLocationListener(mLocationListener);
//            }
//            mLocationClient.stopLocation();
//            mLocationClient.onDestroy();
//        }
        mLocationClient = new AMapLocationClient(ctx);

        AMapLocationClientOption option = new AMapLocationClientOption();
        if(args != null){
            boolean once = Boolean.parseBoolean(args.optString("isSingle","false"));
            option.setOnceLocation(once);
//        option.setOnceLocationLatest(true);
        }
        mLocationClient.setLocationOption(option);

        mLocationListener = new AMapLocationListener() {

            @Override
            public void onLocationChanged(AMapLocation location) {
                if (location != null) {
                    JSONObject result = new JSONObject();
                    try{
                        result.put("address", location.getAddress());
                        result.put("经度", location.getLongitude());
                        result.put("纬度", location.getLatitude());
                        result.put("longitude", location.getLongitude());
                        result.put("latitude", location.getLatitude());
                        result.put("accuracy", location.getAccuracy());
                        result.put("errorcode", location.getErrorCode());
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(callback != null) {
                        callback.callback(0, result);
                    }
                }
            }
        };

        mLocationClient.setLocationListener(mLocationListener);
        if(mLocationClient != null) {
            mLocationClient.startLocation();
        }

    }

}
