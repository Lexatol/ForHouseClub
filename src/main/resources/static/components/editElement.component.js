'use strict'

angular.module('editElement')
    .component('editElement', {
        templateUrl: '/templates/edit-elem.html',
        bindings: {
            name: '@',
            edit: '='
        },
        controller: function ($scope) {
            $scope.sendData = function () {
                $scope.editing = false
                console.log(this.$ctrl.edit)
            };
        }
    });