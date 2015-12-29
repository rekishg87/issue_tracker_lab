/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("HomeMod")
    .controller("HomeController", ['$scope', '$rootScope', '$localStorage', 'ValidationFactory',
        function($scope, $rootScope, $localStorage, ValidationFactory) {

            // If there is no user logged in (there is no data in usernameStr),
            // set isLoggedIn to false and redirect to the login page.
            if($localStorage.usernameStr == undefined) {
                console.log("HomeCtrl if SessionStorage: " + $localStorage.usernameStr);
                console.log("HomeCtrl if sessionData: " + $rootScope.sessionData);
                console.log("HomeCtrl if SessionStorageId: " + $localStorage.sessionIdStorage);
                $localStorage.isLoggedIn = false;
                window.location = '#/login';
            }
            // If there is a valid user logged in, set isLoggedIn to true and redirect to the homepage.
            else {
                console.log("HomeCtrl else SessionStorageUser: " + $localStorage.usernameStr);
                $rootScope.sessionData = $localStorage.usernameStr;
                $rootScope.sessionId = $localStorage.sessionIdStorage;
                $localStorage.isLoggedIn = true;
                console.log("HomeCtrl else sessionData: " + $rootScope.sessionData);
                console.log("HomeCtrl else SessionStorageId: " + $localStorage.sessionIdStorage);
                window.location = '#/home';
            }

            // When the user goes to the homepage it must check if the user is authorized to access the page,
            // by validating if the user had a valid session.
            $scope.userState = function() {
                console.log("UserState() loaded...");
                ValidationFactory.validate();

                // If there is no session data stored in the $localStorage of sessionIdStorage,
                // set isloggedIn to false and redirect to the homepage
                if($localStorage.sessionIdStorage == undefined) {
                    $localStorage.isLoggedIn = false;
                    window.location = '#/login';
                } else {
                    $localStorage.isLoggedIn = true;
            }
        };

    }]);
