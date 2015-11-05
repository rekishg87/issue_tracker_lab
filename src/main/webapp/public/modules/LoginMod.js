/**
 * Created by Rekish on 10/29/2015.
 */

angular.module("LoginMod", [])
    .constant("loginUrl", "api/user/login")
    .factory("LoginFactory", ['$http', 'loginUrl',
        function($http, loginUrl) {

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

            return service;
        }]);


