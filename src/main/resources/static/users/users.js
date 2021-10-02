angular.module('app').controller('UserController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189';

    $scope.loadProfile = function () {
        $http.get(contextPath + '/api/v1/users/profile/' + $localStorage.currentUser.email)
            .then(function (response) {
                $scope.user = response.data;
            });
    }

    $scope.loadProfile();
});