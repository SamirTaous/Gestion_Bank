function openModal() {
    document.getElementById('modalOverlay').style.display = 'flex';
}

function closeModal() {
    document.getElementById('modalOverlay').style.display = 'none';
    document.getElementById('clientForm').reset();
}

function submitClientForm() {
    const clientName = document.getElementById('clientName').value;
    const employeeId = document.getElementById('employeeId').value;

    fetch('/clients', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            nom: clientName,
            codeEmploye: employeeId
        })
    })
        .then(response => response.json())
        .then(data => {
            const newRow = document.createElement('tr');
            newRow.innerHTML = `
            <td>${data.code}</td>
            <td>${data.nom}</td>
            <td>${data.employe ? data.employe.nomEmploye : 'N/A'}</td>
            <td>
                <button class="btn btn-sm btn-primary">Edit</button>
                <button class="btn btn-sm btn-danger delete-btn" data-id="${data.code}">Delete</button>
            </td>
        `;
            document.getElementById('clientTableBody').appendChild(newRow);
            closeModal();
        })
        .catch(error => console.error('Error adding client:', error));
}
