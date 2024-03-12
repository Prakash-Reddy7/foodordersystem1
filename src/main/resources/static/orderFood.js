function orderFood() {
    const tableNumber = document.getElementById('tableNumber').value;
    const foodItem = document.getElementById('foodItem').value;
    const quantity = document.getElementById('quantity').value;
    const description = document.getElementById('description').value;

    const data = {
        tableNumber: tableNumber,
       itemName: foodItem,
        quantity: quantity,
        description: description
    };


    fetch('http://localhost:8080/foodItems/createFoodItem', {
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
            throw new Error('Error placing order');
        }
    })
    .then(data => {
        console.log(data);
        alert('Order placed successfully');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error placing order');
    });
}