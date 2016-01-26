'use strict';

angular.module('myApp.directives', [])
.directive('tabTemplate', function () {
    return {
        restrict: 'E',
        transclude: true,
        scope: {
            isShow: '=isShowSearch',
            style: '=tabWidth',
            offsetStyle: '=searchBoxOffset',
            enterHandler: '&enterHandler',
            registToggle:'=registToggleClick'
        },
        controller: function ($scope) {
            var panes = $scope.panes = [];
            $scope.select = function (pane) {
                if (pane.selected != true) {
                    angular.forEach(panes, function (pane) {
                        pane.selected = false;
                    });
                    pane.selected = true;
                    pane.selectedFunc();
                }
            };
            $scope.$on("toggleClick",function(event, index) {
                if ($scope.registToggle){
                    $scope.toggleSelect(index);
                }
            });
            $scope.toggleSelect = function(index) {
              if (index > 0 && index <= panes.length) {
                  var loc = 1;
                  angular.forEach(panes, function (pane) {
                      if (loc == index) {
                          pane.selected = true;
                          pane.selectedFunc();
                      } else {
                          pane.selected = false;
                      }
                      loc++;
                  });
              }
            };

            this.addPane = function (pane) {
                if (panes.length == 0) {
                    $scope.select(pane);
                }
                panes.push(pane);
            };
        },
        templateUrl: 'templates/tabDesign.html'
    };
}).directive('panelTemplate', function () {
    return {
        require: '^tabTemplate',
        restrict: 'E',
        transclude: true,
        scope: {
            name: '=tabName',
            selectedFunc: '&selectedFunc'
        },
        link: function (scope, element, attrs, tabsCtrl) {
            tabsCtrl.addPane(scope);
        },
        templateUrl: 'templates/panelDesign.html'
    };
}).directive('enterEvent', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function(event) {
            var keyCode = event.which || event.keyCode;
            if (keyCode === 13) {
                scope.$apply(function() {
                    // Evaluate the expression
                    scope.$eval(attrs.enterEvent);
                });
                event.preventDefault();
            }
        });
    }
});
