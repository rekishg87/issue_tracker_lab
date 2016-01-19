/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("SignupMod")
    .controller("SignupController", ['$scope', '$rootScope', '$localStorage', 'SignupFactory',
            function($scope, $rootScope, $localStorage, SignupFactory) {

        if($rootScope.isUserLoggedIn == false) {
            // Stay on the page, because there is no user logged in and the guest can register to get access to the application.
        }
        // If a user tries to go the register user page/form, while logged in.
        // Redirect the user to the homepage, because there is no need to register a new user.
        else if($rootScope.isUserLoggedIn == true) {
            window.location = '#/home';
        }

        // Register a new user function.
        $scope.signupUser = function() {
            console.log("Signup Controller Initialized...");
            if ($scope.registerUserForm.$valid) {
                SignupFactory.signup($scope.username, $scope.password, $scope.email, function (response) {
                    // If all information is valid, redirect to the login page after registration.
                    if (response.status === 200) {
                        alert($scope.username + " registered successfully.");
                        window.location = '#/login';

                    } else if (response.status === 400) {
                        $scope.showUserExistsMsg = true;
                    }
                })
            }
        }
    }]);
