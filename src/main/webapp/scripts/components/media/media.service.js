'use strict';

angular.module('mainApp')
    .factory('MediaService', function () {
        return {
            findAllProfilePictures: function () {
                //Async execution
                var promise = $http.get('api/media/profile/pictures').then(function (response) {
                    // The then function here is an opportunity to modify the response
                    return response.data;
                });
                return promise;
            }
        };
    });
