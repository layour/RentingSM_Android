cordova.define("cordova-plugin-summer-personscan.PersonScan", function(require, exports, module) {
var argscheck = require('cordova/argscheck'),
    exec      = require('cordova/exec');

function PersonScan () {};

PersonScan.prototype = {

    scan: function (params, success, failure)
    {
        params = params || {};
        exec(success, failure, 'PersonScan', 'personscan', [params]);
        
    },

};

module.exports = new PersonScan;

});
