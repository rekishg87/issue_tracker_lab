/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("issueTracker")
    .controller("SignupController", ['$scope', '$rootScope', '$localStorage', 'AuthService',
            function($scope, $rootScope, $localStorage, AuthService) {

        if($rootScope.isUserLoggedIn == false) {
            // User is logged in, so do nothing
        } else if($rootScope.isUserLoggedIn == true) {
            window.location = '#/home';
        }

        $scope.signupUser = function() {
            console.log("Signup Controller Initialized...");

            AuthService.signup($scope.username, $scope.password, $scope.email, function (response) {
                if (response.status === 200) {
                    console.log("User " + $scope.username + "  added.");
                    window.location = '#/login';

                } else {
                    console.log("User already exists, please try again.");
                    $scope.username = "";
                    $scope.password = "";
                    $scope.email = "";
                }

            })
        };


    }]);
