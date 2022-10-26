import {utility} from "./utility.js";

function indexPage() {
    let loginButton = document.querySelector("#login-button");
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
        // TODO for login and reg validation add the new deleted_profile as criteria
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
        // TODO replace question id with user id who posted id
        mainPageQuestions.innerHTML = `<img src="/images/owl.png" alt="Picture of a cute owl." width="300" height="400">`;
        let questionList = await utility.apiGet("/api/all-questions").then(response => response.json());

        if (questionList.length <= 0) {
            mainPageQuestions.innerHTML = `<div>There are no asked questions yet!</div>`;
        }

        let stringBuilder = `<div>
                                <table>
                                     <tr>
                                        <th>Latest question(s)</th>
                                    </tr>`;

        for (let i = 0; i < questionList.length; i++) {
            stringBuilder = stringBuilder + `<tr>
                                                <td id="question-id-${questionList[i].id}">${questionList[i].title} ${questionList[i].questionText}</td>
                                            </tr>`;
        }

        stringBuilder = stringBuilder + `   </table>
                                        </div>`;
        mainPageQuestions.innerHTML = stringBuilder;

        addEventListenerToQuestions();
    }

    function addEventListenerToQuestions() {
        const allQuestion = document.querySelectorAll("td");
        allQuestion.forEach(element => {
            element.addEventListener('click', clickOnSpecificQuestion);
        });
    }

    function clickOnSpecificQuestion(event) {
        const selectedQuestionId = +event.currentTarget.id.replace("question-id-", "");

        utility.apiGet(`/post-question/specific-question/${selectedQuestionId}`).then(response => {
            window.location.href = response.url;
        });
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
            if (response.status === 403) {
                alert("Invalid login attempt!");
            } else if (response.ok) {
                window.location.href = "/";
            }
        })
    }

    function fieldAreEmpty(username, password) {
        return username === "" || password === "";
    }

    initEventListener();
    if (mainPageQuestions) {
        loadQuestions();
    }
}

indexPage();
