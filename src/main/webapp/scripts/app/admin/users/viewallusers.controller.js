'use strict';

angular.module('mainApp')
    .controller('ViewAllUsersController', function ($scope, AdminService) {

        $scope.users = null;

        $scope.getUsers = function () {
            $scope.users = null;
            AdminService.findAll().then(function (r) {
                $scope.users = r; //returned is a promise, wait for the request to complete and assign gishs.
            });
        };

        $scope.deleteUser = function (login) {
            if (confirm("Are you sure you want to delete user [" + login + "] ?")) {
                AdminService.deleteUser(login).then(function (r) {
                    alert(r.data);
                    $scope.getUsers();
                });
            }
        };

        $scope.getUsers();

    });
