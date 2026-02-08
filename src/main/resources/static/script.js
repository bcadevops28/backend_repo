const API_BASE_URL = 'http://localhost:8080/api/court-cases';

// DOM Elements
const caseForm = document.getElementById('caseForm');
const resetBtn = document.getElementById('resetBtn');
const refreshBtn = document.getElementById('refreshBtn');
const casesList = document.getElementById('casesList');
const loadingSpinner = document.getElementById('loadingSpinner');
const successMessage = document.getElementById('successMessage');
const errorMessage = document.getElementById('errorMessage');
const filterStatus = document.getElementById('filterStatus');
const filterType = document.getElementById('filterType');
const editModal = document.getElementById('editModal');
const editForm = document.getElementById('editForm');
const closeBtn = document.querySelector('.close');

// Event Listeners
caseForm.addEventListener('submit', handleAddCase);
resetBtn.addEventListener('click', resetForm);
refreshBtn.addEventListener('click', loadCases);
filterStatus.addEventListener('change', applyFilters);
filterType.addEventListener('change', applyFilters);
closeBtn.addEventListener('click', closeModal);
editForm.addEventListener('submit', handleEditCase);

// Load cases on page load
window.addEventListener('load', loadCases);

// Show message
function showMessage(message, type = 'success') {
    const messageEl = type === 'success' ? successMessage : errorMessage;
    const otherEl = type === 'success' ? errorMessage : successMessage;
    
    messageEl.textContent = message;
    messageEl.style.display = 'block';
    otherEl.style.display = 'none';
    
    setTimeout(() => {
        messageEl.style.display = 'none';
    }, 4000);
}

// Hide loading spinner
function setLoading(show) {
    loadingSpinner.style.display = show ? 'block' : 'none';
}

// Load all cases
async function loadCases() {
    setLoading(true);
    try {
        const response = await fetch(API_BASE_URL, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        });
        if (!response.ok) throw new Error('Failed to load cases');
        
        const cases = await response.json();
        displayCases(cases);
        updateStats(cases);
    } catch (error) {
        console.error('Error loading cases:', error);
        showMessage('Failed to load court cases. Make sure the backend is running.', 'error');
        casesList.innerHTML = '<p class="no-cases">Failed to load cases. Please try again.</p>';
    } finally {
        setLoading(false);
    }
}

// Display cases
function displayCases(cases) {
    if (!cases || cases.length === 0) {
        casesList.innerHTML = '<p class="no-cases">No cases found. Add a new case to get started!</p>';
        return;
    }

    casesList.innerHTML = cases.map(caseItem => `
        <div class="case-card">
            <div class="case-header">
                <div class="case-number">${caseItem.caseNumber}</div>
                <span class="case-type ${caseItem.caseType.toLowerCase()}">${caseItem.caseType}</span>
            </div>
            <span class="case-status ${caseItem.status.toLowerCase().replace('_', '-')}">${formatStatus(caseItem.status)}</span>
            <div class="case-description">${caseItem.description || 'No description provided'}</div>
            <div class="case-actions">
                <button class="btn btn-edit" onclick="openEditModal(${caseItem.id})">Edit</button>
                <button class="btn btn-delete" onclick="deleteCase(${caseItem.id})">Delete</button>
            </div>
        </div>
    `).join('');
}

// Format status
function formatStatus(status) {
    return status.replace(/_/g, ' ').replace(/\b\w/g, c => c.toUpperCase());
}

// Update statistics
function updateStats(cases) {
    const total = cases.length;
    const pending = cases.filter(c => c.status === 'PENDING').length;
    const inProgress = cases.filter(c => c.status === 'IN_PROGRESS').length;
    const closed = cases.filter(c => c.status === 'CLOSED').length;

    document.getElementById('totalCases').textContent = total;
    document.getElementById('pendingCases').textContent = pending;
    document.getElementById('inProgressCases').textContent = inProgress;
    document.getElementById('closedCases').textContent = closed;
}

