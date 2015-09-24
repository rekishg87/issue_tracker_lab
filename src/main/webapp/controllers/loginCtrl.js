/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("Authentication")
    .controller("loginController", ['$scope', '$rootScope', 'AuthService',
            function($scope, $rootScope, AuthService) {

                $scope.login = function() {
                    AuthService.login($scope.username, $scope.password, function(response){

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
    }]);
