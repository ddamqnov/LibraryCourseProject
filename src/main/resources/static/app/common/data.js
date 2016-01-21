(function () {
    'use strict';

    function data($http, $q, notifier, baseServiceUrl) {
        function get(url, queryParams) {
            var defered = $q.defer();

            var authHeader = authorization.getAuthorizationHeader();

            $http.get(baseServiceUrl + '/' + url, { params: queryParams })
                .then(function (response) {
                    defered.resolve(response.data);
                }, function (error) {
                    notifier.error(error);
                    defered.reject(error);
                });

            return defered.promise;
        }

        function post(url, postData) {
            var defered = $q.defer();

            $http.post(baseServiceUrl + '/' + url, postData)
                .then(function (response) {
                    defered.resolve(response.data);
                }, function (error) {
                    notifier.error(error);
                    defered.reject(error);
                });

            return defered.promise;
        }

        return {
            get: get,
            post: post
        };
    }

    angular.module('librarySystem.services')
        .factory('data', ['$http', '$q', 'notifier', 'baseServiceUrl', data]);
}());