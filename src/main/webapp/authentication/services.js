/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("Authentication")
    .config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
    }])
    .factory("AuthService", ['$http', '$rootScope', '$location', '$cookies', 'loginUrl',
        'signupUrl', 'logoutUrl', '$localStorage', 'validateUrl', 'getId',
        function($http, $rootScope, $location, $cookies, loginUrl, signupUrl, logoutUrl,
                 $localStorage, validateUrl, getId) {

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

            service.logoutService = function(sessionId) {
                var sID = {sessionId: sessionId};
                $http.post(logoutUrl, sID)
                    .then(
                    function onSuccess(response) {

                        $localStorage.usernameStr = undefined;
                        $localStorage.sessionIdStorage = undefined;
                        window.location = '#/login';


                    },
                    function onError(err) {
                        if(err.status === 403) {

                            console.log("Allready Logged Out");
                            console.log("logout() ERROR dataRespErr: " + err.data);

                        }

                    }
                );
            };

            service.getSessionIDService = function(sessionId, callback) {
                var test = {sessionId: sessionId};

                $http.post(getId, test)
                    .then(
                    function onSuccess(response) {
                        callback(response);
                    },
                    function onError(err) {
                        callback(err);
                    }
                )
            };

            service.validate = function() {
                console.log("validateService loaded...");
                $http.get(validateUrl)
                    .then(
                    function onSuccess() {

                            console.log("validate Success");
                        $localStorage.sessionIdStorage = $cookies.get("JSESSIONID");




                    },
                    function onError(err) {
                        if(err.status === 403) {
                            console.log("validate ERROR");
                            $localStorage.sessionIdStorage = undefined;
                            $localStorage.usernameStr = undefined;
                            console.log("validate() ERROR: dataRespErr: " + err.data);
                            window.location = '#/login';
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
