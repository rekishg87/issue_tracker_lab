/**
 * Created by Rekish on 9/24/2015.
 */

// Declare modules
angular.module("Authentication", []);

angular.module("issueTracker", ["Authentication", "ngRoute", "ngCookies", "ngStorage"])
    .constant("loginUrl", "api/user/login")
    .constant("logoutUrl", "api/user/signout")
    .constant("valUrl", "api/issues/getall")
    .constant("signupUrl", "api/user/signup")
    .constant("validateUrl", "api/user/validate")
    .config(['$httpProvider', '$routeProvider', function($httpProvider, $routeProvider) {
        $httpProvider.defaults.withCredentials = true;

        $routeProvider
            .when("/home", {
                controller: "HomeController",
                templateUrl: "views/home.html"
            })

            .when("/login", {
                controller: "loginController",
                templateUrl: "views/login.html"
            })

            .when("/issue", {
                controller: "IssueController",
                templateUrl: "views/issue.html"
            })

            .when("/signup", {
                controller: "SignupController",
                templateUrl: "views/signup.html"
            })

            .otherwise({
                redirectTo: "/home"
            });
    }]);