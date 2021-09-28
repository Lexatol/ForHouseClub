angular.module('app').controller('ProfileContractorController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/fhc';

    $scope.loadCompanies = function () {
        $http.get(contextPath + '/api/v1/profile_companies/1')
            .then(function (response) {
                $scope.companies = response.data;
                $scope.cDto = $scope.companies.lkCompanyDto.companyDto;
                $scope.cCustomer = $scope.companies.lkCompanyDto.companyCustomer;
                $scope.cProvider = $scope.companies.lkCompanyDto.companyProvider;
            });
    }

    $scope.loadCompanies();
});