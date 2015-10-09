'use strict';

angular.module('mainApp')
    .controller('MainController', function ($scope, Principal, $state) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });

        if(!Principal.isAuthenticated()){
            $state.go('login');
        }

        $scope.isAuthenticated = function(){
            return Principal.isAuthenticated();
        }
    });
