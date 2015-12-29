/**
 * Created by Rekish on 12/14/2015.
 */

angular.module("IssueMod")
    .controller("EditIssueController", ['$scope', '$rootScope', '$localStorage', 'ValidationFactory', 'IssueFactory',
        function($scope, $rootScope, $localStorage, ValidationFactory, IssueFactory) {

            console.log("Edit Issue Controller Initialized...");

            $scope.checkValidity = function() {
                console.log("checkValidity() loaded...");
                ValidationFactory.validate();
            };

            $scope.updateIssue = function() {
                IssueFactory.updateIssue($scope.description, $scope.subject, $scope.category, $scope.priority, function(response) {
                    if(response.status === 200) {
                        console.log("Issue Updated!");
                    } else if(response.status === 403) {
                        console.log("Failed!");
                    }
                });
            };

        }]);
