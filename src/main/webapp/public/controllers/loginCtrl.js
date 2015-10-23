/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("Authentication")
    .controller("loginController", ['$scope', '$rootScope', '$localStorage', '$cookies', 'AuthService',
            function($scope, $rootScope, $localStorage, $cookies, AuthService) {

                $rootScope.test = 100;

                $rootScope.isUserLoggedIn = false;
                $localStorage.isUserLoggedInStr = $rootScope.isUserLoggedIn;

                if($localStorage.sessionIdStorage == undefined) {
                    console.log("LoginCtrl if SessionStorageUser: " + $localStorage.usernameStr);
                    console.log("LoginCtrl if sessionData: " + $rootScope.sessionData);
                    console.log("LoginCtrl if SessionStorageId: " + $localStorage.sessionIdStorage);
                    $rootScope.isUserLoggedIn = false;
                    // window.location = '#/login';
                } else if($localStorage.sessionIdStorage == $cookies.get("JSESSIONID")) {
                    console.log("LoginCtrl else SessionStorageUser: " + $localStorage.usernameStr);
                    console.log("LoginCtrl else sessionData: " + $rootScope.sessionData);
                    console.log("LoginCtrl else SessionStorageId: " + $localStorage.sessionIdStorage);
                    $rootScope.isUserLoggedIn = true;
                    window.location = '#/home';
                }


                $scope.login = function() {
                    AuthService.login($scope.username, $scope.password, function(response){
                        if(response.status === 200) {
                            $rootScope.isUserLoggedIn = true;
                            $localStorage.usernameStr = $scope.username;
                            $rootScope.sessionData = $localStorage.usernameStr;
                            $localStorage.sessionIdStorage = $cookies.get("JSESSIONID");
                            $rootScope.sessionId = $localStorage.sessionIdStorage;
                            window.location = '#/home';
                        } else if(response.status === 403) {
                            $scope.usernameFailed = $scope.username;
                            $rootScope.isUserLoggedIn = false;
                            $scope.showFailedMsg = true;
                        }

                    });

                };

                $scope.signup = function() {
                    window.location = '#/signup';
                };
    }]);
