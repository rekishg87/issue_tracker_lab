/**
 * Created by Rekish on 9/21/2015.
 */

angular.module('Authentication', []);
angular.module('Home', []);

angular.module('issueTracker', ['Authentication', 'Home', 'ngRoute', 'ngCookies'])
    .config(['$routeProvider', function($routeProvider) {

        $routeProvider
            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'authentication/views/login.html',
                hideMenus: true
            })

            .when('/', {
                controller: 'HomeController',
                templateUrl: 'home/views/home.html'
            })

            .otherwise({ redirectTo: '/login' });
    }])

    .run(['$rootScope', '$location', '$cookieStore', '$http',
            function($rootScope, $location, $cookieStore, $http) {
                // Keep user logged in after page refresh
                $rootScope.globals = $cookieStore.get('globals') || {};
                if($rootScope.globals.currentUser) {
                    $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.globals.currentUser.authData;
                }

                $rootScope.$on('$locationChangeStart', function(event, next, current) {
                    // redirect to login page if not logged in
                    if($location.path() !== '/login' && !$rootScope.globals.currentUser) {
                        $location.path('/login');
                    }
                });
            }]);


