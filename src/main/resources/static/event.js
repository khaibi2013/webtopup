function validate(){

    var selectedDate = document.getElementById("dateDue").value;


    if (selectedDate < Date.now()) {
        alert("The Date must be Bigger or Equal to today date")
        return false;
    }
    return true;
}


