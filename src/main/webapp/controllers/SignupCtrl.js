/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("issueTracker")
    .controller("SignupController", ['$scope', 'AuthService', function($scope, AuthService) {

        $scope.signupUser = function() {
            console.log("Signup Controller Initialized...");

            AuthService.signup($scope.username, $scope.password, $scope.email, function (response) {
                if (response.status === 200) {
                    console.log("Response: " + response.data);
                }

                if(response.status === 500) {
                    console.log("Error: " + response.data);
                }
            })
        }
    }]);
