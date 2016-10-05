/**
 * 
 */
"use strict";
var app = angular.module('scCourse');

var courseService = function($http) {
	
	var getAllCourses = function() {
		return $http.get("courses.json");
	};
	
	var getCourse = function(courseName) {
		if(courseName === '') {
			return getAllCourses();
		}
		return $http.get("course/"+courseName+".json");
	};
	
	var addCourse = function(courseObj) {
		courseObj = courseObj?courseObj:{};
		return $http.post("course/add.json", courseObj);
	};
	
	var updateCourse = function(courseObj) {
		courseObj = courseObj?courseObj:{};
		return $http.put("course/update/"+courseObj.id+".json", courseObj);
	};
	
	var deleteCourse = function(courseId) {
		return $http.delete("course/delete/"+courseId+".json");
	};
	
	return {
		getAllCourses: getAllCourses,
		getCourse: getCourse,
		addCourse: addCourse,
		updateCourse: updateCourse,
		deleteCourse: deleteCourse
	};
};

app.factory("scCourseService", courseService);