angular.module('app').controller('EstimateListController', function ($scope, $http, $localStorage, $window, $location) {
    const contextPath = 'http://localhost:8189';

    $scope.showEstimatesPage = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/estimates',
            method: 'GET',
            params: {
                page: pageIndex
            },
            page: pageIndex
        }).then(function (response) {
            $scope.EstimatesPage = response.data;

            let minPageIndex = pageIndex - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = pageIndex + 2;
            if (maxPageIndex > $scope.EstimatesPage.totalPages) {
                maxPageIndex = $scope.EstimatesPage.totalPages;
            }

            $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.createEstimate = function () {
        $http.post(contextPath + '/api/v1/estimates/new')
            .then(function (response) {
                $scope.editEstimate(response.data.estimateId);
            });
    }

    $scope.editEstimate = function (estimateId) {
        $location.path('/estimates/' + estimateId);
    }

    $scope.showEstimatesPage();

});
