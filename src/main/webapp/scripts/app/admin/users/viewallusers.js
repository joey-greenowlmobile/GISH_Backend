'use strict';

angular.module('mainApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('users', {
                parent: 'admin',
                url: '/users',
                data: {
                    roles: ['ROLE_ADMIN'],
                    pageTitle: 'viewallusers.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/admin/users/viewallusers.html',
                        controller: 'ViewAllUsersController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('viewallusers');
                        return $translate.refresh();
                    }]
                }
            });
    });
