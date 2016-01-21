$(function () {
    'use strict';

    function HomePageController(booksService) {
        var vm = this;

        booksService
            .getLastBooks(10)
            .then(function(books) {
                vm.books = books;
                console.log(books);
            });
    }

    angular.module('librarySystem.controllers')
        .controller('HomePageController', ['booksService', HomePageController]);
}());
