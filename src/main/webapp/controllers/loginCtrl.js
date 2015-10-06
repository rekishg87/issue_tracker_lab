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
    }]);
