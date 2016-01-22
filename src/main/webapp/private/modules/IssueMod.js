/**
 * Created by Rekish on 10/29/2015.
 * Issue module
 */

angular.module("IssueMod", ['LogoutMod', 'ValidationMod', 'xeditable'])
    // Configurations for the Edit form (xeditable)
    .run(function(editableOptions, editableThemes) {
        editableThemes.bs3.inputClass = 'default';
        editableThemes.bs3.buttonsClass = 'btn-sm';
        editableOptions.theme = 'bs3';
    })
    .constant("allIssuesUrl", "api/issues/getAllIssues")
    .constant("allResolvedIssuesUrl", "api/issues/getResolvedIssues")
    .constant("createIssueUrl", "api/issues/create")
    .constant("updateIssueUrl", "api/issues/update")
    .constant("removeIssueUrl", "api/issues/remove")
    .factory("IssueFactory", ['$http', '$location', 'allIssuesUrl', 'createIssueUrl', 'updateIssueUrl', 'removeIssueUrl', 'allResolvedIssuesUrl',
        function($http, $location, allIssuesUrl, createIssueUrl, updateIssueUrl, removeIssueUrl, allResolvedIssuesUrl) {
            var service = {};

            // Service to get all issues from the backend.
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

            // Service to get all resolved issues from the backend.
            service.getResolvedIssues = function(callback) {

                $http.get(allResolvedIssuesUrl)
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

            // Service to create a new issue and post it to the backend.
            service.createIssue = function(description, subject, category, priority, usernameData, callback) {
                var data = {
                    description: description,
                    subject: subject,
                    categoryId: category,
                    priorityId: priority,
                    createdBy: usernameData
                };
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

            // Service to edit/update an issue and post it to the backend.
            service.updateIssue = function(issueId, priority, subject, description, assignee, category, status, callback) {
                var data = {
                    id: issueId,
                    priorityId: priority,
                    subject: subject,
                    description: description,
                    assigneeId: assignee,
                    categoryId: category,
                    statusId: status
                };
                $http.post(updateIssueUrl, data)
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

            // Service to remove an issue.
            service.removeIssue = function(issueId, callback) {
                var data = {
                    id: issueId
                };
                $http.post(removeIssueUrl, data)
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


