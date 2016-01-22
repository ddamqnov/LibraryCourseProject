$(function () {
    'use strict';

    function PublicationWorkDetailsController(publicationWorks, ratings, $routeParams, $location) {
        var vm = this;

        vm.POSSIBLE_RATINGS = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

        var publicationWorkId = $routeParams.id;

        function ratePublicationWork(ratingValue) {
            var publicationWorkId = vm.publicationWork.id;

            ratings
                .ratePublicationWork(publicationWorkId, ratingValue)
                .then(function (newAverageRating) {
                    vm.publicationWork.rating = newAverageRating;
                    vm.publicationWork.hasBeenRatedByIp = true;
                });
        }

        publicationWorks
            .getPublicationWork(publicationWorkId)
            .then(
                function(publicationWork) {
                    vm.publicationWork = publicationWork;
                    vm.ratePublicationWork = ratePublicationWork;
                }, function (error) {
                    $location.path('/');
                });
    }

    angular.module('librarySystem.controllers')
        .controller('PublicationWorkDetailsController',['publicationWorks', 'ratings', '$routeParams', '$location', PublicationWorkDetailsController]);
}());