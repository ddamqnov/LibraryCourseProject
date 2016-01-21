$(function () {
    function publicationWorks(data) {
        var PUBLICATION_WORKS_URL = 'publicationwork';

        function createPublicationWork(newPublicationWork) {
            return data.post(PUBLICATION_WORKS_URL, newPublicationWork);
        }

        function updatePublicationWork(publicationWork) {
            return data.put(PUBLICATION_WORKS_URL, publicationWork);
        }

        return {
            createPublicationWork: createPublicationWork,
            updatePublicationWork: updatePublicationWork
        };
    }

    angular.module('librarySystem.services')
        .factory('publicationWorks', ['data', publicationWorks]);
}());