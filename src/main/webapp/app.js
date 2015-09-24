/**
 * Created by Rekish on 9/24/2015.
 */

// Declare modules
angular.module("Authentication", []);

angular.module("issueTracker", ["Authentication", "ngCookies", "ngStorage"])
    .constant("dataUrl", "api/user/login")
    .config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
    }]);