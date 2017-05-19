<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head> 

<body>
	<div class="container">
		<h2 class="text-center">Client Guzzle</h2>
		<form class="form-inline" method="POST" action="StockController.php" style="margin-top: 50px;">
			<fieldset>
				<legend>Consultation du Stock</legend>
				<div class="form-group">
					<div class="col-md-12">
						<label class="control-label" for="textinput">ISBN :</label>  
					</div>
				</div>
				<div class="form-group">
				  
				  <div class="col-md-5">
					  <input id="textinput" name="isbn_stock" type="text" placeholder="0-123456-47-9" class="form-control input-md" required="">
				  </div>
				</div>
				<div class="form-group">
				  <div class="col-md-2">
				    <button type="submit" class="btn btn-primary">Calculer</button>
				  </div>
				</div>
				  	<div  class="form-group">
				  	<label class="control-label">
		 				<?php if(isset($respStock)){ echo $respStock; }?>
				  	</label>
				  	</div>
			</fieldset>
		</form>

		<form class="form-inline" method="POST" action="WholeSalerController.php" style="margin-top: 100px;">
			<fieldset>
				<legend>Achat d'un livre</legend>
				<div class="form-group">
					<div class="col-md-12">
						<label class="control-label" for="textinput">ISBN :</label>  
					</div>
				</div>
				<div class="form-group">
				  
				  <div class="col-md-5">
					  <input id="textinput" name="isbn_buy" type="text" placeholder="0-123456-47-9" class="form-control input-md" required="">
				  </div>
				</div>
				<div class="form-group">
					<div class="col-md-12">
						<label class="control-label" for="textinput">Quantité :</label>  
					</div>
				</div>
				<div class="form-group">
				  
				  <div class="col-md-5">
					  <input id="textinput" name="quantity_buy" type="number" min="1"  placeholder="Quantité" class="form-control input-md" required="">
				  </div>
				</div>
				<div class="form-group">
				  <div class="col-md-2">
				    <button type="submit" class="btn btn-primary">Acheter</button>
				  </div>
				</div>
			 	<div  class="form-group">
				  	<label class="control-label">
		 				<?php if(isset($respBuy)){ echo $respBuy; }?>
				  	</label>
			  	</div>
			</fieldset>
		</form>
	</div>
</body>


