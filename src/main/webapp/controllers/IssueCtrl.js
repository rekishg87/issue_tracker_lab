/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("issueTracker")
    .controller("IssueController", ['$scope', '$http', 'ValService', function($scope, $http, ValService) {

        $scope.getAllIssues = function() {
            console.log("Issue Controller Initialized...");

            ValService.val(function(response) {
                if(response.status === 200) {
                    var rData = response.data;
                    console.log("All Issues: " + rData.toString());
                }
            })
        }

    }]);
