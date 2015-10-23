/**
 * Created by Rekish on 9/24/2015.
 */

// Declare modules
angular.module("Authentication", []);

angular.module("issueTracker", ["Authentication", "ngRoute", "ngCookies", "ngStorage"])
    .config(['$routeProvider', function($routeProvider) {

        $routeProvider
            .when("/home", {
                controller: "HomeController",
                templateUrl: "views/private/home.html"
            })

            .when("/login", {
                controller: "loginController",
                templateUrl: "views/public/login.html"
            })

            .when("/issue", {
                controller: "IssueController",
                templateUrl: "views/private/issue.html"
            })

            .when("/signup", {
                controller: "SignupController",
                templateUrl: "views/public/signup.html"
            })

            .otherwise({
                redirectTo: "/home"
            });
    }]);