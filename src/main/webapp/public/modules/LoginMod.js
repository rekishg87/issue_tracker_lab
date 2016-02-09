/**
 * Created by Rekish on 10/29/2015.
 */

angular.module("LoginMod", [])
    .constant("loginUrl", "api/user/login")
    .factory("LoginFactory", ['$http', 'loginUrl',
        function($http, loginUrl) {

            var service = {};

            // Service to authenticate the user from the backend.
            service.login = function(username, password, callback) {
                var credentials = {username: username, password: password};

                $http.post(loginUrl, credentials)
                    .then(
                    function onSuccess(response) {
                        callback(response);
                    },
                    function onError(err) {
                        if (err.status === 403 || httpStatusCode === 500) {
                            callback(err);
                        }
                    }
                );
            };

            return service;
        }]);


