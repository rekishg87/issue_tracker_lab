/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("IssueMod")
    .controller("IssueController", ['$scope', '$filter', '$rootScope', '$localStorage', 'ValidationFactory', 'IssueFactory', 'LogoutFactory',
        function($scope, $filter, $rootScope, $localStorage, ValidationFactory, IssueFactory, LogoutFactory) {
            // Initialize $scope.data where the issue response data will be stored in.
            $scope.data = {};

            // Validate if user has a valid session.
            $scope.checkValidity = function() {
                ValidationFactory.validate();
            };

            // Function to get all the issues.
            $scope.getAllIssues = function() {
                IssueFactory.getAllIssues(function(response) {
                    if (response.status === 200) {
                        // In the response.data the
                        // data[0] index has the issues table,
                        // data[1] index has the priority table.
                        // data[2] index has the assignee table.
                        // data[3] index has the category table.
                        // data[4] index has the status table.
                        $scope.data = response.data;
                        $scope.issue = $scope.data[0];
                        $scope.priority = $scope.data[1];
                        $scope.assignee = $scope.data[2];
                        $scope.category = $scope.data[3];
                        $scope.status = $scope.data[4];

                        // Get DateTime from Database as Epoch format and convert it toLocalString(),
                        // so that it can be a human readable DateTime format.
                        for(var x = 0; x < $scope.data[0].length; x++) {
                            $scope.data[0].issueCreatedOn = {};
                            $scope.data[0][x].issueCreatedOn = new Date($scope.data[0][x].issueCreatedOn).toLocaleString();
                        }
                    } else if (response.status === 403) {
                        LogoutFactory.logoutService($scope.sessionId);
                        window.location = '#/login';
                    }
                });
            };

            // Function to get all the resolved issues.
            $scope.getResolvedIssues = function() {
                IssueFactory.getResolvedIssues(function(response) {
                    if (response.status === 200) {
                        // In the response.data the
                        // data[0] index has the issues table,
                        // data[1] index has the priority table.
                        // data[2] index has the assignee table.
                        // data[3] index has the category table.
                        // data[4] index has the status table.
                        $scope.data = response.data;
                        $scope.issue = $scope.data[0];
                        $scope.priority = $scope.data[1];
                        $scope.assignee = $scope.data[2];
                        $scope.category = $scope.data[3];
                        $scope.status = $scope.data[4];

                        // Get DateTime from Database as Epoch format and convert it toLocalString(),
                        // so that it can be a human readable DateTime format.
                        for(var x = 0; x < $scope.data[0].length; x++) {
                            $scope.data[0].issueCreatedOn = {};
                            $scope.data[0][x].issueCreatedOn = new Date($scope.data[0][x].issueCreatedOn).toLocaleString();
                        }
                    } else if (response.status === 403) {
                        LogoutFactory.logoutService($scope.sessionId);
                        window.location = '#/login';
                    }
                });
            };

            // Function to create a new issue.
            $scope.createIssue = function() {
                IssueFactory.createIssue($scope.description, $scope.subject, $scope.category, $scope.priority, $rootScope.usernameData, function(response) {
                    if(response.status === 200) {
                        window.location = '#/issue';
                        alert('Issue created!');
                    } else if(response.status === 403) {
                       console.log("Failed!");
                    }
                });

            };

            // Function to edit/update an issue.
            $scope.updateIssue = function(data, issueId) {
                IssueFactory.updateIssue(issueId, data.priority, data.subject, data.description, data.assignee, data.category, data.status, function(response) {
                    if (response.status === 200) {
                        alert("Updated!");
                    } else if (response.status === 403) {
                        LogoutFactory.logoutService($scope.sessionId);
                        window.location = '#/login';
                    }
                });

            };

            // When the create new issue button/link is clicked, redirect to the create new issue page.
            $scope.createNewIssue = function() {
                window.location = '#/issue/new';
            };

            // Remove an issue
            $scope.removeIssue = function (issueId) {
                IssueFactory.removeIssue(issueId, function(response) {
                    if (response.status === 200) {
                        $scope.getAllIssues();
                    } else if (response.status === 403) {
                        LogoutFactory.logoutService($scope.sessionId);
                        window.location = '#/login';
                    }
                });
            };

            // The following show* functions, display the data in the select boxes,
            // with the corresponding data from the database.
            $scope.showPriority = function(issue) {
                var selected = [];
                if (issue.priorityId) {
                    selected = $filter('filter')($scope.priority, {
                        id: issue.priorityId
                    });
                }
                return selected.length ? selected[0].name : 'Not set';
            };

            $scope.showAssignee = function(issue) {
                var selected = [];
                if (issue.assigneeId) {
                    selected = $filter('filter')($scope.assignee, {
                        id: issue.assigneeId
                    });
                }
                return selected.length ? selected[0].name : 'Not set';
            };

            $scope.showCategory = function(issue) {
                var selected = [];
                if (issue.categoryId) {
                    selected = $filter('filter')($scope.category, {
                        id: issue.categoryId
                    });
                }
                return selected.length ? selected[0].name : 'Not set';
            };

            $scope.showStatus = function(issue) {
                var selected = [];
                if (issue.statusId) {
                    selected = $filter('filter')($scope.status, {
                        id: issue.statusId
                    });
                }
                return selected.length ? selected[0].name : 'Not set';
            };
    }]);
