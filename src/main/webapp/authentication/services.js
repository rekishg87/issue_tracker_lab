/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("Authentication")
    .config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
    }])
    .factory("AuthService", ['$http', '$rootScope', '$location', 'loginUrl',
        function($http, $rootScope, $location, loginUrl) {
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

    .factory("ValService", ['$http', '$rootScope', '$location', 'valUrl',
        function($http, $rootScope, $location, valUrl) {
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
            return valService;

        }]);
