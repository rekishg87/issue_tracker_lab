/**
 * Created by Rekish on 10/9/2015.
 */

angular.module("LogoutMod")
    .controller("LogoutController", ['$rootScope', '$scope', 'LogoutFactory',
        function($rootScope, $scope, LogoutFactory) {

        // Logout user function.
        $scope.logout = function() {
            LogoutFactory.logoutService($scope.sessionId, function(response){
                if(response.status === 200) {
                    toastr.success($rootScope.usernameData + " Logged out!");
                    window.location = '#/login';
                } else if(response.status === 403) {
                    toastr.error("Not logged in!");
                    window.location = '#/login';
                }
            });
        };
    }]);
