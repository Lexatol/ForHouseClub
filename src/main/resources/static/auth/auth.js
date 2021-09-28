angular.module('app').controller('AuthController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/fhc';

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data;
                    $localStorage.currentUser = {email: $scope.user.email, token: response.data};

                    $scope.user.email = null;
                    $scope.user.password = null;

                    //console.log($localStorage.currentUser);
                }
            }, function errorCallback(response) {
                window.alert(response.data.message);
                $scope.clearUser();
            });
    };

    $scope.tryToLogout = function () {
        localStorage.clear();
        $scope.clearUser();
        try {
            if ($scope.user.email) {
                $scope.user.email = null;
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
});