<?php
// Inclure les fichiers nécessaires (autoloader, modèles, etc.) AVANT la déclaration du namespace
// Par exemple :
// require_once __DIR__ . '/../../vendor/autoload.php';

namespace App\Controllers;

use GuzzleHttp\Client;
use GuzzleHttp\Exception\RequestException;

class FamilyController
{
    private string $apiEndpoint;
    private Client $httpClient;

    public function __construct()
    {
        // URL de base de votre API pour les familles
        $this->apiEndpoint = 'http://localhost:8080/api/familles';
        $this->httpClient  = new Client();
    }

    /**
     * Récupérer toutes les familles
     * GET /api/familles
     */
    public function getAllFamilies(): ?array
    {
        try {
            $response = $this->httpClient->get($this->apiEndpoint);
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            // Vous pouvez gérer/loguer l'erreur ici
            return null;
        }
    }

    /**
     * Récupérer une famille par son ID
     * GET /api/familles/{id}
     */
    public function getFamilyById(int $id): ?array
    {
        try {
            $response = $this->httpClient->get("{$this->apiEndpoint}/{$id}");
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            return null;
        }
    }

    /**
     * Créer une nouvelle famille
     * POST /api/familles
     */
    public function createFamily(array $data): ?array
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

    /**
     * Mettre à jour une famille existante
     * PUT /api/familles/{id}
     */
    public function updateFamily(int $id, array $data): ?array
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

    /**
     * Supprimer une famille
     * DELETE /api/familles/{id}
     */
    public function deleteFamily(int $id): bool
    {
        try {
            $this->httpClient->delete("{$this->apiEndpoint}/{$id}");
            return true;
        } catch (RequestException $e) {
            return false;
        }
    }
}
