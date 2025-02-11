<?php

header('Content-Type: application/json');
session_start();
require_once '../db.php';

error_reporting(E_ALL);
ini_set('display_errors', 1);


ob_start();

$method = $_SERVER['REQUEST_METHOD'];
$action = $_GET['action'] ?? '';

if ($method === 'GET' && $action === 'articles') {
    try {
        if (!empty($_GET['family_id'])) {
            $stmt = $pdo->prepare("SELECT * FROM articles WHERE family_id = ?");
            $stmt->execute([$_GET['family_id']]);
        } else {
            $stmt = $pdo->query("SELECT * FROM articles");
        }
        $articles = $stmt->fetchAll(PDO::FETCH_ASSOC);

        ob_end_clean();
        echo json_encode($articles);
        exit;
    } catch (Exception $e) {
        ob_end_clean();
        echo json_encode(['error' => 'Erreur lors de la récupération des articles']);
        exit;
    }
}


if ($method === 'POST' && $action === 'cart_add') {
    try {
        $data = json_decode(file_get_contents('php://input'), true);
        if (!isset($data['article_id']) || !isset($data['quantity'])) {
            throw new Exception('Données invalides');
        }

        $article_id = $data['article_id'];
        $quantity = $data['quantity'];

        if (!isset($_SESSION['cart'])) {
            $_SESSION['cart'] = [];
        }

        $_SESSION['cart'][$article_id] = ($_SESSION['cart'][$article_id] ?? 0) + $quantity;

        ob_end_clean();
        echo json_encode(['message' => 'Article ajouté au panier']);
        exit;
    } catch (Exception $e) {
        ob_end_clean();
        echo json_encode(['error' => 'Impossible d\'ajouter au panier']);
        exit;
    }
}

if ($method === 'GET' && $action === 'cart_get') {
    try {
        $cart = $_SESSION['cart'] ?? [];
        $result = [];

        foreach ($cart as $article_id => $qty) {
            $stmt = $pdo->prepare("SELECT * FROM articles WHERE id = ?");
            $stmt->execute([$article_id]);
            $article = $stmt->fetch(PDO::FETCH_ASSOC);

            if ($article) {
                $result[] = [
                    'id' => $article['id'],
                    'name' => $article['name'],
                    'unit_price' => $article['unit_price'],
                    'quantity' => $qty,
                    'subtotal' => $qty * $article['unit_price']
                ];
            }
        }

        ob_end_clean();
        echo json_encode($result);
        exit;
    } catch (Exception $e) {
        ob_end_clean();
        echo json_encode(['error' => 'Erreur lors de la récupération du panier']);
        exit;
    }
}


if ($method === 'POST' && $action === 'order') {
    try {
        if (!isset($_SESSION['user_id'])) {
            throw new Exception('Utilisateur non connecté');
        }

        $customer_id = $_SESSION['user_id'];


        $stmt = $pdo->prepare("INSERT INTO orders (customer_id, status) VALUES (?, 'PENDING')");
        $stmt->execute([$customer_id]);
        $order_id = $pdo->lastInsertId();

        foreach ($_SESSION['cart'] ?? [] as $article_id => $qty) {
            $stmt = $pdo->prepare("INSERT INTO order_lines (order_id, article_id, quantity) VALUES (?, ?, ?)");
            $stmt->execute([$order_id, $article_id, $qty]);
        }

        $_SESSION['cart'] = [];

        ob_end_clean();
        echo json_encode(['message' => 'Commande validée', 'order_id' => $order_id, 'customer_id' => $customer_id]);
        exit;
    } catch (Exception $e) {
        ob_end_clean();
        echo json_encode(['error' => 'Impossible de passer la commande']);
        exit;
    }
}

ob_end_clean();
echo json_encode(['error' => 'Action non reconnue']);
exit;
?>
