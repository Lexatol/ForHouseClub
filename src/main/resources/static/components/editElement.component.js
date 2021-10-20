'use strict'

angular.module('editElement')
    .component('editElement', {
        templateUrl: '/templates/edit-elem.html',
        bindings: {
            name: '@',
            edit: '='
        },
        controller: function ($scope) {
            $scope.onLoad = function() {
                $scope.currentEdit = this.$ctrl.edit;
            }

            $scope.sendData = function () {
                this.$ctrl.edit = $scope.currentEdit
                $scope.editing = false
            }

            $scope.closeEdit = function () {
                $scope.editing = false
            }

            $scope.startEdit = function () {
                $scope.currentEdit = this.$ctrl.edit
                $scope.editing = true
            }

            $scope.onLoad();
        }
    });