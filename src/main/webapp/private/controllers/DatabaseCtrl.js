/**
 * Created by Rekish on 11/13/2015.
 */

angular.module("DatabaseFactory")
    .controller("DatabaseController", ['$scope', '$localStorage', 'IssueDatabase',
        function($scope, $localStorage, IssueDatabase) {

            console.log("Database Controller Initialized...");

            $scope.retrieveData = function() {
                categoryData();
            };

            $scope.categoryData = function() {
                IssueDatabase.getCategory(function(response) {
                    if (response.status === 200) {
                        $localStorage.categoryDataStorage = response.data;
                        $scope.categoryDataScope = $localStorage.categoryDataStorage;
                        console.log(response.data);
                    } else if (response.status === 403) {
                        console.log(response.data);
                    }
                });
            };

            $scope.priorityData = function() {
                IssueDatabase.getPriority(function(response) {
                    if (response.status === 200) {
                        $localStorage.priorityDataStorage = response.data;
                        $scope.priorityDataScope = $localStorage.priorityDataStorage;
                        console.log(response.data);
                    } else if (response.status === 403) {
                        console.log(response.data);
                    }
                });
            }
        }]);