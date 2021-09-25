angular.module('app').controller('UserController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/fhc';

    $scope.loadProfile = function () {
        $http.get(contextPath + '/api/v1/users/1')
            .then(function (response) {
                $scope.user = response.data;
            });
    }

    $scope.loadProfile();
});