// Handle add case
async function handleAddCase(e) {
    e.preventDefault();

    const newCase = {
        caseNumber: document.getElementById('caseNumber').value,
        caseType: document.getElementById('caseType').value,
        status: document.getElementById('status').value,
        description: document.getElementById('description').value,
        defendantName: document.getElementById('defendantName').value,
        affendantName: document.getElementById('affendantName').value
    };

    console.log('Submitting case:', newCase);

    try {
        const response = await fetch(API_BASE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newCase)
        });

        const responseText = await response.text();
        console.log('Response status:', response.status);
        console.log('Response text:', responseText);

        if (!response.ok) {
            let errorMessage = 'Failed to save court case';
            try {
                const errorJson = JSON.parse(responseText);
                errorMessage = errorJson.error || errorMessage;
            } catch (e) {
                errorMessage = responseText || errorMessage;
            }
            throw new Error(errorMessage);
        }

        showMessage('Court case added successfully!');
        resetForm();
        loadCases();
    } catch (error) {
        console.error('Error adding case:', error);
        showMessage('Failed to save court case: ' + error.message, 'error');
    }
}
    


// Reset form
function resetForm() {
    caseForm.reset();
}

// Apply filters
async function applyFilters() {
    try {
        const response = await fetch(API_BASE_URL);
        if (!response.ok) throw new Error('Failed to load cases');
        
        let cases = await response.json();
        const selectedStatus = filterStatus.value;
        const selectedType = filterType.value;

        if (selectedStatus) {
            cases = cases.filter(c => c.status === selectedStatus);
        }
        if (selectedType) {
            cases = cases.filter(c => c.caseType === selectedType);
        }

        displayCases(cases);
    } catch (error) {
        console.error('Error applying filters:', error);
        showMessage('Failed to apply filters', 'error');
    }
}

// Delete case
async function deleteCase(id) {
    if (!confirm('Are you sure you want to delete this case?')) return;

    try {
        const response = await fetch(`${API_BASE_URL}/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) throw new Error('Failed to delete case');

        showMessage('Case deleted successfully!');
        loadCases();
    } catch (error) {
        console.error('Error deleting case:', error);
        showMessage('Failed to delete case: ' + error.message, 'error');
    }
}

// Open edit modal
async function openEditModal(id) {
    try {
        const response = await fetch(`${API_BASE_URL}/${id}`);
        if (!response.ok) throw new Error('Failed to load case');
        
        const caseItem = await response.json();
        
        document.getElementById('editCaseId').value = caseItem.id;
        document.getElementById('editCaseNumber').value = caseItem.caseNumber;
        document.getElementById('editCaseType').value = caseItem.caseType;
        document.getElementById('editStatus').value = caseItem.status;
        document.getElementById('editDescription').value = caseItem.description || '';
        
        editModal.style.display = 'flex';
    } catch (error) {
        console.error('Error loading case:', error);
        showMessage('Failed to load case details', 'error');
    }
}

// Close modal
function closeModal() {
    editModal.style.display = 'none';
}

// Close modal when clicking outside
window.addEventListener('click', (e) => {
    if (e.target === editModal) {
        closeModal();
    }
});

// Handle edit case
async function handleEditCase(e) {
    e.preventDefault();

    const id = document.getElementById('editCaseId').value;
    const updatedCase = {
        caseNumber: document.getElementById('editCaseNumber').value,
        caseType: document.getElementById('editCaseType').value,
        status: document.getElementById('editStatus').value,
        description: document.getElementById('editDescription').value
    };

    try {
        const response = await fetch(`${API_BASE_URL}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updatedCase)
        });

        if (!response.ok) throw new Error('Failed to update case');

        showMessage('Case updated successfully!');
        closeModal();
        loadCases();
    } catch (error) {
        console.error('Error updating case:', error);
        showMessage('Failed to update case: ' + error.message, 'error');
    }
}