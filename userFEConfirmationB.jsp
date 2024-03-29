<%@ page session="true" %>
<!DOCTYPE HTML>
<html>
<head>
<title>FE ConfirmB</title>
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
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse" style="font-size:17px">
                <ul class="nav" id="main-menu">
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
                        <a href="userForeignExchange" class="active-menu"><i class="fa fa-usd"></i> Foreign Exchange</a>                       
                    </li>    
                    <li>
                        <a href="userPayment" class="hvr-sweep-to-right"><i class="fa fa-paypal"></i> Payment Service</a>                       
                    </li>    
                </ul>
            </div>

        </nav>

        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line" style="text-align:center">CONFIRMATION PAGE</h1>
                    </div>
                </div>
                <!-- /. ROW  -->
                
                <!-- /. ROW  -->

                <div class="row">
                	<div class="col-md-1"></div>
                    <div class="col-md-10">
                        <div class="row">
                        <form method="post" onsubmit="return check()">
							<div class="panel panel-info">
								<div class="panel-heading">TRADING CONFIRM</div>
								<div class="panel-body">
									<div class="form-group">
										<label>Card Number:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
										&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
										&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ${cardNumber}</label>	
										<input type="hidden" value=${cardNumber} name="cardNumber" id="cardNumber">									
									</div>
									<div class="form-group">
										<label>RMB Balance:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
										&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
										&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp CNY ${balance}</label>
										<input type="hidden" value=${balance} name="balance" id="balance">
									</div>
									<div class="form-group">
										<label>Exchange Currency:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
										&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ${type}</label>
										<input type="hidden" value=${type} name="type" id="type">
									</div>
									<div class="form-group">
										<label>Exchange Amount:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
										&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ${amount}</label>
										<input type="hidden" value=${amount} name="amount" id="amount">
									</div>
									<div class="form-group">
										<label>Exchange Bit Rate:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
										&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ${price}</label>
										<input type="hidden" value=${price} name="price" id="price">
									</div>
									<div class="form-group">
										<label>Income RMB Amount:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
										&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp CNY ${income}</label>
										<input type="hidden" value=${income} name="income" id="income">
									</div>
									<div class="form-group">
										<label>Card Pin</label>
										<input id="cardpin" name="cardpin" class="form-control" type="password" required="required" maxlength="6">
									</div>
									<div class="form-group">
										<div class="col-md-2"></div>
										<div class="col-md-3">
											<input class="btn btn-info" type="button" value="Return" onclick="tiao()">
										</div>
										<div class="col-md-2"></div>
										<div class="col-md-3">
											<input class="btn btn-info" type="submit" value="Confirm">
										</div>
										<div class="col-md-2"></div>
									</div>
								</div>
							</div>
							</form>
                        </div>
                        <!-- /. ROW  -->
                        <hr />
                    </div>
                    <div class="col-md-1"></div>
                    <!--/.Chat Panel End-->
                </div>
                <!-- /. ROW  -->


                <!--/.Row-->
        
               
                <!--/.ROW-->

            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
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
 				        window.location.href="userLogout";
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
  <script>
 	function tiao(){
 		window.history.back();
 	}
 </script>
 <script>
 	function check(){
 		var cardNumber=document.getElementById("cardNumber");
 		var type=document.getElementById("type");
 		var amount=document.getElementById("amount");
 		var price=document.getElementById("price");
 		var cardpin=document.getElementById("cardpin");
 		$.ajax({
 			url:"<%=request.getContextPath()%>/userCurrencySell",
 			type:"POST",
 			dataType:"text",
 			data:{
 				'cardNumber':cardNumber.value,
 				'type':type.value,
 				'price':price.value,
 				'amount':amount.value,
 				'cardpin':cardpin.value,
 			},
 			success:function(data){
				console.log(data);
				if(data=="isFronze"){
					bootbox.alert("<br><B>Your card is frozen, please contact system administrator to unfreeze",function (){
						window.location.href="userForeignExchange";});
					return false;
				}
				else if(data=="isLocked"){
					bootbox.alert("<br><B>Your card is locked, please contact system administrator to unlock",function (){
						window.location.href="userForeignExchange";});
					return false;
				}
				else if(data=="shouldLocked"){
					bootbox.alert("<br><B>Sorry you have inputted wrong card pin for 3 times, your card is locked. Please contact system administrator to unlock",function (){
						window.location.href="userForeignExchange";});
					return false;
				}
				else if(data=="wrongCradPin"){
					bootbox.alert("<br><B>Your card pin is wrong, if you input incorrectly for more than 3 times within 1 hour, the card will be locked",function (){
						});
					return false;
				}
				else if(data=="success"){
					bootbox.alert("<br><B>Exchange to RMB successful",function (){
						window.location.href="userForeignExchange";});
					return false;
				}
				else if(data=="failed"){
					bootbox.alert("<br><B>Sorry your operation is failed, please try again",function (){
						window.history.back();});
					return false;
				}
				else if(data=="notype"){
					bootbox.alert("<br><B>Sorry you do not have this currency, please try another one",function (){
						window.history.back();});
					return false;
				}
				else if(data=="nobalance"){
					bootbox.alert("<br><B>Sorry your foreign currency balance is not enough, please try other number",function (){
						window.history.back();});
					return false;
				}
				else{
					bootbox.alert("<br><B>Unknown error, please try again",function (){
						window.history.back();});
					return false;
				}
				return false;
			},
			error:function(){}
 		});
 		return false;
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