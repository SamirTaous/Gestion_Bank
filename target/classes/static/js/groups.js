// Open and Close Group Modal Functions
function openGroupModal() {
    document.getElementById('groupModalOverlay').style.display = 'flex';
}

function closeGroupModal() {
    document.getElementById('groupModalOverlay').style.display = 'none';
    document.getElementById('groupForm').reset();
}

// Submit Group Form
function submitGroupForm() {
    const groupName = document.getElementById('groupName').value;

    fetch('/groupes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            nomGroupe: groupName
        })
    })
        .then(response => response.json())
        .then(data => {
            addGroupToTable(data);
            closeGroupModal();
        })
        .catch(error => console.error('Error adding group:', error));
}

// Helper function to add new group row to table
function addGroupToTable(group) {
    const newRow = document.createElement('tr');
    newRow.innerHTML = `
        <td>${group.numGroupe}</td>
        <td>${group.nomGroupe}</td>
        <td>${group.employes ? group.employes.length : 0}</td>
        <td>
            <button class="btn btn-sm btn-primary">Edit</button>
            <button class="btn btn-sm btn-danger delete-btn" data-id="${group.numGroupe}">Delete</button>
        </td>
    `;
    document.getElementById('groupTableBody').appendChild(newRow);
}
