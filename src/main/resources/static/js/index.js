function indexPage() {
    function initEventListener() {
        let loginButton = document.querySelector("#login-button");
        if (loginButton) {
            loginButton.addEventListener('click', clickOnLoginButton);
        }
    }

    async function loadQuestions() {
        // TODO add header to list, add id to list elements and display only the 5 latest question
        // TODO add loading gif util promise fulfilled
        let mainPageQuestions = document.querySelector("#main-page-questions");
        let questionList = await apiGetQuestions();

        if (questionList.length <= 0) {
            mainPageQuestions.innerText = "There are no questions yet!";
        }

        let stringBuilder = `<ol>`;

        for (let i = 0; i < questionList.length; i++) {
            stringBuilder = stringBuilder + `<li>${questionList[i].title} ${questionList[i].questionText}`;
        }

        stringBuilder = stringBuilder + `</ol>`;
        mainPageQuestions.innerHTML = stringBuilder;
    }

    async function apiGetQuestions() {
        return await fetch("/api/all-questions").then(response => response.json());
    }

    function clickOnLoginButton(event) {
        event.preventDefault();
        const username = document.querySelector("#username-field").value;
        const password = document.querySelector("#password-field").value;

        if (fieldAreEmpty(username, password)) {
            alert("Both fields must be filled!");
            return;
        }

        loginPost(username, password);
    }

    function loginPost(username, password) {
        const login = fetch(`/login`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({'username': username, 'password': password})
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = "/";
                } else if (response.status === 403) {
                    alert("Invalid login attempt!");
                }
            })
            .catch(reason => console.log(`Error happened: ${reason}`));
    }

    function fieldAreEmpty(username, password) {
        return username === "" || password === "";
    }

    initEventListener();
    loadQuestions();
}

indexPage();
