/**
 * Created by Rekish on 9/21/2015.
 */

angular.module('Authentication')
    .controller('LoginController', ['$scope', '$rootScope', '$location', 'AuthenticationService',
        function($scope, $rootScope, $location, AuthenticationService) {
            AuthenticationService.ClearCredentials();

            $scope.login2 = function() {
                $scope.dataLoading = true;
                AuthenticationService.login2($scope.username, $scope.password, function(response) {
                    if(response.success) {
                        AuthenticationService.SetCredentials($scope.username, $scope.password);
                        $location.path('/');
                    } else {
                        $scope.error = response.message;
                        $scope.dataLoading = false;
                    }
                });
            };
        }]);