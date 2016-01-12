/**
 * Created by Rekish on 10/6/2015.
 */

angular.module("IssueMod")
    .controller("IssueController", ['$scope', '$filter', '$rootScope', '$localStorage', 'ValidationFactory', 'IssueFactory', 'LogoutFactory',
        function($scope, $filter, $rootScope, $localStorage, ValidationFactory, IssueFactory, LogoutFactory) {

            console.log("Issue Controller Initialized...");
            // Check if the File API is supported by the browser.
            if (window.File && window.FileReader && window.FileList && window.Blob) {
                //alert('Great success! All the File APIs are supported.');
            } else {
                alert('The File APIs are not fully supported in this browser.');
            }

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
                        // In the response.data the
                        // data[0] index has the issues table,
                        // data[1] index has the status table.
                        $scope.data = response.data;
                        $scope.status = $scope.data[1];

                        // Purely for logging purposes, to log which issue is edited and
                        // which new value is being set for the field that is being edited.
                        for(var i = 0; i < $scope.data[0].length; i++) {
                            console.log("DATA " + $scope.data[0][i].statusId);
                        }
                        console.log("STATUS " + $scope.status);
                        console.log("status id 1: " + $scope.status[0]);
                    } else if (response.status === 403) {
                        LogoutFactory.logoutService($scope.sessionId);
                        window.location = '#/login';
                    }
                })
            };

            // Function to create a new issue. (The added function to upload a file is not fully working yet!!!)
            $scope.createIssue = function(opt_startByte, opt_stopByte) {
                var start = parseInt(opt_startByte) || 0;
                var stop = parseInt(opt_stopByte) || $scope.myFile.size - 1;
                var blob = $scope.myFile.slice(start, stop + 1);

                var reader = new FileReader();
                reader.readAsBinaryString(blob);
                console.log("FileReader: " + reader);
                IssueFactory.createIssue($scope.description, $scope.subject, $scope.category, $scope.priority, blob, function(response) {
                    if(response.status === 200) {
                        window.location = '#/issue';
                        alert('Issue created!');
                       console.log("Issue Created!");
                    } else if(response.status === 403) {
                       console.log("Failed!");
                    }
                });

            };

            // Function to edit/update an issue.
            $scope.updateIssue = function(data, issueId) {
                IssueFactory.updateIssue(issueId, data.status, function(response) {
                    if (response.status === 200) {
                        console.log("Updated!");
                    } else if (response.status === 403) {
                        LogoutFactory.logoutService($scope.sessionId);
                        window.location = '#/login';
                    }
                });

            };

            // When the create new issue button/link is clicked, redirect to the create new issue page.
            $scope.createNewIssue = function() {
                window.location = '#/issue/new';
            };

            $scope.removeIssue = function (issueId) {
                IssueFactory.removeIssue(issueId, function(response) {
                    if (response.status === 200) {
                        $scope.getAllIssues();
                        console.log("removed!");
                    } else if (response.status === 403) {
                        LogoutFactory.logoutService($scope.sessionId);
                        window.location = '#/login';
                    }
                });
            };

            $scope.showStatus = function(issue) {
                var selected = [];
                if (issue.statusId) {
                    selected = $filter('filter')($scope.status, {
                        id: issue.statusId
                    });
                }
                return selected.length ? selected[0].name : 'Not set';
            };

            $scope.uploadFile = function(){
                var file = $scope.myFile;
                console.log('file is ' );
                console.dir(file);

            };

    }]);
