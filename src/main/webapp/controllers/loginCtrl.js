/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("Authentication")
    .controller("loginController", ['$scope', '$rootScope', '$sessionStorage', '$cookies', 'AuthService',
            function($scope, $rootScope, $sessionStorage, $cookies, AuthService) {

                $scope.isLoggedIn = false;

                $scope.login = function() {
                    AuthService.login($scope.username, $scope.password, function(response){
                        if(response.status === 200) {
                            $sessionStorage.sessionUser = $scope.username;
                            $rootScope.sessionData = $sessionStorage.sessionUser;
                            $scope.isLoggedIn = true;
                            $sessionStorage.sessionIdStorage = $cookies.get("JSESSIONID");
                            $rootScope.sessionId = $sessionStorage.sessionIdStorage;
                            window.location = '#/home';
                        } else if(response.status === 403) {
                            $scope.usernameFailed = $scope.username;
                            $scope.showFailedMsg = true;
                        }

                    });

                };

                $scope.signup = function() {
                    window.location = '#/signup';
                };

                $scope.getUser = function() {
                    console.log("getUser() Getting User...");
                    console.log("getUser() SessionStorageUser: " + $sessionStorage.sessionUser);
                    console.log("getUser() sessionData: " + $rootScope.sessionData);
                    console.log("getUser() SessionStorageId: " + $sessionStorage.sessionIdStorage);

                    $rootScope.sessionData = $sessionStorage.sessionUser;
                    $rootScope.sessionId = $sessionStorage.sessionIdStorage;


                    if($sessionStorage.sessionIdStorage == $cookies.get("JSESSIONID")) {
                        window.location = '#/home';
                    } else {

                    }
                };

                if($sessionStorage.sessionIdStorage == undefined) {
                    console.log("LoginCtrl if SessionStorageUser: " + $sessionStorage.sessionUser);
                    console.log("LoginCtrl if sessionData: " + $rootScope.sessionData);
                    console.log("LoginCtrl if SessionStorageId: " + $sessionStorage.sessionIdStorage);
                    window.location = '#/login';
                } else if($sessionStorage.sessionIdStorage == $cookies.get("JSESSIONID")) {
                    console.log("LoginCtrl else SessionStorageUser: " + $sessionStorage.sessionUser);
                    console.log("LoginCtrl else sessionData: " + $rootScope.sessionData);
                    console.log("LoginCtrl else SessionStorageId: " + $sessionStorage.sessionIdStorage);
                    window.location = '#/home';
                }


    }]);
