<?php

#region require
session_start();
$search_recipe = "";

require_once __DIR__ . '/../../vendor/autoload.php';
include_once __DIR__ . '/../controllers/ArticleController.php';

use App\Controllers\ProductController;
$controller = new ProductController();
$article = $controller->index();

if (isset($_GET['btn_search_article_name']) || isset($_GET['btn_search_article_price'])) {
    $searchField  = isset($_GET['btn_search_article_name']) ? 'search_article_name' : 'search_article_price';
    $searchMethod = isset($_GET['btn_search_article_name']) ? 'getArticleByName' : 'getArticleByMaxPrice';

    // Transfert de POST vers GET si besoin
    if (isset($_POST[$searchField])) {
        $_GET[$searchField] = $_POST[$searchField];
    }
    if (!empty($_GET[$searchField])) {
        $article = $controller->$searchMethod($_GET[$searchField]);
        $_SESSION[$searchField] = $_GET[$searchField];
    }
    if (empty($article) || count($article) <= 0) {
        $searchMessage = "Aucune recette trouvée.";
    }
}
?>

<!--#region DOCTYPE Head -->
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil - Partage de Recettes</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="style.css">
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.4.1/dist/flowbite.min.css" rel="stylesheet" />
</head>
<!--#endregion -->

<body>
    <!--#region Header -->
    <header class="sticky top-0 w-full z-20">
        <nav class="border-b border-gray-200 flex flex-wrap items-center justify-between p-4 start-0 bg-[#C49D83] ">
            <!--#region Brand -->
            <a href="index.php" class="cursor-pointer flex items-center rtl:space-x-reverse space-x-3 w-500 h-250">
                <img src="https://placehold.co/20x20" alt="Brand" class="w-20 h-20" />
                <span class="self-center text-2xl font-semibold whitespace-nowrap ">EcoCook</span>
            </a>
            <!--#endregion -->
            <!--#region User Icon Navbar -->
            <div class="flex flex-row items-center md:order-last gap-2">
                <button data-collapse-toggle="top-navbar"
                    class="flex md:hidden cursor-pointer focus:outline-none focus:ring-2 focus:ring-gray-200 hover:bg-gray-100 inline-flex items-center justify-center p-2 rounded-lg text-gray-500">
                    <svg aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 17 14"
                        class="w-5 h-5">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M1 1h15M1 7h15M1 13h15" />
                    </svg>
                </button>
                <a href="user.php" class="">
                        <img alt="Profile icon"
                            src="https://placehold.co/20x20" />
                </a>
            </div>
            <!--#endregion -->
            <!--#region Top Navbar -->
            <div id="top-navbar" class="md:flex md:flex-row md:order-none md:w-auto order-last w-full hidden gap-8">
                <div
                    class="font-medium flex flex-col p-4 md:p-0 mt-4 rounded-lg bg-[#C49D837F] md:flex-row md:space-x-8 rtl:space-x-reverse md:mt-0 md:border-0 md:bg-[#C49D830F]">
                    <a href="recipes.php"
                        class="block cursor-pointer hover:bg-white-100 md:border-0 md:hover:bg-transparent md:hover:text-[#A17C5E] md:p-0 px-3 py-2 rounded text-white-100">Recherche</a>
                    <a href="about.php"
                        class="block cursor-pointer hover:bg-white-100 md:border-0 md:hover:bg-transparent md:hover:text-[#A17C5E] md:p-0 px-3 py-2 rounded text-white-100">À Propos</a>
                    <a href="contact.php"
                        class="block cursor-pointer hover:bg-white-100 md:border-0 md:hover:bg-transparent md:hover:text-[#A17C5E] md:p-0 px-3 py-2 rounded text-white-100">Contact</a>
                    <?php if (isset($_SESSION["user_mail"])): ?>
                        <a href="logout.php"
                            class="block cursor-pointer hover:bg-gray-100 md:border-0 md:hover:bg-transparent md:hover:text-[#A17C5E] md:p-0 px-3 py-2 rounded text-white-100">Déconnexion</a>
                    <?php else: ?>
                        <a href="login.php"
                            class="block cursor-pointer hover:bg-gray-100 md:border-0 md:hover:bg-transparent md:hover:text-[#A17C5E] md:p-0 px-3 py-2 rounded text-white-100">Connexion
                            / Inscription</a>
                    <?php endif; ?>
                </div>
            </div>
            <!--#endregion -->
        </nav>
    </header>
    <!--#endregion -->
    <!--#region Main -->
    <main class="container py-6 mx-auto">
        <!--#region Search -->
        <form class="flex flex-row px-16 pb-4 py gap-4" action="" method="get">
            <div class="relative w-full">
                <div class="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">
                    <svg class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true"
                        xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z" />
                    </svg>
                </div>
                <input type="text" id="name-search" name="search_article_name"
                    class="block w-full p-4 ps-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-brown-500 focus:border-brown-500"
                    placeholder="Rechercher par nom" />
                <button type="submit" name="btn_search_article_name"
                    class="text-white absolute end-2.5 bottom-2.5 bg-[#A17C5E] hover:bg-[#C49D83] focus:ring-4 focus:outline-none focus:ring-brown-300 font-medium rounded-lg text-sm px-4 py-2">Chercher</button>
            </div>
            <div class="relative w-full">
                <div class="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">
                    <svg class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true"
                        xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z" />
                    </svg>
                </div>
                <input type="text" id="price-search" name="search_article_price"
                    class="block w-full p-4 ps-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-brown-500 focus:border-brown-500"
                    placeholder="Rechercher par prix max" />
                <button type="submit" name="btn_search_article_price"
                    class="text-white absolute end-2.5 bottom-2.5 bg-[#A17C5E] hover:bg-[#C49D83] focus:ring-4 focus:outline-none focus:ring-brown-300 font-medium rounded-lg text-sm px-4 py-2">Chercher</button>
            </div>
        </form>
        <!--#endregion -->
        <!--#region Display Search result -->        
        <section class="px-8 mx-auto">
            <!--#region Recipe List -->
            <div class="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 md:gap-8">
                <?php foreach ($article as $article): ?>
                    <div class="relative flex flex-col items-center bg-white/75 border border-gray-200 rounded-lg min-h-64">
                        <!--
                        <div class="absolute top-1 right-1 flex items-start gap-2.5">
                            <button data-dropdown-placement="bottom-start" id="0"
                                class="inline-flex self-center items-center p-2 text-sm font-medium text-center text-gray-900 bg-slate-200/50 rounded-lg hover:bg-slate-400/50"
                                type="button">
                                <svg class="w-4 h-4" aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
                                    fill="currentColor" viewBox="0 0 4 15">
                                    <path
                                        d="M3.5 1.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0Zm0 6.041a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0Zm0 5.959a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0Z" />
                                </svg>
                            </button>
                            <div id="0"
                                class="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-40">
                                <ul class="py-2 text-sm text-gray-700" aria-labelledby="0">
                                    <li>
                                        <a href="recipe.php"
                                            class="block px-4 py-2 hover:bg-gray-100/50"></a>
                                    </li>
                                    <li>
                                            <a href="share.php"
                                                class="block px-4 py-2 hover:bg-gray-100/50">Partager</a>
                                    </li>
                                        <li>
                                            <a href="recipe.php"
                                                class="block px-4 py-2 hover:bg-gray-100/50">Modifier</a>
                                        </li>
                                </ul>
                            </div>
                        </div>-->
                        <!--#endregion -->
                        <!--#region Recipe Card -->
                        <div class="w-full max-h-96 flex flex-col gap-2">
                            <div class="w-full basis-1/3">
                                <img class="object-cover w-full h-48 rounded-t-lg" src="https://www.monvinpersonnalise.fr/820-large_default/vin-rouge-de-france.jpg"
                                    alt="Placeholder" />
                            </div>
                            <div class="w-full flex flex-col gap-1 p-4 basis-2/3 text-ellipsis">
                                <a class="mb-3 text-gray-900 line-clamp-2 text-2xl font-bold leading-tight"
                                    href="article.php?id=<?php echo $article['id']; ?>"><?php echo $article['name']; ?>
                                    <span class="text-gray-400 font-light text-sm"><?php echo $article['unitPrice']; ?>€</span>
                                </a>
                                <p class="mb-2 line-clamp-2 text-gray-700 font-normal"><?php echo $article['description']; ?></p>
                            </div>
                        </div>
                        <!--#endregion -->
                    </div>
                <?php endforeach; ?>
            </div>
            <!--#endregion -->
        </section>
        <!--#endregion -->
    </main>
    <!--#endregion -->
    <script src="https://cdn.jsdelivr.net/npm/flowbite@2.4.1/dist/flowbite.min.js"></script>
</body>
</html>