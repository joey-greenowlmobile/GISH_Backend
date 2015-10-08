'use strict';

angular.module('mainApp')
    .factory('AuthServerProvider', function loginService($http, localStorageService, Base64) {
        var _GOOGLE_OAUTH_URL = 'https://accounts.google.com/o/oauth2/auth' +
            '?redirect_uri=http://localhost:8080/api/social/authenticate' +
            '&response_type=code' +
            '&client_id=432631194634-goq3likfe03ltdq65l3ht5hmlukgl7ib.apps.googleusercontent.com' +
            '&scope=email';
        return {
            login: function(credentials) {
                var data = "username=" + credentials.username + "&password="
                    + credentials.password;
                return $http.post('api/authenticate', data, {
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                        "Accept": "application/json"
                    }
                }).success(function (response) {
                    localStorageService.set('token', response);
                    return response;
                });
            },
            socialLogin: function(credentials){
                var data = "credentials=" + credentials.token + "&platform=" + credentials.platform;
                return $http.post('api/authenticate/client', data, {
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                        "Accept": "application/json"
                    }
                }).success(function (response) {
                    localStorageService.set('token', response);
                    return response;
                });
            },
            logout: function() {
                //Stateless API : No server logout
                localStorageService.clearAll();
            },
            getToken: function () {
                return localStorageService.get('token');
            },
            hasValidToken: function () {
                var token = this.getToken();
                return token && token.expires && token.expires > new Date().getTime();
            }
        };
    });
