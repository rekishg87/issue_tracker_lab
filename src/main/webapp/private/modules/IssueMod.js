/**
 * Created by Rekish on 10/29/2015.
 */

angular.module("IssueMod", ['LogoutMod', 'ValidationMod'])
    .constant("allIssuesUrl", "api/issues/getAllIssues")
    .factory("IssueFactory", ['$http', '$location', 'allIssuesUrl',
        function($http, $location, allIssuesUrl) {
            var service = {};

            service.getAllIssues = function(callback) {

                $http.get(allIssuesUrl)
                    .then(
                    function success(response) {
                        console.log("valService: " + response.data);
                        callback(response);

                    },
                    function error(response) {
                        var httpStatusCode = response.status;
                        if (httpStatusCode === 403) {
                            console.log("error: " + response.data);
                            window.location = '#/login';
                            callback(response);
                        }
                    }
                );
            };

            return service;
        }]);


