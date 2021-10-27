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

        //$scope.profileCompany.specializations.map(function(spec) {
        //    console.log(spec.specializationTitle)
        //});
        let n = $scope.profileCompany.specializations.length;
        for (let i = 0; i < n-1; i++)
        {
            for (let j = i+1; j < n; j++)
            {
                if ($scope.profileCompany.specializations[i].specializationTitle === $scope.profileCompany.specializations[j].specializationTitle){
                    alert('Есть повторяющиеся специализации');
                    return false;
                }
            }
        }

        $http.post(contextPath + '/api/v1/profile_companies/save', $scope.profileCompany)
            .then(function (response) {
                $scope.loadCompanies();
                alert('Данные обновлены');
            });
    };

    $scope.removeThisSpec = function () {
        $scope.profileCompany.specializations.splice($scope.profileCompany.specializations.length - 1, 1);
        //console.log(specId + " " + $scope.profileCompany.specializations[0].specializationId);

        /*for (let index = 0; index < $scope.profileCompany.specializations.length; ++index) {
            if($scope.profileCompany.specializations[index].specializationId === specId) {
                $scope.profileCompany.specializations = $scope.profileCompany.specializations.splice(index, index)
            }
        }*/
    };

    $scope.addNewSpec = function () {
        $http.get(contextPath + '/api/v1/spec/1')
            .then(function (response) {
                $scope.newSpec = response.data;
                $scope.profileCompany.specializations.push($scope.newSpec)
            })
    };

    $scope.applyNewSpec = function (titleSpec) {
        for (let index = 0; index < $scope.specList.length; ++index) {
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
        }/**/
    };

    $scope.loadCompanies();
    $scope.loadSpecList();
});