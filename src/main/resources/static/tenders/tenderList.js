angular.module('app').controller('TenderListController', function ($scope, $http, $location) {
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

    $scope.loadPlatforms = function () {
        $http({
            url: contextPath + '/api/v1/platform',
            method: 'GET',
        }).then(function (response) {
            $scope.platformList = response.data;
        });
    };

    $scope.filterTenders = function (id) {
        alert('В списке тендеров буду показывать только: '+id);
    }

    $scope.addTender = function () {
        $location.path('/tenderAdd');
    }

    $scope.viewTender = function (id) {
        $location.path('/tenderView/' + id);
    }

    $scope.showTender = function () {
        const n = $location.path().split("/");
        const id = n[n.length - 1];
        $http.get(contextPath + '/api/v1/tenders/id/' + id)
            .then(function (response) {
                $scope.currentTender = response.data;
            });
    }

    // $scope.loadTenderId = function (pageIndex = 1) {
    //     $http({
    //         url: contextPath + '/api/v1/tenders',
    //         method: 'GET',
    //         params: {
    //             page: pageIndex
    //         },
    //         page: pageIndex
    //     }).then(function (response) {
    //         $scope.tendersList = response.data;
    //     });
    // };

    if ($location.path().endsWith('/')) {
        $scope.showTendersPage();
        $scope.loadPlatforms();
    }else{
        $scope.showTender();
    }

});