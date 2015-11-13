/**
 * Created by Rekish on 11/13/2015.
 */

angular.module("DatabaseFactory", [])
    .constant("assigneeUrl", "api/assignee/all")
    .constant("categoryUrl", "api/category/all")
    .constant("priorityUrl", "api/priority/all")
    .constant("statusUrl", "api/status/all")
    .factory("IssueDatabase", ['$http', 'assigneeUrl', 'categoryUrl', 'priorityUrl', 'statusUrl',
        function($http, assigneeUrl, categoryUrl, priorityUrl, statusUrl) {

            var service = {};

            service.getAssignee = function(callback) {
                $http.get(assigneeUrl)
                    .then(
                        function onSuccess(response) {
                            callback(response);
                        },
                        function onError(err) {
                            callback(err);
                        }
                    )
            };

            service.getCategory = function() {
                $http.get(categoryUrl)
                    .then(
                        function onSuccess() {

                        },
                        function onError() {

                        }
                    )
            };

            service.getPriority = function() {
                $http.get(priorityUrl)
                    .then(
                        function onSuccess() {

                        },
                        function onError() {

                        }
                    )
            };

            service.getStatus = function() {
                $http.get(statusUrl)
                    .then(
                        function onSuccess() {

                        },
                        function onError() {

                        }
                    )
            };

            return service;
    }]);

