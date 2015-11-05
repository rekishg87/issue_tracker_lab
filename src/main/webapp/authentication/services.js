/**
 * Created by Rekish on 9/24/2015.
 */

angular.module("Authentication", [])
    .constant("validateUrl", "api/user/validate")
    .factory("AuthService", ['$http', '$rootScope', '$location', '$cookies', 'loginUrl',
        'signupUrl', 'logoutUrl', '$localStorage', 'validateUrl',
        function($http, $rootScope, $location, $cookies, loginUrl, signupUrl, logoutUrl,
                 $localStorage, validateUrl) {

            var service = {};

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
                            window.location = '#/login';

                        }

                    }
                );
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


            return service;
        }]);
