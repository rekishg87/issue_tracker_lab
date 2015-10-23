/**
 * Created by Rekish on 10/23/2015.
 */

angular.module("Authentication")
    .constant("allIssuesUrl", "api/issues/getAllIssues")
    .factory("IssueServices", ['$http', '$location', 'allIssuesUrl',
        function($http, $location, allIssuesUrl) {
            var valService = {};

            valService.val = function(callback) {

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
            return valService;

        }]);
