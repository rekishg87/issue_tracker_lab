/**
 * Created by Rekish on 10/29/2015.
 */

angular.module("LogoutMod", [])
    .constant("logoutUrl", "api/user/signout")
    .factory("LogoutFactory", ['$http', 'logoutUrl',
        function($http, logoutUrl) {

            var service = {};

            // Service to logout the user int the front and as well on the backend.
            service.logoutService = function(sessionId, callback) {
                var sessionID = {sessionId: sessionId};

                $http.post(logoutUrl, sessionID)
                    .then(
                    function onSuccess(response) {
                        callback(response);
                    },
                    function onError(err) {
                        if(err.status === 403) {
                            callback(err);
                        }
                    }
                );
            };

            return service;
        }]);

