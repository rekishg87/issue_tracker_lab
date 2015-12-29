/**
 * Created by Rekish on 10/29/2015.
 */

angular.module("LogoutMod", [])
    .constant("logoutUrl", "api/user/signout")
    .factory("LogoutFactory", ['$http', 'logoutUrl', '$localStorage',
        function($http, logoutUrl, $localStorage) {

            var service = {};

            // Service to logout the user int the front and as well on the backend.
            service.logoutService = function(sessionId) {
                var sessionID = {sessionId: sessionId};
                $http.post(logoutUrl, sessionID)
                    .then(
                    function onSuccess() {
                        $localStorage.usernameStr = undefined;
                        $localStorage.sessionIdStorage = undefined;
                        window.location = '#/login';
                    },
                    function onError(err) {
                        if(err.status === 403) {
                            console.log("Not Logged In!");
                            console.log("logout() ERROR dataRespErr: " + err.data);
                            window.location = '#/login';
                        }
                    }
                );
            };

            return service;
        }]);

