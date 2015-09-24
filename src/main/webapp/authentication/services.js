/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("Authentication")
    .factory("AuthService", ['$http', '$rootScope', 'dataUrl',
        function($http, $rootScope, dataUrl) {
            var service = {};
            $rootScope.showLoginButton = true;
            $rootScope.showUsernameFailed = false;
            $rootScope.showUsernameSuccess = false;


            service.login = function(username, password, callback) {
                var input = {username: username, password: password};

                $http.post(dataUrl, input)
                    .then(
                    function success(response) {
                        $rootScope.showUsernameSuccess = true;
                        $rootScope.showUsernameFailed = false;
                        $rootScope.usernameSuccess = username;
                        $rootScope.showLoginButton = false;
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
        }]);
