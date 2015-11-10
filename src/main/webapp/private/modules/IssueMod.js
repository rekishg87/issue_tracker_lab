/**
 * Created by Rekish on 10/29/2015.
 */

angular.module("IssueMod", ['LogoutMod', 'ValidationMod'])
    .constant("allIssuesUrl", "api/issues/getAllIssues")
    .constant("createIssueUrl", "api/issues/create")
    .factory("IssueFactory", ['$http', '$location', 'allIssuesUrl', 'createIssueUrl',
        function($http, $location, allIssuesUrl, createIssueUrl) {
            var service = {};

            service.getAllIssues = function(callback) {

                $http.get(allIssuesUrl)
                    .then(
                    function success(response) {
                        callback(response);
                    },
                    function error(err) {
                        var httpStatusCode = err.status;
                        if (httpStatusCode === 403) {
                            window.location = '#/login';
                            callback(err);
                        }
                    }
                );
            };

            service.createIssue = function(description, callback) {
                var data = {description: description};
                $http.post(createIssueUrl, data)
                    .then(
                        function success(response) {
                            callback(response);
                        },
                        function error(err) {
                            if(err.status === 403) {
                                window.location = '#/login';
                                callback(err);
                            }
                        }
                    )
            };

            return service;
        }]);


