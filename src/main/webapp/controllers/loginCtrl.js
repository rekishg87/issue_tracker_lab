/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("Authentication")
    .controller("loginController", ['$scope', '$rootScope', 'AuthService', 'ValueService', 'ValService',
            function($scope, $rootScope, AuthService, ValueService, ValService) {

                $scope.isLoggedIn = false;

                $scope.login = function() {
                    AuthService.login($scope.username, $scope.password, function(response){
                        if(response.status === 200) {
                            response.sessionStorage
                            $scope.isLoggedIn = true;

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
