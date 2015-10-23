/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("issueTracker")
    .controller("SignupController", ['$scope', '$rootScope', '$localStorage', 'AuthService',
            function($scope, $rootScope, $localStorage, AuthService) {
                console.log("test: " + $rootScope.test);

        if($rootScope.isUserLoggedIn == false) {
            // User is logged in, so do nothing
        } else if($rootScope.isUserLoggedIn == true) {
            window.location = '#/home';
        }

        $scope.signupUser = function() {
            console.log("Signup Controller Initialized...");

            AuthService.signup($scope.username, $scope.password, $scope.email, function (response) {
                if (response.status === 200) {
                    $scope.showSuccessMsg = true;
                    console.log("User " + $scope.username + " added.");
                    window.location = '#/login';

                } else if (response.status === 400 && response.data === "EMPTY" ){
                    console.log("Empty try again");
                    $scope.showSuccessMsg = false;
                    $scope.showUserExistsMsg = false;
                    $scope.showFieldsEmptyMsg = true;
                    $scope.username = "";
                    $scope.password = "";
                    $scope.email = "";
                } else if (response.status === 400 && response.data === "dubUser" ) {
                    console.log("User already exists, please try again.");
                    $scope.showSuccessMsg = false;
                    $scope.showFieldsEmptyMsg = false;
                    $scope.showUserExistsMsg = true;
                    $scope.username = "";
                    $scope.password = "";
                    $scope.email = "";
                }

            })
        };


    }]);
