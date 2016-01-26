'use strict';

angular.module('myApp.services', ['ngResource'])
    .factory('dataDetail', function($resource) {
        return $resource('http://127.0.0.1:5000/something', {}, {
            getDetail:{method:'GET', params:{}, isArray:false}
        });
    })
    .factory('theSocket', function ($rootScope) {
        var socket = io.connect('http://127.0.0.1:5001/something');
        return {
            on: function (eventName, callback) {
                socket.on(eventName, function () {
                    var args = arguments;
                    $rootScope.$apply(function () {
                        callback.apply(socket, args);
                    })
                });
            }
        }
    })
    .value('version', '0.1');