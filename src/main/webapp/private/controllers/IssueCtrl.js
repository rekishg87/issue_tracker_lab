/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("IssueMod")
    .controller("IssueController", ['$scope', '$http', 'IssueServices', function($scope, $http, IssueServices) {

        $scope.getAllIssues = function() {
            console.log("Issue Controller Initialized...");

            IssueServices.val(function(response) {
                if (response.status === 200) {
                    var rData = response.data;
                    console.log("Log: " + rData.toString());
                } else if (response.status === 403) {
                    AuthService.logoutService($scope.sessionId);
                    window.location = '#/login';
                }
            })
        }
    }]);
