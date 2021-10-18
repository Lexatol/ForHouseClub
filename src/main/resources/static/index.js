(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage', 'editElement'])
        .config(config)
        .controller('IndexController', function($scope, $http, $localStorage, $route) {
            $scope.$route = $route;

            $scope.tryToLogout = function () {
                localStorage.clear();
                $scope.clearUser();
                try {
                    if ($scope.user.username) {
                        $scope.user.username = null;
                    }
                    if ($scope.user.password) {
                        $scope.user.password = null;
                    }
                } catch (e) {
                    console.log("already cleaned")
                }
            };

            $scope.clearUser = function () {
                delete $localStorage.currentUser;
                $http.defaults.headers.common.Authorization = '';
            };

            $scope.isUserLoggedIn = function () {
                if ($localStorage.currentUser) {
                    return true;
                } else {
                    return false;
                }
            };
        })
        .run(run);

    function config($routeProvider, $httpProvider) {
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
            .when('/register', {
                templateUrl: 'register/register.html',
                controller: 'regController',
                active:'regPage'
            })
            .otherwise({
                redirect: '/'
            });


        $httpProvider.interceptors.push(function ($q, $location) {
            return {
                'responseError': function (rejection, $localStorage, $http) {
                    let defer = $q.defer();
                    if (rejection.status === 401 || rejection.status === 403) {
                        console.log('error: 401-403');
                        $location.path('/auth');
                        if (!(localStorage.getItem("localUser") === null)) {
                            delete $localStorage.currentUser;
                            $http.defaults.headers.common.Authorization = '';
                            console.log('zxc');
                        }
                        console.log(rejection.data);
                        var answer = JSON.parse(rejection.data);
                        console.log(answer);
                        // window.alert(answer.message);
                    }
                    defer.reject(rejection);
                    return defer.promise;
                }
            };
        });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
        }
    }
})();
