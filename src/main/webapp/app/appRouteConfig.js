/**
 * 
 */
"use strict";

angular.module("app").config(['$routeProvider', function($routeProvider){
	var routes = [{
		url: '/course/add',
		config: {
			templateUrl: 'ext-modules/scCourse/add-course.html',
			controller: 'scCourseController'
		}
	},{
		url: '/course/update',
		config: {
			templateUrl: 'ext-modules/scCourse/update-course.html',
			controller: 'scCourseController'
		}
	},{
		url: '/course/delete',
		config: {
			templateUrl: 'ext-modules/scCourse/delete-course.html',
			controller: 'scCourseController'
		}
	},{
		url: '/course/search',
		config: {
			templateUrl: 'ext-modules/scCourse/search-course.html',
			controller: 'scCourseController'
		}
	}, {
		url: '/role/add',
		config: {
			templateUrl: 'ext-modules/scRole/add-role.html',
			controller: 'scRoleController'
		}
	},{
		url: '/role/update',
		config: {
			templateUrl: 'ext-modules/scRole/update-role.html',
			controller: 'scRoleController'
		}
	},{
		url: '/role/delete',
		config: {
			templateUrl: 'ext-modules/scRole/delete-role.html',
			controller: 'scRoleController'
		}
	},{
		url: '/role/search',
		config: {
			templateUrl: 'ext-modules/scRole/search-role.html',
			controller: 'scRoleController'
		}
	}, {
		url: '/staff/add',
		config: {
			templateUrl: 'ext-modules/scStaff/add-staff.html',
			controller: 'scStaffController'
		}
	},{
		url: '/staff/update',
		config: {
			templateUrl: 'ext-modules/scStaff/update-staff.html',
			controller: 'scStaffController'
		}
	},{
		url: '/staff/delete',
		config: {
			templateUrl: 'ext-modules/scStaff/delete-staff.html',
			controller: 'scStaffController'
		}
	},{
		url: '/staff/search',
		config: {
			templateUrl: 'ext-modules/scStaff/search-staff.html',
			controller: 'scStaffController'
		}
	}, {
		url: '/student/add',
		config: {
			templateUrl: 'ext-modules/scStudent/add-student.html',
			controller: 'scStudentController'
		}
	},{
		url: '/student/update',
		config: {
			templateUrl: 'ext-modules/scStudent/update-student.html',
			controller: 'scStudentController'
		}
	},{
		url: '/student/delete',
		config: {
			templateUrl: 'ext-modules/scStudent/delete-student.html',
			controller: 'scStudentController'
		}
	},{
		url: '/student/search',
		config: {
			templateUrl: 'ext-modules/scStudent/search-student.html',
			controller: 'scStudentController'
		}
	}, {
		url: '/subject/add',
		config: {
			templateUrl: 'ext-modules/scSubject/add-subject.html',
			controller: 'scSubjectController'
		}
	},{
		url: '/subject/update',
		config: {
			templateUrl: 'ext-modules/scSubject/update-subject.html',
			controller: 'scSubjectController'
		}
	},{
		url: '/subject/delete',
		config: {
			templateUrl: 'ext-modules/scSubject/delete-subject.html',
			controller: 'scSubjectController'
		}
	},{
		url: '/subject/search',
		config: {
			templateUrl: 'ext-modules/scSubject/search-subject.html',
			controller: 'scSubjectController'
		}
	}];
	
	routes.forEach(function(route){
		$routeProvider.when(route.url, route.config);
	});
	
	$routeProvider.otherwise({templateUrl: 'pages/home.html'});
}]);