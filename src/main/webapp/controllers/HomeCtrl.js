/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("issueTracker")
    .controller("HomeController", ['$scope', '$rootScope', '$sessionStorage',
        function($scope, $rootScope, $sessionStorage) {

        $scope.getUser = function() {
            console.log("Getting User...");
            console.log("SessionStorage: " + $sessionStorage.sessionUser);
            console.log("sessionData: " + $rootScope.sessionData);

           $rootScope.sessionData = $sessionStorage.sessionUser;
        };

        if($sessionStorage.sessionUser === undefined) {
            console.log("SessionStorage: " + $sessionStorage.sessionUser);
            console.log("sessionData: " + $rootScope.sessionData);
            window.location = '#/login';
        } else {
            console.log("SessionStorage: " + $sessionStorage.sessionUser);
            console.log("sessionData: " + $rootScope.sessionData);
            window.location = '#/home';
        }

        $scope.logout = function() {
            $sessionStorage.sessionUser = undefined;
            window.location = '#/login';
        }
    }]);
