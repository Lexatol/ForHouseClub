(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config);

    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'main/main.html'
            })
            .when('/profile_companies', {
                templateUrl: 'contractor/profile_companies.html',
                controller: 'ProfileContractorController'
            })
            .when('/users', {
                templateUrl: 'users/users.html',
                controller: 'UserController'
            })
            .otherwise({
                redirect: '/'
            });
    }
})();