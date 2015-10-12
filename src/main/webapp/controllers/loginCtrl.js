/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("Authentication")
    .controller("loginController", ['$scope', '$rootScope', '$localStorage', '$cookies', 'AuthService',
            function($scope, $rootScope, $localStorage, $cookies, AuthService) {

                $scope.isLoggedIn = false;

                $scope.login = function() {
                    AuthService.login($scope.username, $scope.password, function(response){
                        if(response.status === 200) {
                            $localStorage.sessionUser = $scope.username;
                            $rootScope.sessionData = $localStorage.sessionUser;
                            $scope.isLoggedIn = true;
                            $localStorage.sessionIdStorage = $cookies.get("JSESSIONID");
                            $rootScope.sessionId = $localStorage.sessionIdStorage;
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
                    console.log("getUser() SessionStorageUser: " + $localStorage.sessionUser);
                    console.log("getUser() sessionData: " + $rootScope.sessionData);
                    console.log("getUser() SessionStorageId: " + $localStorage.sessionIdStorage);

                    $rootScope.sessionData = $localStorage.sessionUser;
                    $rootScope.sessionId = $localStorage.sessionIdStorage;


                    if($localStorage.sessionIdStorage == $cookies.get("JSESSIONID")) {
                        window.location = '#/home';
                    } else {

                    }
                };

                if($localStorage.sessionIdStorage == undefined) {
                    console.log("LoginCtrl if SessionStorageUser: " + $localStorage.sessionUser);
                    console.log("LoginCtrl if sessionData: " + $rootScope.sessionData);
                    console.log("LoginCtrl if SessionStorageId: " + $localStorage.sessionIdStorage);
                    window.location = '#/login';
                } else if($localStorage.sessionIdStorage == $cookies.get("JSESSIONID")) {
                    console.log("LoginCtrl else SessionStorageUser: " + $localStorage.sessionUser);
                    console.log("LoginCtrl else sessionData: " + $rootScope.sessionData);
                    console.log("LoginCtrl else SessionStorageId: " + $localStorage.sessionIdStorage);
                    window.location = '#/home';
                }


    }]);
