/**
 * Created by Rekish on 11/13/2015.
 */

angular.module("DatabaseFactory")
    .controller("DatabaseController", ['$scope', 'IssueDatabase', function($scope, IssueDatabase) {

        console.log("Database Controller Initialized...");

        $scope.databaseCheck = function() {
            IssueDatabase.getAssignee(function(response) {
                if (response.status === 200) {
                    console.log(response.data);
                } else if (response.status === 403) {
                    console.log(response.data);
                }
            });

        }
    }]);
