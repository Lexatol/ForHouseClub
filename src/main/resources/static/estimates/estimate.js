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
        $http.get(contextPath + "/api/v1/work_templates/categories")
            .then(function (response) {
                $scope.categories = response.data;
                $scope.currentCategory = response.data[0].categoryId;
            })
    }

    $scope.openCategory = function (categoryId) {
        $scope.currentCategory = categoryId;
    }

    $scope.saveChanges = function () {
        $http.post(contextPath + '/api/v1/estimates/save', $scope.currentEstimate)
            .then(function (response) {
                $scope.showEstimate();
                alert('Данные обновлены');
            });
    };

    $scope.addWork = function () {
        $http.get(contextPath + "/api/v1/estimates/addwork?estimate=" + $scope.currentEstimate + "&work=" + 1)
            .then(function (response) {
                    $scope.currentEstimate.works.push(response.data);
                }
            );
    }

    if ($location.path().endsWith('/estimates')) {
        $scope.showEstimatesPage();
    } else {
        $scope.showEstimate();
    }

});
