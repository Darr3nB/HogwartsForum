function indexPage(){
    function initEventListener(){
        document.querySelector("#login-button").addEventListener('click', clickOnLoginButton);
    }

    function clickOnLoginButton(){
        let username = document.querySelector("#username-field").value;
        let password = document.querySelector("#password-field").value;

        console.log(username, password);
        loginPost(username, password)
    }

    function loginPost(username, password){
        const login = fetch(`/login`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({'username': username, 'password': password})
        })
            .then()
            .catch(() => console.log("Something went wrong while trying to login!"));
    }

    initEventListener();
}

indexPage();