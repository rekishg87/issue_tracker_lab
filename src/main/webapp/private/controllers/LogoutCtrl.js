/**
 * Created by Rekish on 10/9/2015.
 */

angular.module("LogoutMod")
    .controller("LogoutController", ['$scope', 'LogoutFactory',
        function($scope, LogoutFactory) {

        $scope.logout = function() {
            LogoutFactory.logoutService($scope.sessionId, function(response){
                if(response.status === 200) {
                   console.log("LogoutService: " + response.data);
                    window.location = '#/login';
                } else if(response.status === 403) {
                    console.log("LogoutService Error: " + response.data);
                }

            });
        };
    }]);
