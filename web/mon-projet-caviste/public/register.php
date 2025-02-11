<?php
require_once '../db.php';

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $username = trim($_POST["username"]);
    $password = password_hash($_POST["password"], PASSWORD_BCRYPT);

    $stmt = $pdo->prepare("INSERT INTO users (username, password) VALUES (?, ?)");

    try {
        $stmt->execute([$username, $password]);
        $message = "Inscription réussie ! <a href='login.php'>Se connecter</a>";
    } catch (PDOException $e) {
        $message = "Erreur : Nom d'utilisateur déjà utilisé.";
    }
}
?>

<?php include 'header.php'; ?>

<!DOCTYPE html>
<html>
<head>
    <title>Inscription</title>
    <style>
        /* ======== GLOBAL STYLES ======== */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* ======== CONTAINER ======== */
        .form-wrapper {
            width: 100%;
            height: 100vh; /* Prend toute la hauteur de l'écran */
            position: relative;
        }

        /* ======== FORM CONTAINER ======== */
        .form-container {
            background: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            width: 350px;
            text-align: center;

            /* Centrer l'élément sans toucher body */
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        /* ======== FORM INPUTS ======== */
        .form-container h2 {
            margin-bottom: 20px;
            color: #333;
        }

        .form-container input {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        /* ======== BUTTON STYLES ======== */
        .form-container button {
            width: 100%;
            padding: 12px;
            background: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: 0.3s;
        }

        .form-container button:hover {
            background: #0056b3;
        }

        /* ======== LINKS ======== */
        .form-container a {
            display: block;
            margin-top: 15px;
            color: #007bff;
            text-decoration: none;
            font-size: 14px;
        }

        .form-container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="form-wrapper">
    <div class="form-container">
        <h2>Créer un compte</h2>

        <?php if (isset($message)) echo "<p style='color:red;'>$message</p>"; ?>

        <form method="POST">
            <input type="text" name="username" placeholder="Nom d'utilisateur" required>
            <input type="password" name="password" placeholder="Mot de passe" required>
            <button type="submit">S'inscrire</button>
        </form>
        <a href="login.php">Déjà un compte ? Connectez-vous</a>
    </div>
</div>

</body>
</html>
