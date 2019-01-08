var imageFile = document.querySelector("#imageFile");
var submitButton = document.querySelector('[type="submit"]');

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

imageFile.addEventListener("change", getFilename);
submitButton.addEventListener("click", loader);