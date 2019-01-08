var backButton = document.querySelector("#backButton");

//takes back to the previous URL
function getBack() {
    window.history.back();
}

backButton.addEventListener("click", getBack);