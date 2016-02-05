'use strict';

// Declare app level module which depends on views, and components
angular.module('app', [
    'ngRoute',
    'ngResource',
    'app.financial-modeling',
    'app.machine-learning'
]).config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when("/", {
        controller: 'fmCtrl',
        templateUrl: 'html'
    }).when("/ml", {
        controller: 'mlCtrl',
        templateUrl: 'machine-learning/main.html'
    }).when("/fm", {
        controller: 'fmCtrl',
        templateUrl: 'financial-modeling/main.html'
    }).otherwise({
        redirectTo: '/'
    });
}]).controller('navBarCtrl', function ($scope) {
    $scope.alreadyLogin = false;
   // $scope.bondMarketDataAll = [];
});