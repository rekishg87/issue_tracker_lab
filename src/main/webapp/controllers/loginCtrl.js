/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("Authentication")
    .controller("loginController", ['$scope', '$rootScope', '$sessionStorage', '$cookies', 'AuthService',
            function($scope, $rootScope, $sessionStorage, $cookies, AuthService) {

                $scope.isLoggedIn = false;
                $rootScope.sessionData = undefined;

                $scope.login = function() {
                    AuthService.login($scope.username, $scope.password, function(response){
                        if(response.status === 200) {
                            $sessionStorage.sessionUser = $scope.username;
                            $rootScope.sessionData = $sessionStorage.sessionUser;
                            $scope.isLoggedIn = true;
                            $sessionStorage.sessionIdStorage = $cookies.get("JSESSIONID");
                            $rootScope.sessionId = $sessionStorage.sessionIdStorage;
                            window.location = '#/home';
                        } else if(response.status === 403) {
                            $scope.usernameFailed = $scope.username;
                            $scope.showFailedMsg = true;
                        }

                    });

                };

                $scope.signup = function() {
                    window.location = '#/signup';
                };

                $scope.getUser = function() {
                    console.log("Getting User...");
                    console.log("SessionStorage: " + $sessionStorage.sessionUser);
                    console.log("sessionData: " + $rootScope.sessionData);

                    $rootScope.sessionData = $sessionStorage.sessionUser;
                    $rootScope.sessionId = $sessionStorage.sessionIdStorage;
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

    }]);
