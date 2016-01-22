$(function () {
    'use strict';

    function magazines(data) {
        function getLastMagazines(count) {
            return data.get('magazine/last', { count: count });
        }

        return {
            getLastMagazines: getLastMagazines
        };
    }

    angular.module('librarySystem.services')
        .factory('magazines', ['data', magazines]);
}());