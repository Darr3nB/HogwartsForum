import {utility} from "./utility.js";

function indexPage() {
    const loginButton = document.querySelector("#login-button");
    const mainPageQuestions = document.querySelector("#main-page-questions");
    const deleteProfileButton = document.querySelector("#delete-profile-button");

    function initEventListener() {
        if (loginButton) {
            loginButton.addEventListener('click', clickOnLoginButton);
        }
        if (deleteProfileButton) {
            deleteProfileButton.addEventListener('click', clickOnDeleteProfileButton);
        }
    }

    async function clickOnDeleteProfileButton(event) {
        event.preventDefault();
        const profileId = document.querySelector("#profile-id-on-profile-page").innerText;

        await utility.apiGet(`/delete-profile/${+profileId}`).then(response => {
            if (response.status === 403) {
                console.log("An error has happened while trying to delete profile! Please try again later!");
            } else if (response.ok) {
                window.location.href = "/logout";
            }
        });
    }

    async function loadQuestions() {
        mainPageQuestions.innerHTML = `<img src="/images/owl.png" alt="Picture of a cute owl." width="300" height="400">`;
        let questionList = await utility.apiGet("/api/five-latest-question").then(response => response.json());

        if (questionList.length <= 0) {
            mainPageQuestions.innerHTML = `<div>There are no asked questions yet!</div>`;
        }

        let stringBuilder = `<div>
                                <table>
                                     <tr>
                                        <th>Latest question(s)</th>
                                    </tr>`;

        for (let question of questionList) {
            stringBuilder = stringBuilder + `<tr>
                                                <td id="question-id-${question.id}">${question.title} ${question.questionText}</td>
                                            </tr>`;
        }

        stringBuilder = stringBuilder + `   </table>
                                        </div>`;
        mainPageQuestions.innerHTML = stringBuilder;

        utility.addEventListenerToQuestions();
    }

    async function clickOnLoginButton(event) {
        event.preventDefault();
        const username = document.querySelector("#username-field").value;
        const password = document.querySelector("#password-field").value;

        if (fieldAreEmptyOrValid(username, password)) {
            alert("Both fields must be filled!");
            return;
        }

        await utility.apiPost("/login", {'username': username, 'password': password}).then(response => {
            if (response.status === 403) {
                alert("Invalid login attempt!");
            } else if (response.ok) {
                window.location.href = "/";
            }
        })
    }

    function fieldAreEmptyOrValid(username, password) {
        return username === "" || password === "" || username !== "DELETED_USER";
    }

    initEventListener();
    if (mainPageQuestions) {
        loadQuestions();
    }
}

indexPage();
