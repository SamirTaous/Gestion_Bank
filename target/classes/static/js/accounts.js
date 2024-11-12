function openAccountModal() {
    document.getElementById('accountModalOverlay').style.display = 'flex';
}

function closeAccountModal() {
    document.getElementById('accountModalOverlay').style.display = 'none';
    document.getElementById('accountForm').reset();
}

function submitAccountForm() {
    const code = document.getElementById('code').value;
    const accountType = document.getElementById('accountType').value;
    const balance = document.getElementById('balance').value;

    fetch('/accounts', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            codeClient: code, // This part should correctly map to the client in the backend
            type: accountType,
            solde: balance
        })
    })
        .then(response => response.json())
        .then(data => {
            if (data.client) {  // Check if client data exists
                const newRow = document.createElement('tr');
                newRow.innerHTML = `
                <td>${data.numCompte}</td>
                <td>${data.client.nom}</td> <!-- client name should now be available -->
                <td>${data.class.simpleName}</td>
                <td>${new Date(data.dateCreation).toISOString().split('T')[0]}</td> <!-- Format date as yyyy-MM-dd -->
                <td>${data.solde}</td>
                <td>
                    <button class="btn btn-sm btn-primary">Edit</button>
                    <button class="btn btn-sm btn-danger delete-btn" data-id="${data.numCompte}">Delete</button>
                </td>
            `;
                document.getElementById('accountTableBody').appendChild(newRow);
                closeAccountModal();
            } else {
                console.error("Client data is missing in the response.");
            }
            closeAccountModal();
        })
        .catch(error => console.error('Error adding account:', error));

}
