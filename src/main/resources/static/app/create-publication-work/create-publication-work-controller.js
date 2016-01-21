$(function () {
    'use strict';

    function CreatePublicationWorkController(publicationWorks) {
        var vm = this;

        function createPublicationWork(newPublicationWork) {
             publicationWorks.createPublicationWork(newPublicationWork);
        }

        vm.createPublicationWork = createPublicationWork;
    }

    angular.module('librarySystem.controllers')
        .controller('CreatePublicationWorkController',['publicationWorks', CreatePublicationWorkController]);
}());