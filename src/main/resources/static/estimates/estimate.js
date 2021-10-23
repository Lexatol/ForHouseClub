angular.module('app').controller('EstimateController', function ($scope, $http, $localStorage, $window, $location) {
    const contextPath = 'http://localhost:8189';
    var iter = 0;

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
        $http.get(contextPath + '/api/v1/estimates/works?estimate=' + $scope.currentEstimate.estimateId + "&category=" + $scope.currentCategory)
            .then(function (response) {
                $scope.currentWorks = response.data;
            });
        $http.get(contextPath + '/api/v1/work_templates/list?category=' + categoryId)
            .then(function (response) {
                $scope.availableWorks = response.data;
            });

    }

    $scope.saveChanges = function () {
        $http.post(contextPath + '/api/v1/estimates/save', $scope.currentEstimate)
            .then(function (response) {
                $scope.showEstimate();
                alert('Данные обновлены');
            });
    };

    $scope.addWork = function () {
        if ($scope.currentWorks === undefined || $scope.currentWorks.lenght === 0) {
            $scope.currentWorks = [];
        }
        $scope.currentWorks.push({rowId: 'New' + iter, workTemplate: {name: 'New', templateId: 0}});
        iter++;
    }

    $scope.deleteWork = function (rowId) {
        $scope.currentWorks.forEach(function (item, i, arr) {
            if (item.rowId === rowId) {
                arr.splice(i, 1);
            }
        });
    }

    $scope.showEstimate();

});
