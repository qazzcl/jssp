'use strict';

angular.module('myApp.researcher', ['ngRoute'])
    .factory('search', function(){
        return {
            bondKey: ""
        }
    })
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/researcher', {
            templateUrl: 'researcher/researcher.html',
            controller: 'ViewCtrl'
        });
    }])
    .controller('ViewCtrl', function($rootScope, $scope, bondMarket) {
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
    .controller('primaryMarketCtrl', function($scope, primarySocket) {
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
    })
    .controller("bondTabCtrl", function($scope,search,quoteSocket) {
        $scope.$on("bondPageSelect", function(event,isAlready) {
            if (!isAlready) {
                $scope.marketData = bondMarketDataAll.slice(0,100);
                quoteSocket.on('best quote event', function(data) {
                    $.each(data, function(index, bond) {
                        var find = false;
                        $.each($scope.marketData, function(i, quoteData) {
                            if (quoteData.uid == bond.uid) {
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
            } else {

            }

        });

        $scope.$on("searchKeyChange", function() {
            $scope.searchKey = search.bondKey;
        });

        $scope.searchKey = "";
    });
