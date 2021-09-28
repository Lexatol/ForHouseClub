(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .controller('IndexController', function($scope, $http, $localStorage, $route) {
            $scope.$route = $route;

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
                templateUrl: 'main/main.html',
                active:'homePage'
            })
            .when('/profileCompanies', {
                templateUrl: 'profileCompanies/profileCompanies.html',
                controller: 'ProfileContractorController',
                active:'profileCompanies'
            })
            .when('/users', {
                templateUrl: 'users/users.html',
                controller: 'UserController',
                active:'userPage'
            })
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'AuthController',
                active:'authPage'
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