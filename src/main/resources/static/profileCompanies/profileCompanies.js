angular.module('app').controller('ProfileContractorController', function ($scope, $http, $localStorage, $window) {
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
                $scope.loadMyTenders();
                $scope.loadChosenTenders();
            });
    }

    $scope.loadMyTenders = function(){
        $http.get(contextPath + '/api/v1/tenders/comp/' + $scope.company.companyName)
            .then(function (response) {
                $scope.myTenders = response.data;
            })
    }

    $scope.loadChosenTenders = function(){
        $http.get(contextPath + '/api/v1/tenders/chosen/' + $scope.company.companyName)
            .then(function (response) {
                $scope.chosenTenders = response.data;
            })
    }

    $scope.removeThisContractor = function(tenderId){
        $http.get(contextPath + '/api/v1/tenders/remove_contractor/' + tenderId)
            .then(function (response) {
                $scope.loadMyTenders();
                $scope.loadChosenTenders();
            })
    }

    $scope.approveThisContractor = function(tenderId) {
        $http.get(contextPath + '/api/v1/tenders/approve_contractor/' + tenderId)
            .then(function (response) {
                $scope.loadMyTenders();
            })
    }

    $scope.closeTender = function(tenderId) {
        $http.get(contextPath + '/api/v1/tenders/close_tender/' + tenderId)
            .then(function (response) {
                $scope.loadMyTenders();
            })
    }

    $scope.removeThisTender = function(tenderId){
        $http.delete(contextPath + '/api/v1/tenders?tenderId=' + tenderId)
            .then(function (response) {
                $scope.loadMyTenders();
            })
    }

    $scope.loadSpecList =  function () {
        $http.get(contextPath + '/api/v1/spec')
            .then(function (response) {
                $scope.specList = response.data;
                $scope.buildSelectArray();
            })
        }

    $scope.saveChanges = function () {
        $scope.profileCompany.company = $scope.company

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
        $scope.buildSelectArray()
    };

    $scope.addNewSpec = function () {
        let newSpec = Array.from($scope.specList);

        $scope.cleanSpec(newSpec)

        if(newSpec.length > 0) {
            $http.get(contextPath + '/api/v1/spec/' + newSpec[0].specializationId)
                .then(function (response) {
                    $scope.newSpec = response.data;
                    $scope.profileCompany.specializations.push($scope.newSpec)
                    $scope.buildSelectArray()
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
                        for (let j = 0; j < $scope.profileCompany.specializations.length; ++j) {
                            if ($scope.profileCompany.specializations[j].specializationTitle === titleSpec) {
                                if ($scope.profileCompany.specializations[j].specializationId === $scope.currentSpecId) {
                                    $scope.profileCompany.specializations[j].specializationId = $scope.currentSpecId
                                    $scope.profileCompany.specializations[j].specializationTitle = $scope.currentSpecTitle
                                    break
                                }
                            }
                        }
                $scope.currentSpecList = [];
                for (let i = 0; i < $scope.profileCompany.specializations.length; ++i) {
                    let title = $scope.profileCompany.specializations[i].specializationTitle;
                    $scope.currentSpecList.push(title);
                }
            return;
            }
        }

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
        $scope.buildSelectArray();
    };

    $scope.buildSelectArray = function() {
        for (let i = 0; i < $scope.profileCompany.specializations.length; ++i) {
            $scope.specArrays = new Array($scope.profileCompany.specializations.length);
            for (let j = 0; j < $scope.specArrays.length; j++) {
                $scope.specArrays[j] = Array.from($scope.specList);
            }
        }

        if($scope.specArrays) {
            for (let i = 0; i < $scope.specArrays.length; ++i) {
                for (let j = 0; j < $scope.specArrays[i].length; ++j) {
                    for (let k = 0; k < $scope.profileCompany.specializations.length; ++k) {
                        //console.log(i + " " + j + " " + $scope.specArrays[i][j].specializationTitle + " " + $scope.profileCompany.specializations[i].specializationTitle)
                        if ($scope.specArrays[i][j].specializationTitle === $scope.profileCompany.specializations[k].specializationTitle) {
                            if (i !== k) {
                                $scope.specArrays[i].splice(j, 1)
                                i = 0;
                                j = 0;
                            }
                            //console.log("rem "+ i + " " + j + " " + $scope.specArrays[i][j].specializationTitle + " " + $scope.profileCompany.specializations[i].specializationTitle)
                        }
                    }
                }
            }
        }
    }

    $scope.viewTender = function (id) {
        $window.location.href = contextPath + '/#!/tenderView/' + id;
    }

    $scope.loadCompanies();
});
