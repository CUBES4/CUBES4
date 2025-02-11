<?php include 'header.php'; 
require_once __DIR__ . '/../vendor/autoload.php';
include_once __DIR__ . '/../app/controllers/ArticleController.php';
include_once __DIR__ . '/../app/controllers/FamilyController.php';
require_once __DIR__ . '/../db.php';

use App\Controllers\ProductController;
use App\Controllers\FamilyController;
$controller = new ProductController();
$article = $controller->index();
$controller = new FamilyController();
$family = $controller->getAllFamilies();

if (isset($_GET['btn_by_family'])) {
    $controller = new FamilyController();
    $articleByFamily = $controller->getFamilyById($_GET['btn_by_family']);
} else {
    $articleByFamily = $article;
}

?>
<!DOCTYPE html>
<html>
<head>
    <title>Nos Vins</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div class="container">
    <h1>Nos Vins</h1>

    <div class="filter-buttons">
        <?php if (!isset($_GET['btn_by_family'])): foreach ($article as $article): ?>
        <div class="card">
            <h3><?php echo $article['name']; ?></h3>
            <p><?php echo $article['description']; ?></p>
            <p><strong>Prix:</strong><?php echo $article['unitPrice']; ?>€</p>
            <label for="qty-<?php echo $article['id']; ?>">Quantité:</label>
            <input type="number" id="qty-<?php echo $article['id']; ?>" value="1" min="1" max="99" class="qty-selector">
            <button onclick="addToCart(<?php echo $article['id']; ?>)">Ajouter</button>
        </div>
        <?php endforeach; endif;?>
        <?php foreach ($family as $family):?>
            <form action="" method="get">
                <button name="btn_by_family" type="submit" value="<?php echo $family['id']; ?>"><?php echo $family['name']; ?></button>
            </form>
        <?php endforeach; ?>
        <?php if (isset($_GET['btn_by_family'])): ?>
            <?php if (isset($articleByFamily['articles']) && is_array($articleByFamily['articles'])): ?>
                <?php foreach ($articleByFamily['articles'] as $article): ?>
                    <div class="card">
                        <h3><?php echo htmlspecialchars($article['name']); ?></h3> 
                        <p><?php echo htmlspecialchars($article['description'] ?? "Aucune description"); ?></p>
                        <p><strong>Prix:</strong> <?php echo number_format($article['unitPrice'] ?? 0.0, 2); ?>€</p>
                        <label for="qty-<?php echo $article['id']; ?>">Quantité:</label>
                        <input type="number" id="qty-<?php echo $article['id']; ?>" value="1" min="1" max="99" class="qty-selector">
                        <button onclick="addToCart(<?php echo $article['id']; ?>)">Ajouter</button>
                    </div>
                <?php endforeach; ?>
            <?php else: ?>
                <p>Aucun article trouvé pour cette famille.</p>
            <?php endif; ?>
        <?php endif; ?>



    </div>
    <div class="sort-buttons">
        <button onclick="sortArticles('asc')">Prix ↑</button>
        <button onclick="sortArticles('desc')">Prix ↓</button>
    </div>

    <div class="cards-container" id="articles">
    </div>
</div>

<script src="script.js"></script>


</body>
</html>


<style>
  
.filter-buttons {
    text-align: center;
    margin-bottom: 20px;
    display: flex;
    justify-content: center;
    gap: 10px; 
    flex-wrap: wrap; 
}

.filter-buttons button {
    background: #007bff;
    color: white;
    border: none;
    padding: 12px 20px;
    font-size: 16px;
    cursor: pointer;
    border-radius: 5px;
    transition: 0.3s;
    font-weight: bold;
    box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
}

.filter-buttons button:hover {
    background: #0056b3;
    transform: scale(1.05);
}


.filter-buttons button.active {
    background: #28a745;
}

</style>