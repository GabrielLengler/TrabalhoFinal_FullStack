<?php

define("BASE_URL", "/WorldMarket/");

$usuarioLogado = false;
$usuario = null;

$ch = curl_init("http://localhost:8080/user/me");

curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

if (isset($_SERVER["HTTP_COOKIE"])) {
    curl_setopt($ch, CURLOPT_HTTPHEADER, [
        "Cookie: " . $_SERVER["HTTP_COOKIE"]
    ]);
}

$resposta = curl_exec($ch);
$status = curl_getinfo($ch, CURLINFO_HTTP_CODE);

curl_close($ch);

if ($status === 200) {

    $usuario = json_decode($resposta, true);

    $usuarioLogado = true;

}