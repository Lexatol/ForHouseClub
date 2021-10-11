angular.module('app').controller('ProfileContractorController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189';

    $scope.loadCompanies = function () {
        $http.get(contextPath + '/api/v1/profile_companies/company_info?email=' + $localStorage.currentUser.email)
            .then(function (response) {
                $scope.profileCompany = response.data;
                $scope.company = $scope.profileCompany.company;
            });
    }

    $scope.saveChanges = function () {
        $scope.profileCompany.company = $scope.company

        $http.put(contextPath + '/api/v1/profile_companies', $scope.profileCompany)
            .then(function (response) {
                alert('Данные обновлены обновлен');
            });
    };

    $scope.loadCompanies();
});