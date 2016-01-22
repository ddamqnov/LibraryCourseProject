$(function () {
    function ratings(data) {
        function ratePublicationWork(publicationWorkId, value) {
            return data.post('rating', { publicationWorkId: publicationWorkId, value: value });
        }

        return {
            ratePublicationWork: ratePublicationWork
        };
    }

    angular.module('librarySystem.services')
        .factory('ratings', ['data', ratings]);
}());