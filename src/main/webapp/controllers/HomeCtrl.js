/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("issueTracker")
    .controller("HomeController", ['$scope', '$rootScope', '$sessionStorage', 'AuthService', 'ValService',
        function($scope, $rootScope, $sessionStorage, AuthService, ValService) {

            $scope.userState = function() {
                AuthService.validate();

                if($sessionStorage.sessionIdStorage == null) {
                    window.location = '#/login';
                } else {

                }

            };

        if($sessionStorage.sessionUser === undefined) {
            console.log("SessionStorage: " + $sessionStorage.sessionUser);
            console.log("sessionData: " + $rootScope.sessionData);
            window.location = '#/login';
        } else {
            console.log("SessionStorage: " + $sessionStorage.sessionUser);
            $rootScope.sessionData = $sessionStorage.sessionUser;
            $rootScope.sessionId = $sessionStorage.sessionIdStorage;
            console.log("sessionData: " + $rootScope.sessionData);
            window.location = '#/home';
        }

        $scope.logout = function() {
            $sessionStorage.sessionUser = undefined;
            window.location = '#/login';
        };

            $scope.val = function () {
                ValService.val(function (response) {
                    if (response.status === 200) {
                        var rData = response.data;
                        console.log("Log: " + rData.toString());
                    }
                })
            };
    }]);
