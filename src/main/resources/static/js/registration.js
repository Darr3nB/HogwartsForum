function registrationPage() {
    function initButtonHandler() {
        document.querySelector('#registration-button').addEventListener('click', clickOnRegistrationButton);
        document.querySelector('#username-field').addEventListener('input', validateUsername)
    }

    function clickOnRegistrationButton() {
        const username = document.querySelector('#username-field').value;
        const passwordFieldOne = document.querySelector('#password-field').value;
        const passwordFieldTwo = document.querySelector('#password-again-field').value;
        const house = document.querySelector('#house-field').value;
        const petType = document.querySelector('#pet-field').value;

        if (!checkIfFieldsAreEmpty(username, passwordFieldOne, passwordFieldTwo, house, petType)) {
            alert("All fields must be filled!");
            return;
        }

        if (!passwordsFieldsAreTheSame(passwordFieldOne, passwordFieldTwo)) {
            alert("Passwords must match!");
            return;
        }

        registrationPost(username, passwordFieldOne, house, petType);
    }

    function registrationPost(username, password, house, petType) {
        const registration = fetch(`/registration`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({'username': username, 'password': password, 'house': house, 'petType': petType})
        })
            .then(response => {
                if (response.redirected) {
                    window.location.href = response.url;
                }
            })
            .catch(reason => console.log(`Error happened: ${reason}`));
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
            pTag.classList.add("p-transparent");
        } else if (!usernameIsInDatabase) {
            pTag.setAttribute("class", "");
            pTag.classList.add("p-green");
            pTag.innerText = "Username available"
        } else {
            pTag.setAttribute("class", "");
            pTag.classList.add("p-red");
            pTag.innerText = "Username unavailable"
        }
    }

    async function apiForUsernameValidation(name) {
        if (name === "") {
            return;
        }
        const result = await fetch(`/api/checkUsernameInDatabase/${name}`).then(response => response.json())

        return result;
    }

    initButtonHandler();
}

registrationPage();
