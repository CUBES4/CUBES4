<?php
session_start();
if (!isset($_SESSION["user_id"])) {
    header("Location: login.php");
    exit;
}
?>
<!DOCTYPE html>
<html>
<head>
    <title>Commande validée</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<?php include 'header.php'; ?>


<div class="container">
    <h1>Merci pour votre commande !</h1>
    <p>Votre commande a bien été enregistrée.</p>
</div>

</body>
</html>
