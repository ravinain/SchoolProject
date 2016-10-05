/**
 * 
 */
angular
		.module('scSubject')
		.controller(
				'scSubjectController',
				[
						'$scope',
						'scSubjectService',
						function($scope, scSubjectService) {
							$scope.add = {};
							$scope.add.showMessage = false;
							$scope.add.successFlag = false;
							$scope.addSubject = function() {
								$scope.add.showMessage = false;
								var response = scSubjectService
										.addSubject($scope.add.subject);
								response
										.then(
												function(res) {
													$scope.add.successFlag = true;
													$scope.add.showMessage = true;
													$scope.add.message =[{"message":"Subject has been added successfully!"}];
												},
												function(res) {
													$scope.add.successFlag = false;
													$scope.add.showMessage = true;
													$scope.add.message = res.data.messages;
												});
							};

							 $scope.fillAllSubjectsList = function(){
															scSubjectService.getAllSubjects().then(function(res) {
															$scope.subjects = res.data;
														}, function(res) {
															});
							 };
							
							 $scope.fillAllSubjectsList();
							
							$scope.search = {};
							$scope.search.subject = {};
							$scope.search.subject.header = [ "ID", "Name", "Update", "Delete" ];
							$scope.search.resultFlag = false;

							$scope.searchSubject = function() {
								if (!$scope.search.subject.description) {
									$scope.search.subject.description = {};
									$scope.search.subject.description.id = '';
								}
								$scope.search.resultFlag = false;
								scSubjectService
										.getSubject(
												$scope.search.subject.description.id)
										.then(
												function(res) {
													if (res.data instanceof Array) {
														$scope.search.subject.data = res.data;
													} else {
														$scope.search.subject.data = [ res.data ];
													}
													$scope.search.resultFlag = true;
												}, function(res) {
												});
							};

							/**
							 * update-subject.html page code
							 */
							if (!$scope.update) {
								$scope.update = {};
							}

							$scope.update = $scope.update ? $scope.update : {};
							$scope.update.search = $scope.update.search ? $scope.update.search
									: {};
							$scope.update.search.resultFlag = false;
							$scope.update.errorFlag = false;

							$scope.update.searchSubject = function() {
								$scope.update.search.resultFlag = false;
								if ($scope.update
										&& $scope.update.search
										&& $scope.update.search.subject
										&& $scope.update.search.subject.description
										&& $scope.update.search.subject.description.id) {
									scSubjectService
											.getSubject(
													$scope.update.search.subject.description.id)
											.then(
													function(res) {
														if (res.data instanceof Array) {
															$scope.update.subject = {};
														} else {
															$scope.update.subject = res.data;
														}
														$scope.update.search.resultFlag = true;
													}, function(res) {
													});
								} else {
									alert('select subject');
								}
							};

							$scope.update.resultFlag = false;
							$scope.updateSubject = function() {
								$scope.update.resultFlag = false;
								scSubjectService.updateSubject($scope.update.subject).then(
										function(res) {
											$scope.update.search.resultFlag = false;
											$scope.update.resultFlag = true;
											$scope.update.resultMessage = [{"message":"Subject has updated successfully!"}];
											$scope.update.errorFlag = false;
											$scope.fillAllSubjectsList();
										}, function(res) {
											$scope.update.resultFlag = true;
											$scope.update.resultMessage = res.data.messages;
											$scope.update.errorFlag = true;
										});
							};
							
							/**
							 * delete-subject.html page code
							 */
							if (!$scope.delete) {
								$scope.delete = {};
							}

							$scope.delete = $scope.delete ? $scope.delete : {};
							$scope.delete.search = $scope.delete.search ? $scope.delete.search
									: {};
							$scope.delete.search.resultFlag = false;
							$scope.delete.errorFlag = false;

							$scope.delete.searchSubject = function() {
								$scope.delete.search.resultFlag = false;
								if ($scope.delete
										&& $scope.delete.search
										&& $scope.delete.search.subject
										&& $scope.delete.search.subject.description
										&& $scope.delete.search.subject.description.id) {
									scSubjectService
											.getSubject(
													$scope.delete.search.subject.description.id)
											.then(
													function(res) {
														if (res.data instanceof Array) {
															$scope.delete.subject = {};
														} else {
															$scope.delete.subject = res.data;
														}
														$scope.delete.search.resultFlag = true;
													}, function(res) {
													});
								} else {
									alert('select subject');
								}
							};

							$scope.delete.resultFlag = false;
							$scope.deleteSubject = function() {
								$scope.delete.resultFlag = false;
								scSubjectService.deleteSubject($scope.delete.subject.id).then(
										function(res) {
											$scope.delete.search.resultFlag = false;
											$scope.delete.resultFlag = true;
											$scope.delete.resultMessage = [{"message":"Subject has deleted successfully!"}];
											$scope.delete.errorFlag = false;
											$scope.fillAllSubjectsList();
										}, function(res) {
											$scope.delete.resultFlag = true;
											$scope.delete.resultMessage = res.data.messages;
											$scope.delete.errorFlag = true;
										});
							};


						} ]);