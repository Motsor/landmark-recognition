var imageFile = document.querySelector("#imageFile");
var submitButton = document.querySelector('[type="submit"]');

function getFilename() {
    document.querySelector('[class="custom-file-label"]').innerHTML = imageFile.value.split("\\").pop();
}

function loader() {
    if (imageFile.value !== "" || imageFile.value || undefined) {
        document.querySelector('[class="loader mt-5"]').style.visibility = "visible";
    }
}

imageFile.addEventListener("change", getFilename);
submitButton.addEventListener("click", loader);
