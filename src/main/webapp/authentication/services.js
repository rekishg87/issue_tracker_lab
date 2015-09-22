/**
 * Created by Rekish on 9/21/2015.
 */

angular.module('Authentication')
    .constant('dataUrl', 'http://localhost:8080/issueTracker/api/user/login')
    .factory('AuthenticationService', ['$http', '$cookieStore', '$rootScope', '$timeout',
        function($http, $cookieStore, $rootScope, $timeout) {
            var service = {};

            service.login2 = function(username, password, callback) {

                /* Dummy authentication for testing, uses $timeout to simulate api call
                 ----------------------------------------------*/
                //$timeout(function(){
                //    var response = { success: username === 'test' && password === 'test' };
                //    if(!response.success) {
                //        response.message = 'Username or password is incorrect';
                //    }
                //    $rootScope.username1 = username;
                //    callback(response);
                //}, 1000);

                /* Use this for real authentication
                 ----------------------------------------------*/
                $http.post('api/user/login', { username: username, password: password })
                    .success(function(response) {
                        $rootScope.username1 = username;
                        callback(response);
                    });
            };

            service.getCookie = function() {
                var cookie = $cookieStore.get('JSESSIONID');
            };

            service.SetCredentials = function(username) {
                var authData = 'authData';

                $rootScope.globals = {
                    currentUser: {
                        username: username,
                        authData: authData
                    }
                };

                $http.defaults.headers.common['Authorization'] = 'Basic' + authData;
                $cookieStore.put('globals', $rootScope.globals);
            };

            service.ClearCredentials = function() {
                $rootScope.globals = {};
                $cookieStore.remove('globals');
                $http.defaults.headers.common.Authorization = 'Basic';
            };

            return service;
        }]);