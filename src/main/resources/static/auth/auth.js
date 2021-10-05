angular.module('app').controller('AuthController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189';

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.currentUser = {email: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    //console.log($localStorage.currentUser);
                }
            }, function errorCallback(response) {
                try {
                    window.alert(response.data.message);
                } catch (e) {}
                $scope.clearUser();
            });
    };

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
});