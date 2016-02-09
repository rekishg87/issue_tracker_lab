/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("ValidationMod", [])
    .constant("validateUrl", "api/user/validate")
    .factory("ValidationFactory", ['$http', '$rootScope', '$cookies', '$localStorage', 'validateUrl',
        function($http, $rootScope, $cookies, $localStorage, validateUrl) {

            var service = {};

            service.validate = function() {
                $http.get(validateUrl)
                    .then(
                    function onSuccess() {
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

            return service;
        }]);
