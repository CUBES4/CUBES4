<?php
// Si nécessaire, incluez vos modèles et l'autoloader Composer AVANT la déclaration du namespace
// Par exemple :
// include_once __DIR__ . '/../models/Customer.php';
// require_once __DIR__ . '/../../vendor/autoload.php';

namespace App\Controllers;

use GuzzleHttp\Client;
use GuzzleHttp\Exception\RequestException;

class CustomerController
{
    private string $apiEndpoint;
    private Client $httpClient;

    public function __construct()
    {
        // URL de base pour les clients
        $this->apiEndpoint = 'http://localhost:8080/api/clients';
        $this->httpClient  = new Client();
    }

    /**
     * Récupérer tous les clients
     * GET /api/clients
     */
    public function getAllCustomers(): ?array
    {
        try {
            $response = $this->httpClient->get($this->apiEndpoint);
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            // Vous pouvez logger l'erreur ici
            return null;
        }
    }

    /**
     * Récupérer un client par ID
     * GET /api/clients/{id}
     */
    public function getCustomerById(int $id): ?array
    {
        try {
            $response = $this->httpClient->get("{$this->apiEndpoint}/{$id}");
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            return null;
        }
    }

    /**
     * Rechercher des clients par nom ou prénom
     * GET /api/clients/recherche?lastName=xxx&firstName=yyy
     */
    public function getCustomersByName(?string $firstName = null, ?string $lastName = null): ?array
    {
        try {
            $query = [];
            if ($firstName !== null) {
                $query['firstName'] = $firstName;
            }
            if ($lastName !== null) {
                $query['lastName'] = $lastName;
            }
            $response = $this->httpClient->get($this->apiEndpoint . '/recherche', [
                'query' => $query
            ]);
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            return null;
        }
    }

    /**
     * Rechercher des clients par email
     * GET /api/clients/recherche/email?email=xxx
     */
    public function getCustomersByEmail(string $email): ?array
    {
        try {
            $response = $this->httpClient->get($this->apiEndpoint . '/recherche/email', [
                'query' => ['email' => $email]
            ]);
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            return null;
        }
    }

    /**
     * Rechercher des clients par numéro de téléphone
     * GET /api/clients/recherche/telephone?phoneNumber=xxx
     */
    public function getCustomersByPhoneNumber(string $phoneNumber): ?array
    {
        try {
            $response = $this->httpClient->get($this->apiEndpoint . '/recherche/telephone', [
                'query' => ['phoneNumber' => $phoneNumber]
            ]);
            return json_decode($response->getBody(), true);
        } catch (RequestException $e) {
            return null;
        }
    }

    /**
     * Créer un nouveau client
     * POST /api/clients
     */
    public function createCustomer(array $data): ?array
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
     * Mettre à jour un client existant
     * PUT /api/clients/{id}
     */
    public function updateCustomer(int $id, array $data): ?array
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
     * Supprimer un client par ID
     * DELETE /api/clients/{id}
     */
    public function deleteCustomer(int $id): bool
    {
        try {
            $this->httpClient->delete("{$this->apiEndpoint}/{$id}");
            return true;
        } catch (RequestException $e) {
            return false;
        }
    }
}
?>