angular.module('app').controller('userController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/fhc';

    $scope.loadUsers = function () {
        $http.get(contextPath + '/api/v1/users')
            .then(function (response) {
                $scope.users = response.data;
                $scope.loadProfile();
            });
    }

    $scope.loadProfile = function () {
        $http.get(contextPath + '/api/v1/users/1')
            .then(function (response) {
                $scope.user = response.data;
            });
    }

    $scope.loadProfile();
});