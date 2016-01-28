# issue_tracker_lab
I am using Wildfly 9.0.1 Final with Hibernate 4.3.10.Final, 
IntelliJ IDEA 15.0.3, MySQL Workbench 6.3.4.0 build 828 Community Edition,
Apache Maven 3.3.3 and Java JDK 1.8.0_51.

Here is how my IntelliJ IDEA project is setup

issue_tracker>

      				>src	 
      					>main 	
      						>java 			
      							>nl 	
      								>rivium 	
      									>dao
      										-AssigneeDAO.java
      										-AssigneeDAOImpl.java
      										-CategoryDAO.java
      										-CategoryDAOImpl.java
      										-IssueDAO.java
      										-IssueDAOImpl.java
      										-PriorityDAO.java
      										-PriorityDAOImpl.java
      										-RolesDAO.java
      										-RolesDAOImpl.java
      										-StatusDAO.java
      										-StatusDAOImpl.java
      										-UserDAO.java
      										-UserDAOImpl.java										
      									>entities
      										-Assignee.java
      										-Category.java
      										-Issue.java
      										-Priority.java
      										-Roles.java
      										-Status.java
      										-User.java
      									>resources
      										-AssigneeResource.java
      										-CategoryResource.java
      										-IssueApplication.java
      										-IssueResource.java
      										-PriorityResource.java
      										-StatusResource.java
      										-UserResource.java
      								>resources
      									>META-INF
      										-persistence.xml
      									log4j.properties
      								>webapp
      									>private
      										>controllers
      											-DatabaseCtrl.js
      											-HomeCtrl.js
      											-IssueCtrl.js
      											-LogoutCtrl.js
      										>modules
      											-HomeMod.js
      											-IssueMod.js
      											-LogoutMod.js
      										>services
      											-DatabaseFactory.js
      											-ValidationFactory.js
      									>public
      										>controllers
      											-LoginCtrl.js
      											-SignupCtrl.js
      										>modules
      											-LoginMod.js
      											-SignupMod.js
      									>vendor
      										>styles
      											-angular-toastr.css
      											-bootstrap.min.css
      											-foundation.css
      											-xeditable.css
      										-angular-animate.js
      										-angular-cookies.js
      										-angular-route.min.js
      										-angular-toastr.tpls.js
      										-angular_1.4.6.js
      										-bootstrap.min.js
      										-foundation.js
      										-jquery.js
      										-ngStorage.js
      										-xeditable.js
      									>views
      										>private
      											-home.html
      											-issue.html
      											-newissue.html
      										>public
      											-login.html
      											-signup.html
      										>styles
      											-custom.css
      									>WEB-INF
      										-web.xml
      									-app.js
      									-index.html
			-pom.xml

Downloads

	1	Wildfly 9.0.1 final 									- http://download.jboss.org/wildfly/9.0.1.Final/wildfly-9.0.1.Final.zip
	2	IntelliJ IDEA 15.0.3									- https://www.jetbrains.com/idea/download/#section=windows
	3  	MySQL Workbench 6.3.4.0 build 828 Community Edition		- http://dev.mysql.com/downloads/installer/
	4 	Apache Maven 3.3.3										- http://olex.openlogic.com/package_versions/25255/download?package_version_id=103122&path=https%3A%2F%2Folex-secure.openlogic.com%2Fcontent%2Fprivate%2F5e6a6f0815e830bba705e79e4a0470fbee8a5880%2F%2Folex-secure.openlogic.com%2Fapache-maven-3.3.3-src.zip
	5	Java JDK 1.8.0_51										- http://www.oracle.com/technetwork/java/javase/downloads/java-archive-javase8-2177648.html#jdk-8u51-oth-JPR
