$(function () {
    'use strict';

    function booksService(data) {
        function getLastBooks(count) {
            return data.get('book/last', { count: count });
        }

        return {
            getLastBooks: getLastBooks
        };
    }

    angular.module('librarySystem.services')
        .factory('books', ['data', booksService]);
}());