
<head>
	<title><ui:insert name="title">Default title</ui:insert></title>

</head>



<body>
		<form id="form" action="https://core.spreedly.com/v1/payment_methods"
			method="post">
			<input name="redirect_url" type="hidden" value="http://127.0.0.1:8090/PaymentServiceProviders/frontcontroller" />
			<input name="environment_key" type="hidden" value="J0QM5AkMDWyzV9NnvPtuNYhsU7Q" /> 
			<label for="credit_card_first_name">First name</label>
			<input id="credit_card_first_name" name="credit_card[first_name]" type="text" /> 
			<label for="credit_card_last_name">Last name</label>
			<input id="credit_card_last_name" name="credit_card[last_name]" type="text" /> <br></br> 
			<label for="credit_card_number">Card Number</label> <input id="credit_card_number" name="credit_card[number]"
				type="text" /> <label for="credit_card_verification_value">Security
				Code</label> <input id="credit_card_verification_value"
				name="credit_card[verification_value]" type="text" /> <label
				for="credit_card_month">Expires on</label> <input
				id="credit_card_month" name="credit_card[month]" type="text" /> <input
				id="credit_card_year" name="credit_card[year]" type="text" /> <br></br>
			
			<input type="submit" value="Pay now">
		</form>
		<h4>Test data: 4111111111111111</h4>

</body>

</html>





