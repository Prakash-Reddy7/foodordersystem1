function bookTable() {
    const customerName = document.getElementById('customerName').value;
    const phoneNumber = document.getElementById('phoneNumber').value;
    const tableNumber = document.getElementById('tableNumber').value;
    const data = {
        customerName: customerName,
        phoneNumber: phoneNumber,
        tableNumber: tableNumber
    };
    fetch('http://localhost:8080/tables/bookTable', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Table already booked');
        }
    })
    .then(data => {


        console.log(data);
        alert('Table booked successfully');
    })
    .catch(error => {
        console.log('Error: ' + error);
        alert('Table already booked');
    })
}