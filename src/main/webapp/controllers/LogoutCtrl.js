/**
 * Created by Rekish on 10/9/2015.
 */

angular.module("issueTracker")
    .controller("LogoutController", ['$scope', '$rootScope', 'AuthService',
        function($scope, $rootScope, AuthService) {

        $scope.logout = function() {
            //AuthService.getSessionIDService($scope.sessionId, function(response){
            //    if(response.status === 200) {
            //        console.log("Function: " + response.data);
            //
            //    } else if(response.status === 403) {
            //        console.log("Error F: " + response.data);
            //    }
            //
            //});

            AuthService.logoutService($scope.sessionId, $scope.password, function(response){
                if(response.status === 200) {
                   console.log("LogoutService: " + response.data);
                    window.location = '#/login';
                } else if(response.status === 403) {
                    console.log("LogoutService Error: " + response.data);
                }

            });

        };


    }]);
