// Open and Close Modal Functions
function openVersementModal() {
    document.getElementById('versementModalOverlay').style.display = 'flex';
}

function closeVersementModal() {
    document.getElementById('versementModalOverlay').style.display = 'none';
    document.getElementById('versementForm').reset();
}

function openRetraitModal() {
    document.getElementById('retraitModalOverlay').style.display = 'flex';
}

function closeRetraitModal() {
    document.getElementById('retraitModalOverlay').style.display = 'none';
    document.getElementById('retraitForm').reset();
}

// Submit Versement Form
function submitVersementForm() {
    const accountId = document.getElementById('versementAccountId').value;
    const amount = document.getElementById('versementAmount').value;
    const employeeId = document.getElementById('versementEmployeeId').value;

    fetch('/transactions/versements', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            compteId: accountId,
            montant: amount,
            employeId: employeeId
        })
    })
        .then(response => response.json())
        .then(data => {
            addOperationToTable(data);
            closeVersementModal();
        })
        .catch(error => console.error('Error adding versement:', error));
}

// Submit Retrait Form
function submitRetraitForm() {
    const accountId = document.getElementById('retraitAccountId').value;
    const amount = document.getElementById('retraitAmount').value;
    const employeeId = document.getElementById('retraitEmployeeId').value;

    fetch('/transactions/retraits', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            compteId: accountId,
            montant: amount,
            employeId: employeeId
        })
    })
        .then(response => response.json())
        .then(data => {
            addOperationToTable(data);
            closeRetraitModal();
        })
        .catch(error => console.error('Error adding retrait:', error));
}

// Helper function to add new operation row to table
function addOperationToTable(operation) {
    const newRow = document.createElement('tr');
    newRow.innerHTML = `
        <td>${operation.numOperation}</td>
        <td>${operation.compte.numCompte}</td>
        <td>${operation.employe.nomEmploye}</td>
        <td>${operation.montant}</td>
        <td>${operation.class.simpleName}</td>
        <td>${new Date(operation.dateOperation).toLocaleDateString()}</td>
        <td>
            <button class="btn btn-sm btn-primary">View</button>
            <button class="btn btn-sm btn-danger delete-btn" data-id="${operation.numOperation}">Delete</button>
        </td>
    `;
    document.querySelector('tbody').appendChild(newRow);
}
