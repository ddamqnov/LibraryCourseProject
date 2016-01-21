$(function () {
    'use strict';

    function HomePageController(books, magazines) {
        var vm = this;

        books
            .getLastBooks(10)
            .then(function(booksData) {
                vm.books = booksData;
            });

        magazines
            .getLastMagazines(10)
            .then(function(magazinesData) {
                vm.magazines = magazinesData;
            });
    }

    angular.module('librarySystem.controllers')
        .controller('HomePageController', ['books', 'magazines', HomePageController]);
}());
