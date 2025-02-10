<?php
// test.php situé dans mon-projet-caviste/app/controllers/
require_once __DIR__ . '/../../vendor/autoload.php';
include_once __DIR__ . '/../controllers/ArticleController.php';

use App\Controllers\ProductController;

// Instanciation du contrôleur
$controller = new ProductController();

// Exemple : récupérer tous les articles
$articles = $controller->index();

echo "<h2>Tous les articles :</h2>";
echo "<pre>" . print_r($articles, true) . "</pre>";
