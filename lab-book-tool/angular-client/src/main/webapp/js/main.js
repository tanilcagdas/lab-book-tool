/**
 * AngularJS Tutorial 1
 * @author Nick Kaye <nick.c.kaye@gmail.com>
 */

/**
 * Main AngularJS Web Application
 */
var app = angular.module('tutorialWebApp', [
  'ngRoute'
]);

/**
 * Configure the Routes
 */
app.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    // Home
    .when("/", {templateUrl: "partials/devices.html", controller: "PageCtrl"})
    // Pages
    .when("/devices", {templateUrl: "partials/devices.html", controller: "PageCtrl"})
    .when("/laptops", {templateUrl: "partials/laptops.html", controller: "PageCtrl"})
 
    // Blog

    // else 404
    .otherwise("/404", {templateUrl: "partials/404.html", controller: "PageCtrl"});
}]);

/**
 * Controls the Blog
 */
app.controller('BlogCtrl', function (/* $scope, $location, $http */) {
  console.log("Blog Controller reporting for duty.");
});

/**
 * Controls all other Pages
 */
app.controller('PageCtrl', function (/* $scope, $location, $http */) {
  console.log("Page Controller reporting for duty.");

  // Activates the Carousel
  $('.carousel').carousel({
    interval: 5000
  });

  // Activates Tooltips for Social Links
  $('.tooltip-social').tooltip({
    selector: "a[data-toggle=tooltip]"
  })
});


app.controller('devicesCtrl', function($scope, $http) {
	var req =
	{ 
			method: 'GET',
			url: 'http://localhost:8080/rest/webresources/labBook/device',
			headers: {
			   'Content-Type': undefined,
			   'username' : 'admin'
			 },
			 data: { test: 'test' }
			}
	$http(req).then(function(response) {
		$scope.devices = response.data;
		console.log($scope.devices)
		
	}, function errorCallback(response) {
		alert();
	    // called asynchronously if an error occurs
	    // or server returns response with an error status.
	  });
	
	var reserveReq =
	{ 
			method: 'GET',
			url: 'http://localhost:8080/rest/webresources/labBook/reservedevice?name=SR',
			headers: {
			   'Content-Type': undefined,
			   'username' : 'admin'
			 },
			 data: { test: 'test' }
			}

	
	 $scope.reserve = function() {
		 $http(reserveReq).then(function(response) {
				$scope.devices = response.data;
				console.log($scope.devices)
				
			}, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
				alert();
			  });
	    };

	    var releaseReq =
	    { 
	    		method: 'GET',
	    		url: 'http://localhost:8080/rest/webresources/labBook/releasedevice?name=SR',
	    		headers: {
	    			'Content-Type': undefined,
	    			'username' : 'admin'
	    		},
	    		data: { test: 'test' }
	    }
	    
	    
	    $scope.release = function() {
	    	$http(releaseReq).then(function(response) {
	    		$scope.devices = response.data;
	    		console.log($scope.devices)
	    		
	    	}, function errorCallback(response) {
	    		// called asynchronously if an error occurs
	    		// or server returns response with an error status.
	    		alert();
	    	});
	    };
	    
});

app.controller('laptopCtrl', function($scope, $http) {
	var req =
	{ 
			method: 'GET',
			url: 'http://localhost:8080/rest/webresources/labBook/laptop',
			headers: {
			   'Content-Type': undefined,
			   'username' : 'admin'
			 },
			 data: { test: 'test' }
			}
//	$http.get("http://localhost:8080/rest/webresources/labBook/device").
	$http(req).then(function(response) {
		$scope.laptops = response.data;
		console.log($scope.laptops)
		
	}, function errorCallback(response) {
	    // called asynchronously if an error occurs
	    // or server returns response with an error status.
	  });
});