'use strict';

angular.module('app.financial-modeling', ['ngRoute'])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/fm', {
            templateUrl: 'financial-modeling/main.html',
            controller: 'fmCtrl'
        });
    }])
    .controller('fmCtrl', function($rootScope, $scope) {
        $rootScope.alreadyLogin = true;
        $scope.bondSelect = function () {
            if (bondMarketDataAll.length == 0) {
                bondMarketDataAll = bondMarket.getAllData({}, function success () {
                    $scope.$broadcast("bondPageSelect", false);
                });
            } else {
                $scope.$broadcast("bondPageSelect", true);
            }
        }
    })
    .controller('BinomialTreeCtrl', function($scope, primarySocket) {
        $scope.marketData = primaryMarketData;

        primarySocket.on('primary market event', function(data) {
            $.each(data, function(index, bond) {
                var find = false;
                $.each($scope.marketData, function(i, primaryData) {
                    if (primaryData.uid == bond.uid) {
                        $scope.marketData.splice(i, 1);
                        find = true;
                        return false;
                    }
                });
                if (!find) {
                    $scope.marketData.pop();
                }
                $scope.marketData.splice(0,0,bond);
            });
        });
    });
