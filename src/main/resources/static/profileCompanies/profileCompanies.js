angular.module('app').controller('ProfileContractorController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189';

    $scope.loadCompanies = function () {
        $http.get(contextPath + '/api/v1/profile_companies/company_info?email=' + $localStorage.currentUser.email)
            .then(function (response) {
                $scope.profileCompany = response.data;
                $scope.company = $scope.profileCompany.company;
                $scope.currentSpecList = [];
                for (let i = 0; i < $scope.profileCompany.specializations.length; ++i) {
                    let title = $scope.profileCompany.specializations[i].specializationTitle;
                    $scope.currentSpecList.push(title);
                }
                $scope.loadSpecList();
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
        /*$http.get(contextPath + '/api/v1/spec/1')

            .then(function (response) {
                $scope.newSpec = response.data;
                $scope.profileCompany.specializations.push($scope.newSpec)
            })*/

        let newSpec = Array.from($scope.specList);

        $scope.cleanSpec(newSpec)

        if(newSpec.length > 0) {
            $http.get(contextPath + '/api/v1/spec/' + newSpec[0].specializationId)
                .then(function (response) {
                    $scope.newSpec = response.data;
                    $scope.profileCompany.specializations.push($scope.newSpec)
                })
        }
    };

    $scope.cleanSpec = function(newSpec){
        for(let k = 0; k < 2; k++){
            for (let i = 0; i < newSpec.length; ++i) {
                for (let j = 0; j < $scope.profileCompany.specializations.length; j++) {
                    if(newSpec.length > 0) {
                        if ($scope.profileCompany.specializations[j].specializationTitle === newSpec[i].specializationTitle) {
                            //console.log($scope.profileCompany.specializations[j].specializationTitle + " " + $scope.specList[i].specializationTitle);
                            newSpec.splice(i, 1)
                            i = 0;
                        }
                    }
                }
            }
        }
    }

    $scope.saveCurrentSpec = function(currentSpecId, currentSpecTitle){
        $scope.currentSpecId = currentSpecId;
        $scope.currentSpecTitle = currentSpecTitle;
    }

    $scope.applyNewSpec = function (titleSpec) {
        for (let i = 0; i < $scope.currentSpecList.length; ++i) {
            if($scope.currentSpecList[i] === titleSpec){
                //console.log($scope.currentSpecId + " " + $scope.currentSpecTitle);

                //for (let i = 0; i < $scope.specList.length; ++i) {
                    //if ($scope.specList[i].specializationTitle === titleSpec) {
                        //let specId = $scope.specList[i].specializationId;

                        for (let j = 0; j < $scope.profileCompany.specializations.length; ++j) {
                            if ($scope.profileCompany.specializations[j].specializationTitle === titleSpec) {
                                if ($scope.profileCompany.specializations[j].specializationId === $scope.currentSpecId) {
                                    $scope.profileCompany.specializations[j].specializationId = $scope.currentSpecId
                                    $scope.profileCompany.specializations[j].specializationTitle = $scope.currentSpecTitle
                                    break
                                }
                            }
                        }
                    //    break
                    //}
                //}

                //console.log($scope.currentSpecList)
                $scope.currentSpecList = [];
                //console.log($scope.currentSpecList)
                for (let i = 0; i < $scope.profileCompany.specializations.length; ++i) {
                    let title = $scope.profileCompany.specializations[i].specializationTitle;
                    $scope.currentSpecList.push(title);
                }
            return;
            }
        }

        /*
        console.log($scope.currentSpec);
        console.log(titleSpec)
        for (let i = 0; i < $scope.currentSpecList.length; ++i) {
        //    for (let j = 0; j < $scope.profileCompany.specializations.length; ++j) {
            console.log($scope.currentSpecList)
                if($scope.currentSpecList[i] === titleSpec){
                    for (let j = 0; j < $scope.profileCompany.specializations.length; ++j) {
                        if ($scope.profileCompany.specializations[j].specializationTitle === titleSpec) {
                            $scope.profileCompany.specializations[j].specializationTitle = $scope.currentSpec
                        }
                    }

                    $scope.currentSpecList = [];
                    console.log($scope.currentSpecList)
                    for (let i = 0; i < $scope.profileCompany.specializations.length; ++i) {
                        let title = $scope.profileCompany.specializations[i].specializationTitle;
                        $scope.currentSpecList.push(title);
                    }
                    return;
                }
        //    }
        }*/

        for (let i = 0; i < $scope.specList.length; ++i) {
            if ($scope.specList[i].specializationTitle === titleSpec) {
                let specId = $scope.specList[i].specializationId;

                for (let j = 0; j < $scope.profileCompany.specializations.length; ++j) {
                    if ($scope.profileCompany.specializations[j].specializationTitle === titleSpec) {
                        $scope.profileCompany.specializations[j].specializationId = specId

                        $scope.currentSpecList = [];
                        for (let i = 0; i < $scope.profileCompany.specializations.length; ++i) {
                            let title = $scope.profileCompany.specializations[i].specializationTitle;
                            $scope.currentSpecList.push(title);
                        }
                        break
                    }
                }
                break
            }
        }

    };

    $scope.loadCompanies();
});
