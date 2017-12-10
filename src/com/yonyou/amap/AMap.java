package com.yonyou.amap;

import com.yonyou.uap.um.util.SimpleCallback;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by houmy on 2017/8/14.
 */

public class AMap extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext)
            throws JSONException {


        if("getLocation".equalsIgnoreCase(action)){
            AMapPlugin.getLocation(cordova.getActivity(), args.getJSONObject(0), new SimpleCallback() {
                @Override
                public void callback(int i, Object o) {
                    if(o instanceof JSONObject){
                        callbackContext.success((JSONObject)o);
                    }
                }
            });
            return true;
        }
        return false;
    }
}
