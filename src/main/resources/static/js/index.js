function indexPage(){
    function initEventListener(){
        document.querySelector("#login-button").addEventListener('click', clickOnLoginButton);
    }

    function clickOnLoginButton(){
        // TODO Check if fields are empty
        const username = document.querySelector("#username-field").value;
        const password = document.querySelector("#password-field").value;

        console.log(username, password);
        //loginPost(username, password)
    }

    function loginPost(username, password){
        const login = fetch(`/login`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({'username': username, 'password': password})
        })
            .then(response => {
                if (response.redirected){
                    window.location.href = response.url;
                }
            })
            .catch(reason => console.log(`Error happened: ${reason}`));
    }

    initEventListener();
}

indexPage();
