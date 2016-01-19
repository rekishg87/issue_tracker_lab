/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("LoginMod")
    .controller("LoginController", ['$scope', '$rootScope', '$localStorage', '$cookies', 'LoginFactory',
        function($scope, $rootScope, $localStorage, $cookies, LoginFactory) {

            // When there is no data in the sessionIdStorage, stay at the login page.
            if($localStorage.sessionIdStorage == undefined) {
                $rootScope.isUserLoggedIn = false;
                $localStorage.isUserLoggedInStr = $rootScope.isUserLoggedIn;
            }
            // If a user tries to go to the login page while logged in, redirect to the homepage.
            else if($localStorage.sessionIdStorage == $cookies.get("JSESSIONID")) {
                console.log("LoginCtrl else SessionStorageUser: " + $localStorage.usernameStr);
                console.log("LoginCtrl else usernameData: " + $rootScope.usernameData);
                console.log("LoginCtrl else SessionStorageId: " + $localStorage.sessionIdStorage);
                $rootScope.isUserLoggedIn = true;
                window.location = '#/home';
            }

            // Login user function.
            $scope.login = function() {
                LoginFactory.login($scope.username, $scope.password, function (response) {
                    // If the user successfully logs in, set the isUserLoggedIn to true,
                    // the $localStorage.usernameStr to the username that is logged in,
                    // the $localStorage.sessionIdStorage to the cookie value obtained from the response data from the backend
                    // with the appropriate $rootScopes data.
                    // The $localStorage is used because the data must be persisted when the user refreshes or goes to another page.
                    // The $rootScope is used to display the data on the web page.
                    if (response.status === 200) {
                        $rootScope.userRole = response.data; // Set userRole to $rootScope so it will available in the whole application.
                        $rootScope.isUserLoggedIn = true;
                        $localStorage.usernameStr = $scope.username;
                        $rootScope.usernameData = $localStorage.usernameStr;
                        $localStorage.sessionIdStorage = $cookies.get("JSESSIONID");
                        $rootScope.sessionId = $localStorage.sessionIdStorage;
                        window.location = '#/home';
                    } else if (response.status === 403) {
                        $scope.usernameFailed = $scope.username;
                        $rootScope.isUserLoggedIn = false;
                        $scope.showFailedMsg = true;

                    }
                });

                // Function for the register new user button to go to the register new user page.
                $scope.signup = function () {
                    window.location = '#/signup';
                };
            }
        }]);
