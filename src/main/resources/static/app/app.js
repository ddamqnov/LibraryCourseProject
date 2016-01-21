$(function () {
    'use strict';

    var config = function config($routeProvider) {
        var CONTROLLER_VIEW_MODEL_NAME = 'vm',
            VIEWS_BASE_DIRECTORY = 'app/';


        $routeProvider
            .when('/', {
                templateUrl: VIEWS_BASE_DIRECTORY + 'home-page/home-page-view.html',
                controller: 'HomePageController',
                controllerAs: CONTROLLER_VIEW_MODEL_NAME,
            })
            .otherwise({ redirectTo: '/' });
    };

    angular.module('librarySystem.services', []);
    angular.module('librarySystem.directives', []);
    angular.module('librarySystem.controllers', ['librarySystem.services']);

    angular.module('librarySystem', ['ngRoute', 'librarySystem.controllers', 'librarySystem.services', 'librarySystem.directives', ])
        .config(['$routeProvider', config])
        .value('toastr', toastr)
        .constant('baseServiceUrl', 'http://localhost:8080');
}());