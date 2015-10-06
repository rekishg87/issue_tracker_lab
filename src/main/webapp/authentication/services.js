/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("Authentication")
    .config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
    }])
    .factory("AuthService", ['$http', '$rootScope', '$location', 'loginUrl', 'signupUrl',
        function($http, $rootScope, $location, loginUrl, signupUrl) {
            var service = {};
            $rootScope.showLoginButton = true;
            $rootScope.showUsernameFailed = false;
            $rootScope.showUsernameSuccess = false;


            service.login = function(username, password, callback) {
                var credentials = {username: username, password: password};

                $http.post(loginUrl, credentials)
                    .then(
                    function success(response) {
                        $rootScope.showUsernameSuccess = true;
                        $rootScope.showUsernameFailed = false;
                        $rootScope.usernameSuccess = username;
                        $rootScope.showLoginButton = false;
                        $location.path("/home");
                        callback(response);

                    },
                    function error(response) {
                        var httpStatusCode = response.status;
                        if (httpStatusCode === 403) {
                            $rootScope.showUsernameSuccess = false;
                            $rootScope.showUsernameFailed = true;
                            $rootScope.usernameFailed = username;
                            $rootScope.showLoginButton = true;
                            callback(response);
                        }
                    }
                );
            };

            service.signup = function(username, password, email, callback) {
                var signupData = {
                    username: username,
                    password: password,
                    email: email
                };

                $http.post(signupUrl, signupData)
                    .then(
                    function onSuccess(response) {
                        response.entity;
                        callback(response);
                    },
                    function onError(err) {
                        callback(err);
                    }
                )
            };

            return service;
        }])

    .factory("ValueService", ['$http', '$rootScope', '$location', 'valueUrl',
        function($http, $rootScope, $location, dataUrl) {
            var valueService = {};

            valueService.value = function(username, password, callback) {
                var input = {username: username, password: password};

                $http.post(dataUrl, input)
                    .then(
                    function success(response) {
                        console.log("valueService: " + response.data);
                        callback(response);

                    },
                    function error(response) {
                        var httpStatusCode = response.status;
                        if (httpStatusCode === 403) {
                            $rootScope.showUsernameSuccess = false;
                            $rootScope.showUsernameFailed = true;
                            $rootScope.usernameFailed = username;
                            $rootScope.showLoginButton = true;
                            console.log("error: " + response.data);
                            callback(response);
                        }
                    }
                );
            };
            return valueService;

        }])

    .factory("ValService", ['$http', '$location', 'valUrl',
        function($http, $location, valUrl) {
            var valService = {};

            valService.val = function(callback) {

                $http.get(valUrl)
                    .then(
                    function success(response) {
                        console.log("valService: " + response.data);
                        callback(response);

                    },
                    function error(response) {
                        var httpStatusCode = response.status;
                        if (httpStatusCode === 403) {
                            console.log("error: " + response.data);
                            callback(response);
                        }
                    }
                );
            };
            return valService;

        }]);
