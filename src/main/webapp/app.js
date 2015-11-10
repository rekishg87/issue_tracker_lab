/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("issueTracker", ["ValidationMod", "LoginMod", "HomeMod", "LogoutMod", "IssueMod",
    "SignupMod",  "ngRoute", "ngCookies", "ngStorage"])
    .config(['$routeProvider', function($routeProvider) {

        $routeProvider
            .when("/home", {
                controller: "HomeController",
                templateUrl: "views/private/home.html"
            })

            .when("/login", {
                controller: "LoginController",
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

            .when("/issue/new", {
                controller: "IssueController",
                templateUrl: "views/private/newissue.html"
            })

            .otherwise({
                redirectTo: "/home"
            })
    }]);