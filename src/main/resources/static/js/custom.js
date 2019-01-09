var imageFile = document.querySelector("#imageFile");
var targetImg = document.querySelector("#targetImg");
var submitButton = document.querySelector('[type="submit"]');
var backButton = document.querySelector("#backButton");

//gets the filename of the selected file and displays it
function getFilename() {
    document.querySelector('[class="custom-file-label"]').innerHTML = imageFile.value.split("\\").pop();
}

//display selected image
function showImage(imageFile, targetImg) {
    var fr = new FileReader();
    fr.onload = function (e) {
        targetImg.src = this.result;
        targetImg.style.visibility = "visible";
    };
    if (imageFile !== null) {
        imageFile.addEventListener("change", function () {
            fr.readAsDataURL(imageFile.files[0]);
        })
    }
}

showImage(imageFile, targetImg);

//makes the loader visible
function loader() {
    if (imageFile.value !== "" || imageFile.value || undefined) {
        document.querySelector('[class="loader mt-5"]').style.visibility = "visible";
    }
}

//takes back to the previous URL
function goBack() {
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