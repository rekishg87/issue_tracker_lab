/**
 * Created by Rekish on 11/13/2015.
 */

angular.module("DatabaseFactory")
    .controller("DatabaseController", ['$scope', '$localStorage', 'IssueDatabase', 'ValidationFactory',
        function($scope, $localStorage, IssueDatabase, ValidationFactory) {
            // Validate if user has a valid session.
            $scope.checkValidity = function() {
                ValidationFactory.validate();
            };

            // Get all category data and add it to a $localStorage to persist the data.
            $scope.categoryData = function() {
                IssueDatabase.getCategory(function(response) {
                    if (response.status === 200) {
                        $localStorage.categoryDataStorage = response.data;
                        $scope.categoryDataScope = $localStorage.categoryDataStorage;
                    } else if (response.status === 403) {
                        console.log(response.data);
                    }
                });
            };

            // Get all priority data and add it to a $localStorage to persist the data.
            $scope.priorityData = function() {
                IssueDatabase.getPriority(function(response) {
                    if (response.status === 200) {
                        $localStorage.priorityDataStorage = response.data;
                        $scope.priorityDataScope = $localStorage.priorityDataStorage;
                    } else if (response.status === 403) {
                        console.log(response.data);
                    }
                });
            };
        }]);
