$(function () {
    'use strict';

    function HomePageController(books) {
        var vm = this;

        books
            .getLastBooks(10)
            .then(function(booksData) {
                vm.books = booksData;
            });
    }

    angular.module('librarySystem.controllers')
        .controller('HomePageController', ['books', HomePageController]);
}());
