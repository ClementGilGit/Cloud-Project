<?php

require_once('vendor/autoload.php');

use GuzzleHttp\Client;
use GuzzleHttp\Exception\RequestException;

$client = new Client(['verify' => false]);

try {
	$response = $client->get('https://still-fact-164115.appspot.com/shopping-service/shopping/book/' . $_POST['isbn_stock']);
	$respStock = json_decode($response->getBody())->response;
}catch (RequestException $e) {
 	$respStock = "Erreur : Service indisponible";
}

require('view.php');