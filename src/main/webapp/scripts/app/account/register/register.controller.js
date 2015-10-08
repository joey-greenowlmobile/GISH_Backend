'use strict';

angular.module('mainApp')
    .controller('RegisterController', function ($scope, $translate, $log, $timeout, Auth, $http) {
        $scope.validateFlow = true; //1. Validate a phone number by sending SMS verification code
        $scope.verifyFlow = null;  //2. Check if the user can enter the correct verification code
        $scope.registerFlow = null; //3. Create a new user
        $scope.successMsg = null;
        $scope.errorMsg = null;
        $scope.doNotMatch = null;
        $scope.errorUserExists = null;
        $scope.registerAccount = {};
        $scope.registerAccount.langKey = "en";
        $scope.validLogin = true;

        $scope.userNameAvailable = function (name) {
            // Simple POST request example (passing data) :
            $http.get("api/validate/login/" + name).
                success(function (data, status, headers, config) {
                    // this callback will be called asynchronously
                    // when the response is available
                    $scope.validLogin = true;
                }).
                error(function (data, status, headers, config) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    $scope.validLogin = false;
                });
        };

        $scope.$watch('registerAccount.login', function (newValue) {
            if (newValue != undefined && newValue.length > 4) {
                $log.info("Checking availability of username...");
                $scope.userNameAvailable(newValue);
            }
        });

        //RegisterFlow - responsible for registering a new user after he/she has validated and verified their phone number
        $scope.checkUserAvailability = function (login) {

        };

        $timeout(function () {
            angular.element('[ng-model="registerAccount.login"]').focus();
        });


        $scope.register = function () {
            $scope.registerAccount.langKey = $translate.use(); //'en' by default
            $scope.doNotMatch = null;
            $scope.error = null;
            $scope.errorUserExists = null;
            $scope.errorEmailExists = null;
            console.log($scope.registerAccount);
            Auth.createAccount($scope.registerAccount).then(function () {
                $scope.errorMsg = null;
                $scope.successMsg = 'Created!';
            }).catch(function (response) {
                $scope.success = null;
                if (response.status === 400 && response.data === 'login already in use') {
                    $scope.successMsg = null;
                    $scope.errorMsg = 'Username already exists, please choose another.';
                }
                else if (response.status === 400 && response.data === 'phone number already registered') {
                    $scope.successMsg = null;
                    $scope.errorMsg = 'Phone number already registered to an account.';
                } else {
                    $scope.successMsg = null;
                    $scope.errorMsg = 'Unable to register you, uh oh!';
                }
            });
        };

    });
