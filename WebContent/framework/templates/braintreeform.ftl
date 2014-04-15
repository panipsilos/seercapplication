<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
          "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:p="http://primefaces.org/ui">

<h:head>
	<title><ui:insert name="title">Default title</ui:insert></title>

</h:head>



<body>
	<h:graphicImage id="image" alt="jsf-sun" url="seerc.jpg"
		style="height: 115px; width: 266px; "></h:graphicImage>
	<p:panel id="panel" header="Make a Test Purchase using Braintree">
		<form action="/PaymentServiceProviders/BraintreeAuthoriseServlet" method="post" id="braintree-payment-form">
        <p>
          <label>Card Number</label>
          <input type="text" size="20" autocomplete="off" data-encrypted-name="number" />
        </p>
        <p>
          <label>CVV</label>
          <input type="text" size="4" autocomplete="off" data-encrypted-name="cvv" />
        </p>
        <p>
          <label>Expiration (MM/YYYY)</label>
          <input type="text" size="2" name="month" /> / <input type="text" size="4" name="year" />
        </p>
        <h:graphicImage id="image" alt="jsf-sun" url="drivein12.jpg"></h:graphicImage>
        <br></br>
        <input type="submit" id="submit" />
      </form>
	 <script src="https://js.braintreegateway.com/v1/braintree.js"></script>   
    <script>
      var braintree = Braintree.create("MIIBCgKCAQEA4qrrBBrp3eDu5OCgICU4qQiYp6/BdZhKb6LDZ+jmYZ/7fvbhDiQwHz/uq4EmVZkASZW2eWxQ6qAuG2X7N3YCycf4Za48F7GJYIJwubyErWA6ViCr/uxI2AByZQP8DVjvP1HouRZFUEmzip8N49BqyPOBSNCKcxHVNUJB3RMQ65aEpX8oNcMuOxaxy1hGoDdti+ZpBdR0gQb0MfIJzRh1AjGlOPuUA3T2bLVC864/221CmJMzjAZG1eT5/GDiTda0Azps7I2EPtISTbggo0QQSkSll5QmqF+nsKnlShxg3DS6ovsOpSOL0By63WjEf7j4dXW2Fo7AqBA64YEeW2HbIwIDAQAB");
      braintree.onSubmitEncryptForm('braintree-payment-form');
    </script> 
	<h4>Test Card: 4111 1111 1111 1111</h4>
	</p:panel>

</body>

</html>





