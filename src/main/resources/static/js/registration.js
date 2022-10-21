function registrationPage() {
    function initButtonHandler() {
        document.querySelector('#registration-button').addEventListener('click', clickOnRegistrationButton);
        document.querySelector('#username-field').addEventListener('input', validateUsername);
    }

    function clickOnRegistrationButton(event) {
        event.preventDefault();
        const username = document.querySelector('#username-field').value;
        const passwordFieldOne = document.querySelector('#password-field').value;
        const passwordFieldTwo = document.querySelector('#password-again-field').value;
        const house = document.querySelector('#house-field').value;
        const petType = document.querySelector('#pet-field').value;

        if (!fieldValidationForRegisterPage(username, passwordFieldOne, passwordFieldTwo, house, petType)) {
            return;
        }

        registrationPost(username, passwordFieldOne, passwordFieldTwo, house, petType);
    }

    function registrationPost(username, password, passwordAgain, house, petType) {
        const registration = fetch(`/registration`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                'username': username, 'password': password, 'passwordAgain': passwordAgain,
                'house': house, 'petType': petType
            })
        })
            .then(response => {
                if (response.redirected) {
                    window.location.href = response.url;
                }
            })
            .catch(reason => console.log(`Error happened: ${reason}`));
    }

    function fieldValidationForRegisterPage(username, passwordFieldOne, passwordFieldTwo, house, petType) {
        if (!checkIfFieldsAreEmpty(username, passwordFieldOne, passwordFieldTwo, house, petType)) {
            alert("All fields must be filled!");
            return false;
        }

        if (!passwordsFieldsAreTheSame(passwordFieldOne, passwordFieldTwo)) {
            alert("Passwords must match!");
            return false;
        }

        if (username === passwordFieldOne){
            alert("Username cannot be the same as password!");
            return false;
        }

        return true;
    }

    function checkIfFieldsAreEmpty(username, passwordFieldOne, passwordFieldTwo, house, petType) {
        return !(username === "" || passwordFieldOne === "" || passwordFieldTwo === "" || house === "noneSelected" || petType === "noneSelected");
    }

    function passwordsFieldsAreTheSame(passwordFieldOne, passwordFieldTwo) {
        return passwordFieldOne === passwordFieldTwo;
    }

    async function validateUsername(event) {
        const inputFieldValue = event.currentTarget.value;
        const pTag = document.querySelector("#valid-field");
        let usernameIsInDatabase = await apiForUsernameValidation(inputFieldValue);

        if (inputFieldValue === "") {
            pTag.classList.add("transparent");
        } else if (!usernameIsInDatabase) {
            pTag.setAttribute("class", "");
            pTag.classList.add("available");
            pTag.innerText = "Username available";
        } else {
            pTag.setAttribute("class", "");
            pTag.classList.add("unavailable");
            pTag.innerText = "Username unavailable";
        }
    }

    async function apiForUsernameValidation(name) {
        if (name === "") {
            return;
        }
        const result = await fetch(`/api/checkUsernameInDatabase/${name}`).then(response => response.json());

        return result;
    }

    initButtonHandler();
}

registrationPage();
