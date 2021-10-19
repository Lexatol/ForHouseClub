angular.module('app').controller('EstimateController', function ($scope, $http, $localStorage, $window, $location) {
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

    $scope.showEstimate = function () {
        const n = $location.path().split("/");
        const id = n[n.length - 1];
        $http.get(contextPath + '/api/v1/estimates/' + id)
            .then(function (response) {
                $scope.currentEstimate = response.data;
            });
    }

    $scope.saveChanges = function () {
        $http.post(contextPath + '/api/v1/estimates/save', $scope.currentEstimate)
            .then(function (response) {
                $scope.showEstimate();
                alert('Данные обновлены');
            });
    };

    if ($location.path().endsWith('/estimates')) {
        $scope.showEstimatesPage();
    } else {
        $scope.showEstimate();
    }

});
