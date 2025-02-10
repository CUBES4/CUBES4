<?php

namespace App\Controllers;
// Inclure les fichiers externes AVANT toute déclaration de namespace
require_once __DIR__ . '/../../vendor/autoload.php';


use GuzzleHttp\Client;
use GuzzleHttp\Exception\RequestException;

class ProductController
{
    private string $apiEndpoint;
    private Client $httpClient;

    public function __construct()
    {
        // URL de base de votre API (adapter si besoin)
        $this->apiEndpoint = 'http://localhost:8080/api/articles';
        $this->httpClient  = new Client();
    }

    // ---------- Requêtes GET ----------

    /**
     * Récupérer tous les articles
     * GET /api/articles
     */
    public function index(): ?array
    {
        try {
            $response = $this->httpClient->get($this->apiEndpoint);
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            return null;
        }
    }

    /**
     * Récupérer un article par ID
     * GET /api/articles/{id}
     */
    public function show(int $id): ?array
    {
        try {
            $response = $this->httpClient->get("{$this->apiEndpoint}/{$id}");
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            return null;
        }
    }

    /**
     * Rechercher des articles par nom
     * GET /api/articles/recherche?name=xxx
     */
    public function getArticleByName(string $name): ?array
    {
        try {
            $response = $this->httpClient->get($this->apiEndpoint . '/recherche', [
                'query' => ['name' => $name]
            ]);
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            return null;
        }
    }

    // ---------- Requêtes POST ----------

    /**
     * Rechercher des articles par prix maximal
     * POST /api/articles/recherche/prix-max
     * Corps JSON attendu : { "maxPrice": <valeur> }
     */
    public function getArticleByMaxPrice(float $maxPrice): ?array
    {
        try {
            $response = $this->httpClient->post($this->apiEndpoint . '/recherche/prix-max', [
                'json' => ['maxPrice' => $maxPrice]
            ]);
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            return null;
        }
    }

    /**
     * Rechercher des articles par stock minimum
     * POST /api/articles/recherche/stock-min
     * Corps JSON attendu : { "minStock": <valeur> }
     */
    public function getArticlesByStockMin(int $minStock): ?array
    {
        try {
            $response = $this->httpClient->post($this->apiEndpoint . '/recherche/stock-min', [
                'json' => ['minStock' => $minStock]
            ]);
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            return null;
        }
    }

    /**
     * Rechercher des articles par stock maximal
     * POST /api/articles/recherche/stock-max
     * Corps JSON attendu : { "maxStock": <valeur> }
     */
    public function getArticlesByStockMax(int $maxStock): ?array
    {
        try {
            $response = $this->httpClient->post($this->apiEndpoint . '/recherche/stock-max', [
                'json' => ['maxStock' => $maxStock]
            ]);
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            return null;
        }
    }

    /**
     * Rechercher des articles par plage de stock
     * POST /api/articles/recherche/stock-range
     * Corps JSON attendu : { "stockMin": <valeur>, "stockMax": <valeur> }
     */
    public function getArticlesByStockRange(int $stockMin, int $stockMax): ?array
    {
        try {
            $response = $this->httpClient->post($this->apiEndpoint . '/recherche/stock-range', [
                'json' => [
                    'stockMin' => $stockMin,
                    'stockMax' => $stockMax
                ]
            ]);
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            return null;
        }
    }

    /**
     * Créer un nouvel article
     * POST /api/articles
     */
    public function store(array $data): ?array
    {
        try {
            $response = $this->httpClient->post($this->apiEndpoint, [
                'json' => $data
            ]);
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            return null;
        }
    }

    // ---------- Requêtes PUT ----------

    /**
     * Mettre à jour un article
     * PUT /api/articles/{id}
     */
    public function update(int $id, array $data): ?array
    {
        try {
            $response = $this->httpClient->put("{$this->apiEndpoint}/{$id}", [
                'json' => $data
            ]);
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            return null;
        }
    }

    // ---------- Requêtes DELETE ----------

    /**
     * Supprimer un article
     * DELETE /api/articles/{id}
     */
    public function destroy(int $id): bool
    {
        try {
            $this->httpClient->delete("{$this->apiEndpoint}/{$id}");
            return true;
        } catch (RequestException $e) {
            return false;
        }
    }
}
