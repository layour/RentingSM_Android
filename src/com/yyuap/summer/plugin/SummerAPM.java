package com.yyuap.summer.plugin;


import com.yonyou.uap.apm.utils.ApmService;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SummerAPM extends CordovaPlugin {

    private static final String PLUGIN_VERSION = "3.0";
    private static final String METHOD_GET_PLUGIN_VERSION = "getPluginVersion";
    
    @Override
    public boolean execute(String action, final JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("insertAction")) {
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    if (args != null && args.length() >= 2) {
                        ApmService.getInstance(cordova.getActivity()).colectActionInfo(args.optString(0), args.optString(1));
                    }
                }
            });
            return true;
        } else if (action.equals("getPluginVersion")) {
            JSONObject json = new JSONObject();
            try {
                json.put("result", PLUGIN_VERSION);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            PluginResult r = new PluginResult(PluginResult.Status.OK, json);
            callbackContext.sendPluginResult(r);
            return true;
        }
        return false;
    }
}