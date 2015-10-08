'use strict';

angular.module('mainApp')
    .controller('SettingsController', function ($scope, Principal, Auth) {
        $scope.success = null;
        $scope.error = null;

        /**
         * Currently authenticated principal
         */
        Principal.identity().then(function(account) {
            $scope.settingsAccount = account;
        });

        /**
         * Update user information.
         */
        $scope.save = function () {
            Auth.updateAccount($scope.settingsAccount).then(function() {
                $scope.error = null;
                $scope.success = 'OK';
                Principal.identity().then(function(account) {
                    $scope.settingsAccount = account;
                });
            }).catch(function() {
                $scope.success = null;
                $scope.error = 'ERROR';
            });
        };
    });
