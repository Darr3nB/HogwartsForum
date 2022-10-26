import {utility} from "./utility.js";

function indexPage() {
    function initEventListener() {
        let loginButton = document.querySelector("#login-button");
        if (loginButton) {
            loginButton.addEventListener('click', clickOnLoginButton);
        }
    }

    async function loadQuestions() {
        // TODO add id to table elements
        let mainPageQuestions = document.querySelector("#main-page-questions");
        mainPageQuestions.innerHTML = `<img src="/images/owl.png" alt="Picture of a cute owl." width="300" height="400">`;
        let questionList = await utility.apiGet("/api/all-questions").then(response => response.json());

        if (questionList.length <= 0) {
            mainPageQuestions.innerHTML = `<div>There are no asked questions yet!</div>`;
        }

        let stringBuilder = `<div>
                                <table>
                                     <tr>
                                        <th>Asked question(s)</th>
                                    </tr>`;

        for (let i = 0; i < questionList.length; i++) {
            stringBuilder = stringBuilder + `<tr>
                                                <td>${questionList[i].title} ${questionList[i].questionText}</td>
                                            </tr>`;
        }

        stringBuilder = stringBuilder + `   </table>
                                        </div>`;
        mainPageQuestions.innerHTML = stringBuilder;
    }

    async function clickOnLoginButton(event) {
        event.preventDefault();
        const username = document.querySelector("#username-field").value;
        const password = document.querySelector("#password-field").value;

        if (fieldAreEmpty(username, password)) {
            alert("Both fields must be filled!");
            return;
        }

        await utility.apiPost("/login", {'username': username, 'password': password}).then(response => {
            if (response.status === 403){
                alert("Invalid login attempt!");
            }else if (response.ok){
                window.location.href = "/";
            }
        })
    }

    function fieldAreEmpty(username, password) {
        return username === "" || password === "";
    }

    initEventListener();
    loadQuestions();
}

indexPage();
