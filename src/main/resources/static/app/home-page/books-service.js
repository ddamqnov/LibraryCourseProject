$(function () {
    'use strict';

    function booksService($q, data) {
        function getLastBooks(count) {
            return data.get('book/last', { page: 1, limit: count });
        }

        return {
            getLastBooks: getLastBooks
        };
    }

    angular.module('librarySystem.services')
        .factory('booksService', ['$q', 'data', booksService]);
}());