var imageFile = document.querySelector("#imageFile");
var submitButton = document.querySelector('[type="submit"]');
var backButton = document.querySelector("#backButton");

//gets the filename of the selected file and displays it
function getFilename() {
    document.querySelector('[class="custom-file-label"]').innerHTML = imageFile.value.split("\\").pop();
}

//makes the loader visible
function loader() {
    if (imageFile.value !== "" || imageFile.value || undefined) {
        document.querySelector('[class="loader mt-5"]').style.visibility = "visible";
    }
}

//takes back to the previous URL
function goBack() {
    console.log("back up");
    window.history.back();
}

if (imageFile !== null) {
    imageFile.addEventListener("change", getFilename);
}

if (submitButton !== null) {
    submitButton.addEventListener("click", loader);
}

if (backButton !== null) {
    backButton.addEventListener("click", goBack);
}