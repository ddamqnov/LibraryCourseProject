$(function () {
    'use strict';

    function booksService(data) {
        function getLastBooks(count) {
            return data.get('book/last', { page: 1, size: count, sort: 'id,desc' });
        }

        return {
            getLastBooks: getLastBooks
        };
    }

    angular.module('librarySystem.services')
        .factory('books', ['data', booksService]);
}());