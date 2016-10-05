/**
 * 
 */
"use strict";
var app = angular.module("scSubject");

var subjectService = function($http) {
	
	var getAllSubjects = function() {
		return $http.get("subjects.json");
	};
	
	var getSubject = function(subject) {
		if(!subject) {
			return getAllSubjects();
		}
		return $http.get("subject/"+subject+".json");
	};
	
	var addSubject = function(subjectObj) {
		subjectObj = subjectObj?subjectObj:{};
		return $http.post("subject/add.json", subjectObj);
	};
	
	var updateSubject = function(subjectObj) {
		subjectObj = subjectObj?subjectObj:{};
		return $http.put("subject/update/"+subjectObj.id+".json", subjectObj);
	};
	
	var deleteSubject = function(subjectId) {
		return $http.delete("subject/delete/"+subjectId+".json");
	};
	
	return {
		getAllSubjects: getAllSubjects,
		getSubject: getSubject,
		addSubject: addSubject,
		updateSubject: updateSubject,
		deleteSubject: deleteSubject
	};
};

app.factory("scSubjectService", subjectService);