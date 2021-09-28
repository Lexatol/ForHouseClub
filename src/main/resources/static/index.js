(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .controller('IndexController', function($scope, $http, $localStorage) {
            $scope.hello = "hello"

            $scope.isUserLoggedIn = function () {
                if ($localStorage.currentUser) {
                    return true;
                } else {
                    return false;
                }
            };
        })
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'main/main.html'
            })
            .when('/profileCompanies', {
                templateUrl: 'profileCompanies/profileCompanies.html',
                controller: 'ProfileContractorController'
            })
            .when('/users', {
                templateUrl: 'users/users.html',
                controller: 'UserController'
            })
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'AuthController'
            })
            .otherwise({
                redirect: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
        }
    }
})();