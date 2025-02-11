
async function loadArticles() {
    try {
        let response = await fetch('api.php?action=articles');
        articles = await response.json();

        if (!Array.isArray(articles) || articles.length === 0) {
            console.error("Erreur : la réponse de l'API est vide ou invalide.", articles);
            articles = []; 
        } else {
            console.log("Articles chargés :", articles); // Vérification
        }

        displayArticles(articles);
    } catch (error) {
        console.error("Erreur lors du chargement des articles :", error);
    }
}


async function filterByFamily(familyId) {
    let response = await fetch(`api.php?action=articles&family_id=${familyId}`);
    let articles = await response.json();
    displayArticles(articles);
}
function sortArticles(order) {
    if (!Array.isArray(articles) || articles.length === 0) {
        console.warn("Aucun article à trier !");
        return;
    }

    let sortedArticles = [...articles]; // Copie pour éviter de modifier la liste originale
    if (order === 'asc') {
        sortedArticles.sort((a, b) => a.unit_price - b.unit_price);
    } else if (order === 'desc') {
        sortedArticles.sort((a, b) => b.unit_price - a.unit_price);
    }
    displayArticles(sortedArticles);
}

function displayArticles(articlesList) {
    let container = document.getElementById('articles');
    container.innerHTML = articlesList.map(article => `
        <div class="card">
            <h3>${article.name}</h3>
            <p>${article.description}</p>
            <p><strong>Prix:</strong> ${article.unit_price} €</p>
            <label for="qty-${article.id}">Quantité:</label>
            <input type="number" id="qty-${article.id}" value="1" min="1" max="99" class="qty-selector">
            <button onclick="addToCart(${article.id})">Ajouter</button>
        </div>
    `).join('');
}

async function addToCart(articleId) {
    let qtyInput = document.getElementById(`qty-${articleId}`);
    let quantity = parseInt(qtyInput.value) || 1; 

    await fetch('api.php?action=cart_add', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ article_id: articleId, quantity })
    });

    alert(`Ajouté ${quantity} au panier !`);
}

async function loadCart() {
    let response = await fetch('api.php?action=cart_get');
    let cart = await response.json();
    let container = document.getElementById('cart');
    container.innerHTML = cart.map(item => `
        <div class="cart-item">
            <div>${item.name} (x${item.quantity})</div>
            <div>${item.subtotal} €</div>
        </div>
    `).join('');
}

async function placeOrder() {
    let response = await fetch('api.php?action=order', { method: 'POST' });
    let data = await response.json();
    alert(data.message);
    window.location.href = "order.php";
}
