/**
 * Created by Rekish on 9/24/2015.
 */

// Declare modules
angular.module("Authentication", []);

angular.module("issueTracker", ["Authentication", "ngRoute", "ngCookies", "ngStorage"])
    .constant("dataUrl", "api/user/login")
    .config(['$httpProvider', '$routeProvider', function($httpProvider, $routeProvider) {
        $httpProvider.defaults.withCredentials = true;

        $routeProvider
            .when("/home", {
                templateUrl: "views/home.html"
            })

            .when("/login", {
                controller: "loginController",
                templateUrl: "views/login.html"
            })

            .otherwise({
                redirectTo: "/login"
            });
    }]);