angular.module('app').controller('ProfileContractorController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189';

    // http://localhost:8189/api/v1/profile_companies/company_info?email=petya@google.com

    $scope.loadCompanies = function () {
        $http.get(contextPath + '/api/v1/profile_companies/company_info?email=' + $localStorage.currentUser.email)
            .then(function (response) {
                $scope.companies = response.data;
                $scope.cDto = $scope.companies.companyDto;
            });
    }

    $scope.loadCompanies();
});