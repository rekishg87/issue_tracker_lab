/**
 * Created by Rekish on 10/9/2015.
 */

angular.module("LogoutMod")
    .controller("LogoutController", ['$scope', '$rootScope', 'LogoutFactory',
        function($scope, $rootScope, LogoutFactory) {

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

            LogoutFactory.logoutService($scope.sessionId, $scope.password, function(response){
                if(response.status === 200) {
                   console.log("LogoutService: " + response.data);
                    window.location = '#/login';
                } else if(response.status === 403) {
                    console.log("LogoutService Error: " + response.data);
                }

            });

        };


    }]);
