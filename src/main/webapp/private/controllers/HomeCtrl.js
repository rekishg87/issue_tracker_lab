/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("HomeMod")
    .controller("HomeController", ['$scope', '$rootScope', '$localStorage', 'ValidationFactory',
        function($scope, $rootScope, $localStorage, ValidationFactory) {

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

            $scope.userState = function() {
                console.log("UserState() loaded...");
                ValidationFactory.validate();

            if($localStorage.sessionIdStorage == undefined) {
                $localStorage.isLoggedIn = false;
                window.location = '#/login';
            } else {
                $localStorage.isLoggedIn = true;
            }
        };



    }]);
