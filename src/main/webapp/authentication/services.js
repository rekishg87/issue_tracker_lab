/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("Authentication")
    .config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
    }])
    .factory("AuthService", ['$http', '$rootScope', '$location', '$cookies', 'loginUrl',
        'signupUrl', 'logoutUrl', '$sessionStorage', 'validateUrl',
        function($http, $rootScope, $location, $cookies, loginUrl, signupUrl, logoutUrl,
                 $sessionStorage, validateUrl) {

            var service = {};

            service.login = function(username, password, callback) {
                var credentials = {username: username, password: password};

                $http.post(loginUrl, credentials)
                    .then(
                    function success(response) {
                        callback(response);

                    },
                    function error(err) {
                        var httpStatusCode = err.status;
                        if (httpStatusCode === 403 || httpStatusCode === 500) {
                            callback(err);
                        }
                    }
                );
            };

            service.logout = function() {

                $http.get(logoutUrl)
                    .then(
                    function onSuccess() {
                        $sessionStorage.sessionUser = undefined;
                        $sessionStorage.sessionIdStorage = undefined;
                        window.location = '#/login';

                    },
                    function onError(err) {
                        console.log("dataRespErr: " + err.data);
                    }
                );
            };

            service.validate = function() {

                $http.get(validateUrl)
                    .then(
                    function onSuccess(response) {
                        if(response.status === 200 && $sessionStorage.sessionIdStorage == null) {
                            $sessionStorage.sessionIdStorage = $cookies.get("JSESSIONID");
                        } else if(response.status === 200 && $sessionStorage.sessionIdStorage != null) {
                            $sessionStorage.sessionIdStorage = null;
                        }

                        console.log("ID : " + response.data);

                    },
                    function onError(err) {
                        if(err.status === 403) {

                            window.location = '#/login';
                        }
                        console.log("dataRespErr: " + err.data);
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
                        callback(response);
                    },
                    function onError(err) {
                        callback(err);
                    }
                )
            };

            return service;
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
