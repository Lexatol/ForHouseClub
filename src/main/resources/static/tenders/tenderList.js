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

    $scope.showTendersPage();
});
