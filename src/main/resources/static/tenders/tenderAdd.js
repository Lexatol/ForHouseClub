angular.module('app').controller('TenderAddController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189';

    $scope.loadCompanies = function () {
        $http.get(contextPath + '/api/v1/profile_companies/company_info?email=' + $localStorage.currentUser.email)
            .then(function (response) {
                $scope.profileCompany = response.data.company;
                $scope.tender={
                    customer: {companyName: $scope.profileCompany.companyName}
                }
            });
    }

    $scope.submitTender = function () {
        $http.put(contextPath + '/api/v1/tenders/add', $scope.tender)
            .then(function (response) {
                //console.log(response)
                alert('Тендер добавлен');
            }, function errorCallback(response) {
                //console.log(response)
                window.alert(response.data.message);
            });
    };

    /*$scope.loadPlatformList = function () {
        $http.get(contextPath + "api/v1/platform")
            .then(function (response) {
                $scope.platformList = response.data;
            })
    }*/

    $scope.loadCompanies();
});