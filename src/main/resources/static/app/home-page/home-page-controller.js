$(function () {
    'use strict';

    var HomePageController = function HomePageController() {
        var vm = this;

        vm.test = 'test';
    };

    angular.module('librarySystem.controllers')
        .controller('HomePageController', [HomePageController]);
}());
