angular.module('app').controller('AuthController', function ($scope, $http, $localStorage, $window) {
    const contextPath = 'http://localhost:8189';

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.currentUser = {email: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $window.location.href = contextPath + '#!/users';
                    //console.log($localStorage.currentUser);
                }
            }, function errorCallback(response) {
                try {
                    window.alert(response.data.message);
                } catch (e) {}
                $scope.clearUser();
            });
    };
});