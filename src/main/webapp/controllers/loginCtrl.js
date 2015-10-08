/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("Authentication")
    .controller("loginController", ['$scope', '$rootScope', '$sessionStorage', 'AuthService', 'ValueService', 'ValService',
            function($scope, $rootScope, $sessionStorage, AuthService, ValueService, ValService) {

                $scope.isLoggedIn = false;
                $rootScope.sessionData = null;

                $scope.login = function() {
                    AuthService.login($scope.username, $scope.password, function(response){
                        if(response.status === 200) {
                            $sessionStorage.sessionUser = $scope.username;
                            $rootScope.sessionData = $sessionStorage.sessionUser;
                            $scope.isLoggedIn = true;
                            window.location = '#/home';
                        }

                    });
                    /*{
                        if(response.status === 200) {
                            $rootScope.usernameSuccess = $scope.username;
                            $scope.successMsg = "Success";
                        } else if(response.status === 403) {
                            $scope.successMsg = "Failed";
                        }
                    });*/
                };

                $scope.signup = function() {
                    window.location = '#/signup';
                };

                $scope.value = function() {
                    ValueService.value($scope.username, $scope.password, function(response) {
                        if(response.status === 200) {
                            console.log("Log: " + response.status);
                        }
                    })
                }

                $scope.val = function() {
                    ValService.val(function(response) {
                        if(response.status === 200) {
                            var rData = response.data;
                            console.log("Log: " + rData.toString());
                        }
                    })
                }

                $scope.getUser = function() {
                    console.log("Getting User...");
                    console.log("SessionStorage: " + $sessionStorage.sessionUser);
                    console.log("sessionData: " + $rootScope.sessionData);

                    $rootScope.sessionData = $sessionStorage.sessionUser;
                };

                if($sessionStorage.sessionUser === undefined) {
                    console.log("SessionStorage: " + $sessionStorage.sessionUser);
                    console.log("sessionData: " + $rootScope.sessionData);
                    window.location = '#/login';
                } else {
                    console.log("SessionStorage: " + $sessionStorage.sessionUser);
                    console.log("sessionData: " + $rootScope.sessionData);
                    window.location = '#/home';
                }
    }]);
