<?xml version="1.0" encoding="ISO-8859-1" ?>
    <%@ page import="com.braintreegateway.BraintreeGateway" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

	

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>

	<img id="image"  url="seerc.jpg"
		style="height: 115px; width: 266px; "><img>
	<h:panel id="panel" header="Make a Test Payment using Braintree">
		<form  method="post" id="braintree-payment-form">
        <h>          
          <input type="hidden" name="tr_data"  />
        </h>
        <h>
          <label>Card Number</label>
          <input type="text" name="transaction[credit_card][number]" />
        </h>
        <h>
          <label>CVV</label>
          <input type="text" size="4" autocomplete="off" data-encrypted-name="cvv" />
        </h>
        <h>
          <label>Expiration (MM/YYYY)</label>
         <input type="text" name="transaction[credit_card][expiration_date]" />
        </h>
        <h:graphicImage id="image" alt="jsf-sun" url="drivein12.jpg"></h:graphicImage>
        <br></br>
        <input type="submit" id="submit"  onclick="myFunction()"/>
      </form>	
	</h:panel>

</body>
</html>