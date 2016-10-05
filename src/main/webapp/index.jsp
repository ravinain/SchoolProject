<!DOCTYPE html>
<html data-ng-app="app">
<head>

<link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" />
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet"/>
<link href="app/app.css" rel="stylesheet" />
<link href= "ext-modules/scMenu/scMenu.css" rel="stylesheet" />
<link href= "ext-modules/scCourse/scCourse.css" rel="stylesheet" />
<link href= "ext-modules/scRole/scRole.css" rel="stylesheet" />
<link href= "ext-modules/scSubject/scSubject.css" rel="stylesheet" />
<link href= "ext-modules/scStaff/scStaff.css" rel="stylesheet" />
<link href= "ext-modules/scStudent/scStudent.css" rel="stylesheet" />

<link href="ext-modules/scFramework/scFramework.css" rel="stylesheet" />

<script src="scripts/ext_lib/jquery-3.1.0.min.js"></script>
<script src="scripts/ext_lib/angular.min.js"></script>
<script src="scripts/ext_lib/angular-animate.js"></script>
<script src="scripts/ext_lib/angular-route.js"></script>

<script src="ext-modules/scMenu/scMenuModule.js"></script>
<script src="ext-modules/scMenu/scMenuController.js"></script>
<script src="ext-modules/scMenu/scMenuDirective.js"></script>
<script src="ext-modules/scMenu/scMenuItemDirective.js"></script>
<script src="ext-modules/scMenu/scMenuGroupDirective.js"></script>

<script src="ext-modules/scDashboard/scDashboardModule.js"></script>

<script src="ext-modules/scFramework/scFrameworkModule.js"></script>
<script src="ext-modules/scFramework/scFrameworkController.js"></script>
<script src="ext-modules/scFramework/scFrameworkDirective.js"></script>

<!-- Course JSs -->
<script src="ext-modules/scCourse/scCourseModule.js"></script>
<script src="ext-modules/scCourse/scCourseController.js"></script>
<script src="ext-modules/scCourse/scCourseService.js"></script>

<!-- Role JSs -->
<script src="ext-modules/scRole/scRoleModule.js"></script>
<script src="ext-modules/scRole/scRoleController.js"></script>
<script src="ext-modules/scRole/scRoleService.js"></script>

<!-- Subject JSs -->
<script src="ext-modules/scSubject/scSubjectModule.js"></script>
<script src="ext-modules/scSubject/scSubjectController.js"></script>
<script src="ext-modules/scSubject/scSubjectService.js"></script>

<!-- Staff JSs -->
<script src="ext-modules/scStaff/scStaffModule.js"></script>
<script src="ext-modules/scStaff/scStaffController.js"></script>
<script src="ext-modules/scStaff/scStaffService.js"></script>

<!-- Student JSs -->
<script src="ext-modules/scStudent/scStudentModule.js"></script>
<script src="ext-modules/scStudent/scStudentController.js"></script>
<script src="ext-modules/scStudent/scStudentService.js"></script>

<script src="app/appModule.js"></script>
<!-- <script src="app/appConfig.js"></script> -->
<script src="app/appRouteConfig.js"></script>
</head>
<body>
	<sc-framework sc-title="ABC Public School">
		<sc-menu>
			<sc-menu-group label="Course" icon="fa fa-graduation-cap">
				<sc-menu-item label="Search" icon="glyphicon glyphicon-search" route="course/search"></sc-menu-item>
				<sc-menu-item label="Add" icon="glyphicon glyphicon-plus-sign" route="course/add"></sc-menu-item>
				<sc-menu-item label="Update" icon="glyphicon glyphicon-pencil" route="course/update"></sc-menu-item>
				<sc-menu-item label="Delete" icon="glyphicon glyphicon-trash" route="course/delete"></sc-menu-item>				
			</sc-menu-group>
			<sc-menu-group label="Student" icon="fa fa-group">
				<sc-menu-item label="Search" icon="glyphicon glyphicon-search" route="student/search"></sc-menu-item>
				<sc-menu-item label="Add" icon="glyphicon glyphicon-plus-sign" route="student/add"></sc-menu-item>
				<sc-menu-item label="Update" icon="glyphicon glyphicon-pencil" route="student/update"></sc-menu-item>
				<sc-menu-item label="Delete" icon="glyphicon glyphicon-trash" route="student/delete"></sc-menu-item>
			</sc-menu-group>
			<sc-menu-group label="Staff" icon="fa fa-group">
				<sc-menu-item label="Search" icon="glyphicon glyphicon-search" route="staff/search"></sc-menu-item>
				<sc-menu-item label="Add" icon="glyphicon glyphicon-plus-sign" route="staff/add"></sc-menu-item>
				<sc-menu-item label="Update" icon="glyphicon glyphicon-pencil" route="staff/update"></sc-menu-item>
				<sc-menu-item label="Delete" icon="glyphicon glyphicon-trash" route="staff/delete"></sc-menu-item>
			</sc-menu-group>
			<sc-menu-group label="Subject" icon="fa fa-book">
				<sc-menu-item label="Search" icon="glyphicon glyphicon-search" route="subject/search"></sc-menu-item>
				<sc-menu-item label="Add" icon="glyphicon glyphicon-plus-sign" route="subject/add"></sc-menu-item>
				<sc-menu-item label="Update" icon="glyphicon glyphicon-pencil" route="subject/update"></sc-menu-item>
				<sc-menu-item label="Delete" icon="glyphicon glyphicon-trash" route="subject/delete"></sc-menu-item>
			</sc-menu-group>
			<sc-menu-group label="Role" icon="fa fa-user">
				<sc-menu-item label="Search" icon="glyphicon glyphicon-search" route="role/search"></sc-menu-item>
				<sc-menu-item label="Add" icon="glyphicon glyphicon-plus-sign" route="role/add"></sc-menu-item>
				<sc-menu-item label="Update" icon="glyphicon glyphicon-pencil" route="role/update"></sc-menu-item>
				<sc-menu-item label="Delete" icon="glyphicon glyphicon-trash" route="role/delete"></sc-menu-item>
			</sc-menu-group>
		</sc-menu>
	</sc-framework>
</body>
</html>