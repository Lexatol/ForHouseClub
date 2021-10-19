angular.module('app').controller('ProfileContractorController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189';

    $scope.loadCompanies = function () {
        $http.get(contextPath + '/api/v1/profile_companies/company_info?email=' + $localStorage.currentUser.email)
            .then(function (response) {
                $scope.profileCompany = response.data;
                $scope.company = $scope.profileCompany.company;
            });
    }

    $scope.loadSpecList =  function () {
        $http.get(contextPath + '/api/v1/spec')
            .then(function (response) {
                $scope.specList = response.data;
            })
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
        let newSpec = null
        $scope.profileCompany.specializations.push(newSpec);
    };

    $scope.applyNewSpec = function (titleSpec) {
    /*    for (let index = 0; index < $scope.specList.length; ++index) {
            if($scope.specList[index].specializationTitle === titleSpec){
                let specId = $scope.specList[index].specializationId;

                for (let index = 0; index < $scope.profileCompany.specializations.length; ++index) {
                    if($scope.profileCompany.specializations[index].specializationTitle === titleSpec) {
                        $scope.profileCompany.specializations[index].specializationId = specId
                        break
                    }
                }
                break
            }
        }*/
    };

    $scope.loadCompanies();
    $scope.loadSpecList();
});