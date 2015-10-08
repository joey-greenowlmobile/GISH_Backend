'use strict';

angular.module('mainApp')
    .factory('AdminService', function ($rootScope, $http) {
        return {
            findAll: function () {
                return $http.get('api/users/profiles/public').then(function (response) {
                    return response.data;
                });
            },
            deleteUser: function (login) {
                var promise = $http.delete('api/account/' + login).then(function (response) {
                });
                return promise;
            }

        };
    });
