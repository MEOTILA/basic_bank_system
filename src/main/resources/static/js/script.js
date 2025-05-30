async function getUserCards() {
    const res = await fetch('/v1/user/userCard');
    const data = await res.json();
    const container = document.getElementById('userCards');
    container.innerHTML = data.map(card => `
        <div>
            <strong> Card Number: ${card.cardNumber}</strong> | Balance: ${card.balance} Rial |
             Withdraw Limit: ${card.withdrawLimitation} Rial
        </div>
    `).join('');
}

document.getElementById('transferForm').addEventListener('submit', async function (e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const payload = Object.fromEntries(formData.entries());

    const res = await fetch('/v1/user/transfer', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            ...payload,
            amount: parseInt(payload.amount),
            expireDate: payload.expireDate
        })
    });
    const result = await res.json();
    document.getElementById('transferResult').innerText = JSON.stringify(result, null, 2);
});

document.getElementById('limitForm').addEventListener('submit', async function (e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const payload = Object.fromEntries(formData.entries());

    const res = await fetch('/v1/user/card/setLimit', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            cardNumber: payload.cardNumber,
            newLimit: parseInt(payload.newLimit)
        })
    });

    if (res.ok) {
        document.getElementById('limitResult').innerText = "New withdraw limitation has been set";
    } else {
        document.getElementById('limitResult').innerText = "Error!";
    }
});

document.getElementById('logoutBtn').addEventListener('click', function() {
    fetch('/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).then(() => {
        window.location.href = '/login?logout=true';
    });
});