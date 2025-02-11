<?php
session_start();
if (!isset($_SESSION["user_id"])) {
    header("Location: login.php");
    exit;
}
?>

<?php include 'header.php'; ?>


<!DOCTYPE html>
<html>
<head>
    <title>Votre Panier</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>


<div class="container cart-container">
    <h1>Votre Panier</h1>
    <div id="cart"></div>
    <button onclick="placeOrder()">Passer commande</button>
</div>

<script src="script.js"></script>
<script>loadCart();</script>

</body>
</html>
