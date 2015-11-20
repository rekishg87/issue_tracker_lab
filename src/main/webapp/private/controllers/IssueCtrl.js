/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("IssueMod")
    .controller("IssueController", ['$scope', '$rootScope', '$localStorage', 'ValidationFactory', 'IssueFactory', 'LogoutFactory',
        function($scope, $rootScope, $localStorage, ValidationFactory, IssueFactory, LogoutFactory) {

            console.log("Issue Controller Initialized...");

            $scope.data = {};

            $scope.checkValidity = function() {
                console.log("checkValidity() loaded...");
                ValidationFactory.validate();
            };

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

            $scope.createIssue = function() {
                IssueFactory.createIssue($scope.description, $scope.category, function(response) {
                    if(response.status === 200) {
                        $scope.description = "";
                        $scope.category = "";
                        $scope.priority = "";
                       console.log("Issue Created!");
                    } else if(response.status === 403) {
                       console.log("Failed!");
                    }
                })
            };

            $scope.createNewIssue = function() {
                window.location = '#/issue/new';
            }
    }]);
