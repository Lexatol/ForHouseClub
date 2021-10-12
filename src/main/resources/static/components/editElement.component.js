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
            }

            $scope.closeEdit = function () {
                this.$ctrl.edit = $scope.currentEdit
                $scope.editing = false
            }

            $scope.startEdit = function () {
                $scope.editing = true
                $scope.currentEdit = this.$ctrl.edit
            }
        }
    });