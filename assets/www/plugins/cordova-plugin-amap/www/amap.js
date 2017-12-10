cordova.define("cordova-plugin-amap.AMap", function(require, exports, module) {

var cordova = require('cordova');

module.exports = {

	getLocation:function(args,successCallback, errorCallback){
		cordova.exec(successCallback, errorCallback, "AMap", "getLocation",[args])
	}
};


});
