<?php

require_once('vendor/autoload.php');

use GuzzleHttp\Client;
use GuzzleHttp\Exception\RequestException;

$client = new Client(['verify' => false]);

try {
	$response = $client->get('https://still-fact-164115.appspot.com/shopping-service/shopping/buy/' . $_POST['isbn_buy'] . '/' .  $_POST['quantity_buy']);
	$respBuy = json_decode($response->getBody())->response;
}catch (RequestException $e) {
 	$respBuy = "Erreur : Service indisponible";
}

require('view.php');