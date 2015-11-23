/**
 * Created by Rekish on 11/13/2015.
 */

angular.module("DatabaseFactory", ["ValidationMod"])
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

            service.getCategory = function(callback) {
                $http.get(categoryUrl)
                    .then(
                        function onSuccess(response) {
                            callback(response);
                        },
                        function onError(err) {
                            callback(err);
                        }
                    )
            };

            service.getPriority = function(callback) {
                $http.get(priorityUrl)
                    .then(
                        function onSuccess(response) {
                            callback(response);
                        },
                        function onError(err) {
                            callback(err);
                        }
                    )
            };

            service.getStatus = function(callback) {
                $http.get(statusUrl)
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

