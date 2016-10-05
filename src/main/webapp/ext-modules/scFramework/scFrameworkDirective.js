/**
 * 
 */
"use strict";

angular.module("scFramework").directive("scFramework", function() {
	return {
		transclude: true,
		scope: {
			"scTitle": '@'
		},
		controller: "scFrameworkController",
		templateUrl: "ext-modules/scFramework/scFrameworkTemplate.html"
	};
});