angular.module('app').controller('UserController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189';

    $scope.loadProfile = function () {
        $http.get(contextPath + '/api/v1/users/profile/' + $localStorage.currentUser.email)
            .then(function (response) {
                $scope.user = response.data;
            });
    }

    $scope.submitUpdateProfile = function () {
        $http.put(contextPath + '/api/v1/users', $scope.user)
            .then(function (response) {
                $scope.loadProfile();
                alert('Профиль обновлен');
            });
    };

    $scope.loadProfile();
});