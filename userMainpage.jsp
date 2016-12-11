<%@ page session="true" %>
<!DOCTYPE HTML>
<html>
<head>
<title>userMainpage</title>
	<link href="static/css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
	<script src="static/js/jquery-1.11.0.min.js"></script>
	<link href="static/css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="keywords" content="Treks Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
	Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<script src="static/js/menu_jquery.js"></script>
	<link href="static/assets/css/bootstrap.css" rel="stylesheet" />
	<link href="static/assets/css/font-awesome.css" rel="stylesheet" />
	<link href="static/assets/css/basic.css" rel="stylesheet" />
	<link href="static/assets/css/custom.css" rel="stylesheet" />
	<link href='http://fonts.useso.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
	<script src="static/assets/js/jquery-1.10.2.js"></script>
	<script src="static/assets/js/bootstrap.js"></script>
	<script src="static/assets/js/jquery.metisMenu.js"></script>
	<script src="static/assets/js/custom.js"></script>
	<link href='static/css/bootstrap.min.css' rel='stylesheet'>
	<link href='static/css/font-face.css' rel='stylesheet'>
	<script src='static/js/bootbox.min.js'></script>
	<link href='static/css/nav.css' rel='stylesheet'>
	<link href="static/css/aa.css" rel="stylesheet">
	 <link href="static/css/animate.css" rel="stylesheet" type="text/css" media="all">
	<script src="static/js/wow.min.js"></script>
	<script>
		 new WOW().init();
	</script>
</head>

<body>
<!--banner start here-->
<div class="banner">
  <div class="header">
	<div class="container">
		 <div class="header-main">
				<div class="logo">
					<h1>HD Bank</h1>
				</div>
				<div class="header-right">
					<div class="head-top">
					<br>
					<br>
					<div class="top-nav-right">
						<h3 style="color:black">${message}</h3>
						
                  		
                  
				   </div>
				   <div class="top-nav-right" style="margin-left:10px">
				   <a href="dataSelect">
				   <h1 class="glyphicon glyphicon glyphicon-star" style="font-size:1.5em;color:red"></h1>
				   </a>
				   </div>
				  <div class="ph-numb">  
				   	<div id="loginContainer">
				   		<a onclick="checkOut()" style="text-align:right"><button class="btn btn-warning" style="width:96px;height:35px;background:#FFAD06"><font style="font-size:1.1em;color:#fff">Logout</font></button></a>
					</div>
				 </div>
				</div>
				 
				 
		    </div>

		 <div class="clearfix"> </div>
	  </div>
		 <div class="container " style="width:100%;">


		 	<div id="wrapper" >
        
        <!-- /. NAV TOP  -->
        
        <!--  <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse" style="font-size:17px">
                <ul class="nav" id="main-menu">
                   <li>
                        <a class="active-menu" href="userMainpage"><i class="fa fa-dashboard "></i><label>Home</label></a>
                    </li>
                   
                     
                    <li>
                        <a href="userTransfer" class="hvr-sweep-to-right"><i class="fa fa-flash "></i><label>Transfer</label></a>
                        
                    </li>
                    <li>
                        <a href="userPayment" class="hvr-sweep-to-right"><i class="fa fa-flash "></i><label>Payment Service</label></a>
                        
                    </li>
                     <li>
                        <a href="userBalancePage" class="hvr-sweep-to-right"><i class="fa fa-bicycle "></i><label>Account Enquiry</label></a>
                         <!-- <ul class="nav nav-second-level collapse">
                           
                             <li>
                                <a href="getUserBalance" class="hvr-sweep-to-right"><i class="fa fa-desktop "></i>Balance </a>
                            </li>
                            <li>
                                <a href="userLastTen" class="hvr-sweep-to-right"><i class="fa fa-code "></i>Last ten</a>
                            </li>
                            <li>
                                <a href="userHistory" class="hvr-sweep-to-right"><i class="fa fa-code "></i>History</a>
                            </li>
                             
                           
                        </ul> -->
                   <!--  </li>                
                </ul>

            </div>

        </nav>-->
        
       
 <nav class="navbar  sidebar navbar-fixed-top" role="navigation">
<div class="nav-side-menu navbar-default navbar-side" role="navigation">
  
  
        <div class="menu-list">
  
            <ul id="menu-content" class="nav menu-content collapse out">
                <li>
                        <a href="userMainpage" class="hvr-sweep-to-right"><i class="fa fa-university"></i> Home</a>                       
                    </li>    
                    <li>
                        <a href="userTransfer"  class="hvr-sweep-to-right"><i class="fa fa-exchange"></i> Transfer</a>
                    </li>                  
                     <li>
                        <a href="userEnquiry" class="hvr-sweep-to-right"><i class="fa fa-pie-chart"></i> Account Enquiry</a>
                    </li> 
                    <li>
                        <a href="FundPage" class="hvr-sweep-to-right"><i class="fa fa-money"></i> Fund</a>                       
                    </li>   
                     <li>
                        <a href="userDeposit" class="hvr-sweep-to-right"><i class="fa fa-jpy"></i> Time Deposit</a>                       
                    </li> 
                    <li>
                        <a href="userForeignExchange" class="hvr-sweep-to-right"><i class="fa fa-usd"></i> Foreign Exchange</a>                       
                    </li>    
                    <li>
                        <a href="userPayment" class="hvr-sweep-to-right"><i class="fa fa-paypal"></i> Payment Service</a>                       
                    </li>    
            </ul>
     </div>
