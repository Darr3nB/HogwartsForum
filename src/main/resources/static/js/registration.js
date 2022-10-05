function registrationPage(){
    function initButtonHandler(){
        document.querySelector('#registration-button').addEventListener('click', clickOnRegistrationButton);
    }

    function clickOnRegistrationButton(){
        // TODO Check if fields are not empty, and passwords are equal
        const username = document.querySelector('#username-field').value;
        const passwordFieldOne = document.querySelector('#password-field').value;
        const passwordFieldTwo = document.querySelector('#password-again-field').value;
        const house = document.querySelector('#house-field').value;
        const petType = document.querySelector('#pet-field').value;

        registrationPost(username, passwordFieldOne, passwordFieldTwo, house, petType);
    }

    function registrationPost(username, password, house, petType){
        const registration = fetch(`/registration`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({'username': username, 'password': password, 'house': house, 'pet': petType})
        })
            .then(response => {
                if (response.redirected){
                    window.location.href = response.url;
                }
            })
            .catch(reason => console.log(`Error happened: ${reason}`));
    }

    initButtonHandler();
}

registrationPage();
