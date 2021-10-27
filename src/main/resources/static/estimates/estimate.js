angular.module('app').controller('EstimateController', function ($scope, $http, $localStorage, $window, $location) {
    const contextPath = 'http://localhost:8189';
    var iter = 0;

    $scope.showEstimate = function () {
        const n = $location.path().split("/");
        const id = n[n.length - 1];
        $scope.selectedItem = 0;
        $http.get(contextPath + '/api/v1/estimates/' + id)
            .then(function (response) {
                $scope.currentEstimate = response.data;
            });

        $http.get(contextPath + "/api/v1/work_templates/categories")
            .then(function (response) {
                $scope.categories = response.data;
                $scope.currentCategory = response.data[0].categoryId;
                $scope.openCategory($scope.currentCategory);
            })

    }

    $scope.openCategory = function (categoryId) {
        $scope.currentCategory = categoryId;
        $http.get(contextPath + '/api/v1/work_templates/list?category=' + categoryId)
            .then(function (response) {
                $scope.availableWorks = response.data;
            });
        if ($scope.currentEstimate !== undefined) {
            $http.get(contextPath + '/api/v1/estimates/works?estimate=' + $scope.currentEstimate.estimateId + "&category=" + $scope.currentCategory)
                .then(function (response) {
                    $scope.currentWorks = response.data;
                });
        }
    }

    $scope.saveChanges = function () {
        if ($scope.editWorks === undefined || $scope.editWorks.lenght === 0) {
            $scope.editWorks = [];
        }
        $scope.editWorks.forEach(function (item, i, arr) {
            if (item.templateId > 0) {
                $http.get(contextPath + '/api/v1/estimates/addwork?estimate=' + $scope.currentEstimate.estimateId + '&work=' + item.templateId);
            }
        });
        $http.post(contextPath + '/api/v1/estimates/save', $scope.currentEstimate)
            .then(function (response) {
                $scope.showEstimate();
                $scope.editWorks = [];
                alert('Данные обновлены');
            });
    };

    $scope.addWork = function () {
        if ($scope.editWorks === undefined || $scope.editWorks.lenght === 0) {
            $scope.editWorks = [];
        }
        $scope.editWorks.push({rowId: 'New' + iter, templateId: 0, category: $scope.currentCategory});
        iter++;
    }

    $scope.changeSelectedItem = function (rowId) {
        $scope.editWorks.forEach(function (item, i, arr) {
            if (item.rowId === rowId) {
                item.templateId = $scope.selectedItem;
            }
        });
    }

    $scope.deleteWork = function (rowId) {
        $scope.currentWorks.forEach(function (item, i, arr) {
            if (item.rowId === rowId) {
                $http.delete(contextPath + '/api/v1/estimates/works?estimate=' + $scope.currentEstimate.estimateId + '&work=' + rowId)
                    .then(function (response) {
                            arr.splice(i, 1);
                        }
                    )
            }
        });
        $scope.editWorks.forEach(function (item, i, arr) {
            if (item.rowId === rowId) {
                arr.splice(i, 1);
            }
        });
    }

    $scope.showEstimate();

});