</div>
  </nav>
   <!-- /. NAV SIDE  -->
      
	
           
            <!-- /. PAGE INNER  -->
        
        <!-- /. PAGE WRAPPER  -->
        
            <div id="page-inner" class="col-md-12">
								<div class="carousel fade-carousel slide" data-ride="carousel" data-interval="2500" id="bs-carousel"  style="width:100%;height:362px">
									  <!-- Overlay -->
									  <div class="overlay"></div>
									
									  <!-- Indicators -->
									  <ol class="carousel-indicators">
									    <li data-target="#bs-carousel" data-slide-to="0" class="active"></li>
									    <li data-target="#bs-carousel" data-slide-to="1"></li>
									    <li data-target="#bs-carousel" data-slide-to="2"></li>
									    <li data-target="#bs-carousel" data-slide-to="3"></li>
									     <li data-target="#bs-carousel" data-slide-to="4"></li>
									  </ol>
									  
									  <!-- Wrapper for slides -->
									  <div class="carousel-inner"  style="width:100%;height:365px">
									    <div class="item slides active" >
									      <div class="slide-1" style="width:100%;height:362px"></div>
									      <div class="hero">
									        <hgroup>
									            <h1>ACCESS ACCOUNTS</h1>      
									            <h3 style="color:black">Check all activities whenever you want</h3>
									            <br>
									        </hgroup>
									        <button class="btn btn-hero btn-lg" role="button">See all features</button>
									      </div>
									    </div>
									    <div class="item slides" >
									      <div class="slide-2" style="width:100%;height:362px"></div>
									      <div class="hero" >        
									        <hgroup>
									            <h1>MANAGE FUNDS</h1>        
									            <h3 style="color:black">Get access to a range of sophisticated investments </h3>
									            <br> 
									        </hgroup>       
									        <button class="btn btn-hero btn-lg" role="button">See all features</button>
									      </div>
									    </div>
									    <div class="item slides" >
									      <div class="slide-3" style="width:100%;height:362px"></div>
									      <div class="hero">        
									        <hgroup>
									            <h1>TERM DEPOSITS</h1>        
									            <h3 style="color:black">Enjoy a fixed rate of return for the nominated term
									            </h3>
									            <br>
									        </hgroup>
									        <button class="btn btn-hero btn-lg" role="button">See all features</button>
									      </div>
									    </div>
									      <div class="item slides" >
									      <div class="slide-4" style="width:100%;height:362px"></div>
									      <div class="hero">        
									        <hgroup>
									            <h1>PAYMENT SERVICES</h1>        
									            <h3 style="color:black">Enjoy an easy way to payment
									            </h3>
									            <br>
									        </hgroup>
									        <button class="btn btn-hero btn-lg" role="button">See all features</button>
									      </div>
									    </div>
									      <div class="item slides" >
									      <div class="slide-5" style="width:100%;height:362px"></div>
									      <div class="hero">        
									        <hgroup>
									            <h1>FOREIGN EXCHANGE</h1>        
									            <h3 style="color:black">Benefit from favorable exchange rate movements
									            </h3>
									            <br>
									        </hgroup>
									        <button class="btn btn-hero btn-lg" role="button">See all features</button>
									      </div>
									    </div>
									  </div> 
									</div>
                            </div>
                  
                    <!--/.Chat Panel End-->
                </div>
        
        
    </div>

		 </div>
	 </div>
 </div>
</div>
 <script>
 	function checkOut(){
 		bootbox.dialog({
 			message:"Do you want to logout?",
 			title:"Notice",
 			buttons:{
 				confirm: {
 				      label: "Confirm",
 				      className: "btn-success",
 				      callback: function() {
 				        window.location.href="userLogin";
 				      }
 				    },
 				   Return: {
 				      label: "Return",
 				      className: "btn-primary",
 				      callback: function() {
 				      }
 				    }
 			}
 		});
 	}
 </script>
<!--banner start here-->
<!--banner strip here-->
		<div class="copyright">
			<p style="color:black">2016 HD Banks . All rights reserved | Design by  <a  target="_blank">  Deft Lee & Helen Huang</a></p>
		</div>
<!--footer end here-->

</body>
</html>