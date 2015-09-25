/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("Authentication")
    .controller("loginController", ['$scope', '$rootScope', 'AuthService', 'LocalStorageService',
            function($scope, $rootScope, AuthService, LocalStorageService) {

                $scope.login = function() {
                    AuthService.login($scope.username, $scope.password, function(response){
                        if(response.status === 200) {
                            $rootScope.dataSave = LocalStorageService.save("test1");
                            $rootScope.dataSave2 = LocalStorageService.save("test2");
                            $rootScope.dataLoad = LocalStorageService.load();
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
    }]);
