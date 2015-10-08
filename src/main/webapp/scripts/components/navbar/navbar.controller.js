'use strict';

angular.module('mainApp')
    .controller('NavbarController', function ($scope, $location, $state, Auth, Principal) {
        $scope.isAuthenticated = Principal.isAuthenticated;
        $scope.isInRole = Principal.isInRole;
        $scope.$state = $state;

        $scope.logout = function () {
            Auth.logout();
            $state.go('home');
        };

        $scope.collapse = function (id) {
            var myEl = angular.element(document.querySelector(id));
            myEl.toggleClass('in');
        }
    });
