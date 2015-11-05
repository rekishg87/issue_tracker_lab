/**
 * Created by Rekish on 10/29/2015.
 */

angular.module("SignupMod", [])
    .constant("signupUrl", "api/user/signup")
    .factory("SignupFactory", ['$http', '$rootScope', '$location', '$cookies', 'signupUrl',
        function($http, $rootScope, $location, $cookies, signupUrl) {

            var service = {};

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
        }]);

