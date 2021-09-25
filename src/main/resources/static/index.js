(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run();

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
            .otherwise({
                redirect: '/'
            });
    }
})();