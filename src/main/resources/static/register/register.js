angular.module('app').controller('regController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189';

    $scope.tryToRegister = function () {
        $http.post(contextPath + '/register', $scope.user)
            .then(function successCallback(response) {
                alert('Регистрация прошла успешно');
            }, function errorCallback(response) {
                window.alert(response.data.message);
                $scope.clearUser();
            });
    }
});