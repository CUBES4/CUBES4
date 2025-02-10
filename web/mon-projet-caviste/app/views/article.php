<?php

session_start(); // Démarre ou reprend une session au début de chaque script

require_once __DIR__ . '/../../vendor/autoload.php';
include_once __DIR__ . '/../controllers/ArticleController.php';

$id = $_GET["id"];

use App\Controllers\ProductController;
$controller = new ProductController();
$article = $controller->show($id);
?>
<!--#region DOCTYPE Head -->
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produit XXX</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="style.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.4.1/dist/flowbite.min.css" rel="stylesheet" />
</head>
<!--#endregion -->

<body>
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
    <main class="container py-6 mx-auto">
        <section class="px-8 mx-auto">
            <div class="flex flex-col xl:flex-row min-h-[536px] gap-4">
                <div class="flex flex-col md:flex-row md:basis-2/3 border border-gray-300 rounded-t-lg xl:rounded-l-lg bg-white/40 xl:rounded-tr-none">
                    <div class="flex flex-col basis-3/5 p-4 divide-y divide-gray-200">
                        <form method="post">
                            <div class="flex flex-row justify-between">
                                <h1 class="text-3xl font-bold text-gray-900 leading-tight">
                                <?php echo $article['name']; ?>
                                </h1>
                            </div>
                            <div class="flex flex-col text-gray-400 font-light text-md">
                                <span class="text-normal font-normal leading-tight">
                                    <span class="underline font-semibold text-black"><?php echo $article['unitPrice']; ?>€</span>
                                </span>
                            </div>
                            <div class="flex flex-col max-h-96 overflow-y-auto">
                                <p class="text-normal font-normal leading-tight pt-1">
                                    <span class="underline font-semibold text-black">Description :</span>
                                    <br />
                                    <span class="whitespace-pre-wrap text-black"><?php echo $article['description']; ?></span>
                                </p>
                            </div>
                        </form>
                    </div>
                    <div class="flex flex-col basis-2/5">
                            <img src="https://www.monvinpersonnalise.fr/820-large_default/vin-rouge-de-france.jpg" class="basis-2/5 m-4 h-12 object-cover rounded-xl shadow-md" alt="...">
                        </div>
                </div>
            </div>
        </section>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/flowbite@2.4.1/dist/flowbite.min.js"></script>
</body>
</html>