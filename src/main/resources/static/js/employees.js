function openModal() {
    document.getElementById('modalOverlay').style.display = 'flex';
}

function closeModal() {
    document.getElementById('modalOverlay').style.display = 'none';
    document.getElementById('employeeForm').reset();
}

function submitEmployeeForm() {
    const employeeName = document.getElementById('employeeName').value;
    const superiorId = document.getElementById('superiorId').value;

    fetch('/employees', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            nomEmploye: employeeName,
            superieur: superiorId ? { codeEmploye: superiorId } : null
        })
    })
        .then(response => response.json())
        .then(data => {
            const newRow = document.createElement('tr');
            newRow.innerHTML = `
            <td>${data.codeEmploye}</td>
            <td>${data.nomEmploye}</td>
            <td>${data.superieur ? data.superieur.codeEmploye : 'N/A'}</td>
            <td>
                <button class="btn btn-sm btn-primary">Edit</button>
                <button class="btn btn-sm btn-danger delete-btn" data-id="${data.codeEmploye}">Delete</button>
            </td>
        `;
            document.getElementById('employeeTableBody').appendChild(newRow);
            closeModal();
        })
        .catch(error => console.error('Error adding employee:', error));
}

