function indexPage(){
    function initEventListener(){
        document.querySelector("#login-button").addEventListener('click', clickOnLoginButton);
    }

    function clickOnLoginButton(event){
        event.preventDefault();
        const username = document.querySelector("#username-field").value;
        const password = document.querySelector("#password-field").value;

        if (fieldAreEmpty(username, password)){
            alert("Both fields must be filled!");
            return;
        }

        loginPost(username, password);
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
                if (response.ok){
                    window.location.href = "/";
                }else if (response.status === 403){
                    alert("Invalid login attempt!");
                }
            })
            .catch(reason => console.log(`Error happened: ${reason}`));
    }

    function fieldAreEmpty(username, password){
        return username === "" || password === "";
    }

    initEventListener();
}

indexPage();
