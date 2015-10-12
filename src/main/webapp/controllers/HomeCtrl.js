/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("issueTracker")
    .controller("HomeController", ['$scope', '$rootScope', '$sessionStorage', 'AuthService', 'ValService',
        function($scope, $rootScope, $sessionStorage, AuthService, ValService) {

            $scope.userState = function() {
                console.log("UserState() loaded...");
                AuthService.validate();

            if($sessionStorage.sessionIdStorage == undefined) {
                window.location = '#/login';
            } else {

            }
        };

        if($sessionStorage.sessionUser == undefined) {
            console.log("HomeCtrl if SessionStorage: " + $sessionStorage.sessionUser);
            console.log("HomeCtrl if sessionData: " + $rootScope.sessionData);
            console.log("HomeCtrl if SessionStorageId: " + $sessionStorage.sessionIdStorage);
            window.location = '#/login';
        } else {
            console.log("HomeCtrl else SessionStorageUser: " + $sessionStorage.sessionUser);
            $rootScope.sessionData = $sessionStorage.sessionUser;
            $rootScope.sessionId = $sessionStorage.sessionIdStorage;
            console.log("HomeCtrl else sessionData: " + $rootScope.sessionData);
            console.log("HomeCtrl else SessionStorageId: " + $sessionStorage.sessionIdStorage);
            window.location = '#/home';
        }

        $scope.logout = function() {
            $sessionStorage.sessionUser = undefined;
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
    }]);
