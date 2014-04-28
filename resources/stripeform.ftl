
<html>
<head>
<title><ui:insert name="title">Default title</ui:insert></title>
<head>
<body>
	<script src="https://checkout.stripe.com/v2/checkout.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js"></script>
	
	<form action="/PaymentServiceProviders/frontcontroller" method="post" id="form">
	<button id="customButton" style="visibility: hidden" />
	</form>
	<script>
		$('#customButton').click(
						function() {
							var token = function(res) {
								var $input = $(
										'<input type=hidden name=stripeToken />')
										.val(res.id);
								$('form').append($input).submit();
							};

							StripeCheckout.open({
								key : 'pk_test_835zPTsgBymYkiZ3lUQQJaBs',
								panelLabel : 'Checkout',
								token : token
							});

							return false;
						});
	</script>

	<script>
		window.onload = function() {
			document.getElementById("customButton").click();
		}
	</script>

</body>

</html>
