/**
 * Created by Rekish on 10/9/2015.
 */

angular.module("issueTracker")
    .controller("LogoutController", ['$scope', '$rootScope', 'AuthService',
        function($scope, $rootScope, AuthService) {

        $scope.logoutS = function() {
            AuthService.logout();
            window.location = '#/login';


        };


    }]);
