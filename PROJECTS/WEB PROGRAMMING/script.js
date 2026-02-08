function validateForm() {
    alert("Form submitted successfully!");
    return true;
}

function calculateTotal() {
    let m1 = parseInt(document.getElementById("m1").value) || 0;
    let m2 = parseInt(document.getElementById("m2").value) || 0;
    let m3 = parseInt(document.getElementById("m3").value) || 0;

    document.getElementById("total").value = m1 + m2 + m3;
}
