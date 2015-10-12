/**
 * Created by Rekish on 10/9/2015.
 */

angular.module("issueTracker")
    .controller("LogoutController", ['$scope', '$rootScope', 'AuthService',
        function($scope, $rootScope, AuthService) {

        $scope.logout = function() {
            AuthService.logoutService();
            window.location = '#/login';
        };


    }]);
