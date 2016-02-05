'use strict';

angular.module('app.machine-learning.bayes', ['ngRoute', 'ngModal'])
    .controller('bayesCtrl', function ($scope, $routeParams) {


        $scope.roles = {trader: '001', fundManager: '003'};
        $scope.bidRatio = 0.0

        $scope.bondKeyListedMarket = $routeParams.bondKeyListedMarket;
        $scope.user = $routeParams.user;
        $scope.role = $routeParams.role;
        $scope.fundManagers = [{code: 'all', name: '不限'}]

        var init = function () {
            $scope.underwriters = [];


            $scope.bond = primaryMarketBond.get({
                bond: $scope.bondKeyListedMarket,
                user: $scope.user,
                role: $scope.role
            }, function success(response) {
                $scope.underwriters = $scope.bond.underwriters;
                $scope.underwriters.forEach(function (underwriter) {
                    if (underwriter.contacts.length > 0) {
                        underwriter.selectedContact = underwriter.contacts[0];
                    }
                });
                $scope.underwriterIds = $scope.underwriters.map(function (underwriter, index) {
                    return underwriter.id;
                });

                $scope.orders = $scope.bond.orders;

                $scope.accounts = unique($scope.orders.map(function (order) {
                    return order.account;
                }));

                $scope.fundManagers = $scope.bond.orderCreators;

                $scope.fundManagers.unshift({code: 'all', name: '不限'});

                if ($scope.bond.tenorYear > 1.0) {
                    $scope.exTypes.length = 1;
                }

                $scope.bidRecords = $scope.bond.bidRecords;

                if ($scope.bidRecords.totals.length == 0 || !$scope.bond.issueAmount) {
                    $scope.bidRatio = 0.0;
                } else {
                    $scope.bidRatio = $scope.bidRecords.totals[0] / $scope.bond.issueAmount * 100;
                }
                $scope.bidRatio = $scope.bidRatio.toFixed(1);

                $scope.clearBids();
            });
        };

        init();

        $scope.selectedUnderwriter = null;

        $scope.onClickBid = function (index) {
            $scope.selectedUnderwriter = $scope.underwriters[index];
        };

        $scope.onClickBatchBid = function () {
        };

        $scope.fake = function () {
        };

        $scope.bids = [];

        var Bid = function () {
            var bid = {
                createdBy: $scope.user,
                price: '',
                exType: $scope.exTypes[0].value,
                returnType: 0,
                transferType: $scope.transferTypes[0].value,
                vols: [],
            };
            bid.vols.length = $scope.underwriters.length;
            return bid;
        };

        $scope.onSelectFundManager = function (fundManager) {
            $scope.initBidTable(fundManager);
        };

        $scope.onSubmitBid = function () {

            $scope.bids = $scope.bids.filter(function (bid) {
                return !(bid.price == '' || bid.price == null);
            });

            $scope.bids.forEach(function (bid) {
                var vols = [];
                var volume = 0;
                bid.vols.forEach(function (vol, i) {
                    if (vol) {
                        vols.push({
                            underwriter: $scope.underwriterIds[i],
                            vol: vol
                        });
                        volume += vol;
                    }
                });
                bid.vols = vols;
                bid.volume = volume;
            });

            primaryMarketBid.sendRequest({
                request: {
                    bondKeyListedMarket: $scope.bondKeyListedMarket,
                    bids: $scope.bids,
                    user: $scope.user,
                    role: $scope.role,
                    unit: $scope.unit
                }
            }, function success(response) {
                init();
            });
        };

        $scope.onSubmitBatchBid = function () {
            $scope.onSubmitBid();
        };

        $scope.onHideDialog = function () {
        };

        $scope.clearBids = function () {//TODO remane as resetBids
            $scope.bids = [];
            $scope.initBidTable()
        };

        $scope.clearBids1 = function () { //TODO
            $scope.bids = [];
            $scope.add5bids();
        };

        $scope.sum = function (var1, var2) {
            var i = (var1 == '') ? '' : parseInt(var1)
            var j = (var2 == '') ? '' : parseInt(var2)

            return (isNaN(i) || isNaN(j)) ? NaN
                : (i == '') ? ((j == '') ? '' : j)
                : ((j == '') ? i : i + j);
        };

        $scope.initBidTable = function (fundManager) {

            $scope.bids = [];
            if ($scope.orders) {
                for (var i = 0; i < $scope.orders.length; ++i) {
                    var order = $scope.orders[i];
                    if ($scope.role == $scope.roles.trader && fundManager && fundManager != 'all' && order.createdBy != fundManager) {
                        continue;
                    }
                    $scope.bids.push({
                            createdBy: order.createdBy,
                            price: order.price,
                            exType: order.exType,
                            returnType: order.returnType,
                            transferType: order.transferType,
                            vols: order.vols
                        }
                    )
                }
            }


            var n = 5 - $scope.bids.length;

            for (var i = 0; i < n; i++) {
                $scope.bids.push(Bid());
            }

        };

        $scope.add5bids = function (bids) {
            for (var i = 0; i < 5; i++) {
                $scope.bids.push(Bid());
            }
        };


        $scope.units = [{label: '收益率(%)', value: 1}, {label: '价格', value: 2}];
        $scope.unit = $scope.units[0].value
        $scope.exTypes = [{text: '票面', value: 0}, {text: '综收', value: 1}];
        $scope.transferTypes = [{text: '上市交易', value: 0}, {text: '缴款', value: 1}];
        $scope.transferType = $scope.transferTypes[0].value;
        $scope.accounts = [];
        $scope.account = null;
        $scope.clearBids()
    })
    .controller('primaryRecommendCtrl', function ($scope, primaryMarketRecommend) {

        $scope.recommendedByList = [
            {label: '相同行业', value: 'bySector'},
            {label: '相同发行人', value: 'byIssuer'},
            {label: '相似期限', value: 'byTenor'}
        ];

        $scope.recommendedBy = null;

        $scope.getRecommends = function () {
            $scope.allRecommended = primaryMarketRecommend.get({
                bond: $scope.bondKeyListedMarket
            }, function success(response) {
                $scope.recommendedBy = $scope.recommendedByList[0].value;
            })
        };

        $scope.getRecommends();
    });