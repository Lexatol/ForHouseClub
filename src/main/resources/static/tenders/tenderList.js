angular.module('app').controller('TenderListController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189';

    $scope.showTendersPage = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/tenders',
            method: 'GET',
            params: {
                page: pageIndex
            },
            page: pageIndex
        }).then(function (response) {
            $scope.tendersList = response.data;
        });
    };

    $scope.setThisForMe = function(tenderId){
        $http.get(contextPath + '/api/v1/tenders/set_contractor/' + $scope.profileCompany.companyName + "/" + tenderId)
            .then(function (response) {
                $scope.showTendersPage();
            })
    }

    $scope.removeThisFromMe = function(tenderId){
        $http.get(contextPath + '/api/v1/tenders/remove_contractor/' + tenderId)
            .then(function (response) {
                $scope.showTendersPage();
            })
    }

    $scope.loadCompanies = function () {
        $http.get(contextPath + '/api/v1/profile_companies/company_info?email=' + $localStorage.currentUser.email)
            .then(function (response) {
                $scope.profileCompany = response.data.company;
            });
    }

    $scope.showTendersPage();
    $scope.loadCompanies();
});
