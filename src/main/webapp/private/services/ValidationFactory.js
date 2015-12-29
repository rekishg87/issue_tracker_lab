/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("ValidationMod", [])
    .constant("validateUrl", "api/user/validate")
    .factory("ValidationFactory", ['$http', '$cookies', '$localStorage', 'validateUrl',
        function($http, $cookies, $localStorage, validateUrl) {

            var service = {};

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

            return service;
        }]);
