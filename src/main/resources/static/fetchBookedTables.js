
    let currentPage = 0;
    document.addEventListener('DOMContentLoaded', function () {
        fetchBookedTables();
    });
    function fetchBookedTables(page = 0, size = 4) {
        const url = `http://localhost:8080/tables/getAllTablesWithFoodItems?page=${page}&size=${size}`;
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch booked tables with food items');
                }
                return response.json();
            })
            .then(data => {
                displayBookedTables(data.content);
                updatePaginationButtons(data);
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Failed to fetch booked tables with food items');
            });
    }
    function updatePaginationButtons(data) {
        const prevPageBtn = document.getElementById('prevPageBtn');
        const nextPageBtn = document.getElementById('nextPageBtn');
        const currentPageSpan = document.getElementById('currentPage');
        currentPageSpan.textContent = `Page: ${currentPage + 1}`;
        prevPageBtn.disabled = currentPage === 0;
        nextPageBtn.disabled = currentPage === data.totalPages - 1;
    }


    function fetchNextPage() {
        currentPage++;
        fetchBookedTables(currentPage);
    }
    function fetchPreviousPage() {
        currentPage = Math.max(0, currentPage - 1);
        fetchBookedTables(currentPage);
    }
    function displayBookedTables(tablesWithFoodItems) {
        const tbody = document.getElementById('bookedTablesList').getElementsByTagName('tbody')[0];
        tbody.innerHTML = '';
        if (tablesWithFoodItems.length === 0) {
            tbody.innerHTML = '<tr><td colspan="5">No tables booked.</td></tr>';
            return;
        }
        for (const tableWithFoodItems of tablesWithFoodItems) {
            const row = tbody.insertRow(-1);
            const cell1 = row.insertCell(0);
            const cell2 = row.insertCell(1);
            const cell3 = row.insertCell(2);
            const cell4 = row.insertCell(3);
            const cell5 = row.insertCell(4);
            cell1.textContent = tableWithFoodItems.diningTable.id;
            cell2.textContent = tableWithFoodItems.diningTable.customerName;
            cell3.textContent = tableWithFoodItems.diningTable.phoneNumber;
            cell4.textContent = tableWithFoodItems.diningTable.tableNumber;
            const foodItemsList = document.createElement('ul');
            for (const foodItem of tableWithFoodItems.foodItems) {
                const listItem = document.createElement('li');
                listItem.textContent = `${foodItem.itemName} - $${foodItem.price}`;
                foodItemsList.appendChild(listItem);
            }
            cell5.appendChild(foodItemsList);
        }
    }
