/**
 * 
 */
angular
		.module('scCourse')
		.controller(
				'scCourseController',
				[
						'$scope',
						'$rootScope',
						'$location',
						'scCourseService',
						'scSubjectService',
						function($scope, $rootScope, $location, scCourseService, scSubjectService) {
							/*$rootScope.$broadcast("active-element-event", {activeElement:$location.path()});*/
							$scope.add = {};
							$scope.add.showMessage = false;
							$scope.add.successFlag = false;
							$scope.addCourse = function() {
								$scope.add.showMessage = false;
								var response = scCourseService
										.addCourse($scope.add.course);
								response
										.then(
												function(res) {
													$scope.add.successFlag = true;
													$scope.add.showMessage = true;
													$scope.add.message = [{"message":"Course has been added successfully!"}];
												},
												function(res) {
													$scope.add.successFlag = false;
													$scope.add.showMessage = true;
													$scope.add.message = res.data.messages;
												});
							};

							scSubjectService.getAllSubjects().then(function(res){
								$scope.subjects = res.data;
							}, function(res){});

 $scope.fillAllCoursesList = function(){
								scCourseService.getAllCourses().then(function(res) {
								$scope.courses = res.data;
							}, function(res) {
								});
 };

 $scope.fillAllCoursesList();
							
							$scope.search = {};
							$scope.search.course = {};
							$scope.search.course.header = [ "ID", "Name",
									"Subjects", "Update", "Delete" ];
							$scope.search.resultFlag = false;

							$scope.searchCourse = function() {
								if (!$scope.search.course.description) {
									$scope.search.course.description = {};
									$scope.search.course.description.description = '';
								}
								$scope.search.resultFlag = false;
								scCourseService
										.getCourse(
												$scope.search.course.description.description)
										.then(
												function(res) {
													if (res.data instanceof Array) {
														$scope.search.course.data = res.data;
													} else {
														$scope.search.course.data = [ res.data ];
													}
													$scope.search.resultFlag = true;
												}, function(res) {
												});
							};

							/**
							 * update-course.html page code
							 */
							if (!$scope.update) {
								$scope.update = {};
							}

							$scope.update = $scope.update ? $scope.update : {};
							$scope.update.search = $scope.update.search ? $scope.update.search
									: {};
							$scope.update.search.resultFlag = false;
							$scope.update.errorFlag = false;

							$scope.update.searchCourse = function() {
								$scope.update.search.resultFlag = false;
								if ($scope.update
										&& $scope.update.search
										&& $scope.update.search.course
										&& $scope.update.search.course.description
										&& $scope.update.search.course.description.description) {
									scCourseService
											.getCourse(
													$scope.update.search.course.description.description)
											.then(
													function(res) {
														if (res.data instanceof Array) {
															$scope.update.course = {};
														} else {
															$scope.update.course = res.data;
														}
														$scope.update.search.resultFlag = true;
													}, function(res) {
													});
								} else {
									alert('select course');
								}
							};

							$scope.update.resultFlag = false;
							$scope.updateCourse = function() {
								$scope.update.resultFlag = false;
								scCourseService.updateCourse($scope.update.course).then(
										function(res) {
											$scope.update.search.resultFlag = false;
											$scope.update.resultFlag = true;
											$scope.update.resultMessage = [{"message":"Course has updated successfully!"}];
											$scope.update.errorFlag = false;
											$scope.fillAllCoursesList();
										}, function(res) {
											$scope.update.resultFlag = true;
											$scope.update.resultMessage = res.data.messages;
											$scope.update.errorFlag = true;
										});
							};
							
							/**
							 * delete-course.html page code
							 */
							if (!$scope.delete) {
								$scope.delete = {};
							}

							$scope.delete = $scope.delete ? $scope.delete : {};
							$scope.delete.search = $scope.delete.search ? $scope.delete.search
									: {};
							$scope.delete.search.resultFlag = false;
							$scope.delete.errorFlag = false;

							$scope.delete.searchCourse = function() {
								$scope.delete.search.resultFlag = false;
								if ($scope.delete
										&& $scope.delete.search
										&& $scope.delete.search.course
										&& $scope.delete.search.course.description
										&& $scope.delete.search.course.description.description) {
									scCourseService
											.getCourse(
													$scope.delete.search.course.description.description)
											.then(
													function(res) {
														if (res.data instanceof Array) {
															$scope.delete.course = {};
														} else {
															$scope.delete.course = res.data;
														}
														$scope.delete.search.resultFlag = true;
													}, function(res) {
													});
								} else {
									alert('select course');
								}
							};

							$scope.delete.resultFlag = false;
							$scope.deleteCourse = function() {
								$scope.delete.resultFlag = false;
								scCourseService.deleteCourse($scope.delete.course.id).then(
										function(res) {
											$scope.delete.search.resultFlag = false;
											$scope.delete.resultFlag = true;
											$scope.delete.resultMessage = [{"message":"Course has deleted successfully!"}];
											$scope.delete.errorFlag = false;
											$scope.fillAllCoursesList();
										}, function(res) {
											$scope.delete.resultFlag = true;
											$scope.delete.resultMessage = res.data.messages;
											$scope.delete.errorFlag = true;
										});
							};


						} ]);