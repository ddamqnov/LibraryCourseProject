$(function () {
    function publicationWorks(data) {
        var PUBLICATION_WORKS_URL = 'publicationwork';

        function createPublicationWork(newPublicationWork) {
            return data.post(PUBLICATION_WORKS_URL, newPublicationWork);
        }

        function updatePublicationWork(publicationWork) {
            return data.put(PUBLICATION_WORKS_URL, publicationWork);
        }

        function getPublicationWork(id) {
            return data.get(PUBLICATION_WORKS_URL, { id: id });
        }

        return {
            createPublicationWork: createPublicationWork,
            updatePublicationWork: updatePublicationWork,
            getPublicationWork: getPublicationWork
        };
    }

    angular.module('librarySystem.services')
        .factory('publicationWorks', ['data', publicationWorks]);
}());