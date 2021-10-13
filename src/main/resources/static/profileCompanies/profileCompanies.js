angular.module('app').controller('ProfileContractorController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189';

    $scope.loadCompanies = function () {
        $http.get(contextPath + '/api/v1/profile_companies/company_info?email=' + $localStorage.currentUser.email)
            .then(function (response) {
                $scope.profileCompany = response.data;
                $scope.company = $scope.profileCompany.company;
                console.log($scope.profileCompany)
            });
    }

    $scope.saveChanges = function () {
        $scope.profileCompany.company = $scope.company

        $http.post(contextPath + '/api/v1/profile_companies/save', $scope.profileCompany)
            .then(function (response) {
                $scope.loadCompanies();
                alert('Данные обновлены');
            });
    };

    $scope.removeThisSpec = function (specId) {
        console.log(specId + " " + $scope.profileCompany.specializations[0].specializationId);

        for (let index = 0; index < $scope.profileCompany.specializations.length; ++index) {
            if($scope.profileCompany.specializations[index].specializationId === specId) {
                $scope.profileCompany.specializations = $scope.profileCompany.specializations.splice(index, index)
            }
        }
    };

    $scope.addNewSpec = function () {
        let newSpec = {specializationId: "", specializationTitle: ""}
        $scope.profileCompany.specializations.push(newSpec);
    };

    $scope.loadCompanies();
});