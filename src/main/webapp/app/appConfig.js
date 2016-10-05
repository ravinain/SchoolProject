/**
 * 
 */
"use strict";

angular.module("app").config(function($provider){
	$provider.decorator("$exceptionHandler", ["$delegate", function($delegate){
		return function(exception, cause) {
			$delegate(exception, cause);
		};
	}]);
});