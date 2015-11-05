/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("IssueMod")
    .controller("IssueController", ['$scope', 'IssueFactory', 'LogoutFactory',
        function($scope, IssueFactory, LogoutFactory) {

        $scope.getAllIssues = function() {
            console.log("Issue Controller Initialized...");

            IssueFactory.validate(function(response) {
                if (response.status === 200) {
                    var rData = response.data;
                    console.log("Log: " + rData.toString());
                } else if (response.status === 403) {
                    LogoutFactory.logoutService($scope.sessionId);
                    window.location = '#/login';
                }
            })
        }
    }]);
