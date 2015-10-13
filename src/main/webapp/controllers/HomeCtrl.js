/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("issueTracker")
    .controller("HomeController", ['$scope', '$rootScope', '$localStorage', 'AuthService', 'ValService',
        function($scope, $rootScope, $localStorage, AuthService, ValService) {

            $scope.userState = function() {
                console.log("UserState() loaded...");
                AuthService.validate();

            if($localStorage.sessionIdStorage == undefined) {
                $localStorage.isLoggedIn = false;
                window.location = '#/login';
            } else {
                $localStorage.isLoggedIn = true;
            }
        };

        if($localStorage.usernameStr == undefined) {
            console.log("HomeCtrl if SessionStorage: " + $localStorage.usernameStr);
            console.log("HomeCtrl if sessionData: " + $rootScope.sessionData);
            console.log("HomeCtrl if SessionStorageId: " + $localStorage.sessionIdStorage);
            $localStorage.isLoggedIn = false;
            window.location = '#/login';
        } else {
            console.log("HomeCtrl else SessionStorageUser: " + $localStorage.usernameStr);
            $rootScope.sessionData = $localStorage.usernameStr;
            $rootScope.sessionId = $localStorage.sessionIdStorage;
            $localStorage.isLoggedIn = true;
            console.log("HomeCtrl else sessionData: " + $rootScope.sessionData);
            console.log("HomeCtrl else SessionStorageId: " + $localStorage.sessionIdStorage);
            window.location = '#/home';
        }

        $scope.logout = function() {
            $localStorage.usernameStr = undefined;
            $localStorage.isLoggedIn = false;
            window.location = '#/login';
        };

            $scope.val = function () {
                ValService.val(function (response) {
                    if (response.status === 200) {
                        var rData = response.data;
                        console.log("Log: " + rData.toString());
                    }
                })
            };

        $scope.readStorage = function() {
            console.log($localStorage.isLoggedIn);

        };
    }]);
