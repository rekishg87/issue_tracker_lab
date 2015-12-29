/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("IssueMod")
    .controller("IssueController", ['$scope', '$rootScope', '$localStorage', 'ValidationFactory', 'IssueFactory', 'LogoutFactory',
        function($scope, $rootScope, $localStorage, ValidationFactory, IssueFactory, LogoutFactory) {

            console.log("Issue Controller Initialized...");

            // Initialize $scope.data where the issue response data will be stored in.
            $scope.data = {};

            // Validate if user has a valid session.
            $scope.checkValidity = function() {
                console.log("checkValidity() loaded...");
                ValidationFactory.validate();
            };

            // Function to get all the issues.
            $scope.getAllIssues = function() {
                IssueFactory.getAllIssues(function(response) {
                    if (response.status === 200) {
                        $scope.data = response.data;
                        console.log($scope.data);
                    } else if (response.status === 403) {
                        LogoutFactory.logoutService($scope.sessionId);
                        window.location = '#/login';
                    }
                })
            };

            // Function to create a new issue.
            $scope.createIssue = function() {
                IssueFactory.createIssue($scope.description, $scope.subject, $scope.category, $scope.priority, function(response) {
                    if(response.status === 200) {
                       console.log("Issue Created!");
                    } else if(response.status === 403) {
                       console.log("Failed!");
                    }
                });

            };

            // When the edit issue button is clicked, redirect to the edit issue page.
            $scope.editIssue = function(issue) {
                window.location = '#/issue/edit';
            };

            // Function to edit/update an issue (DOES NOT WORK YET).
            $scope.updateIssue = function(issue) {
                $scope.description = issue.description;
            };

            // When the create new issue button/link is clicked, redirect to the create new issue page.
            $scope.createNewIssue = function() {
                window.location = '#/issue/new';
            }
    }]);
