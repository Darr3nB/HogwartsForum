import {utility} from "./utility.js";

function allQuestionsPage() {
    // TODO add input fields to search for specific question
    const listedQuestionsDiv = document.querySelector("#listed-questions");

    function initEventListener() {
        document.querySelector("#filter-search-button").addEventListener('click', filterQuestions);
    }

    async function filterQuestions(event) {
        event.preventDefault();
        const titleFieldValue = document.querySelector("#filter-title").value;
        const descriptionFieldValue = document.querySelector("#filter-description").value;

        let filteredQuestionList = await utility.apiGet(`/api/get-all-questions-filtered/${titleFieldValue}/${descriptionFieldValue}`)
            .then(response => response.json());

        loadQuestions(filteredQuestionList);
    }

    async function loadQuestions(qList = []) {
        listedQuestionsDiv.innerHTML = `<img src="/images/owl.png" alt="Picture of a cute owl." width="300" height="400">`;
        let allQuestions = qList.length <= 0 ?
            await utility.apiGet("/api/get-all-questions").then(response => response.json()) : qList;

        if (allQuestions.length <= 0) {
            listedQuestionsDiv.innerHTML = `<div>There are no asked questions yet!</div>`;
        }

        let stringBuilder = `<div>
                                <table>
                                    <tr>
                                        <tr>Questions: </tr>
                                    </tr>`;

        for (let question of allQuestions) {
            stringBuilder = stringBuilder + `<tr>
                                                <td id="question-id-${question.id}">${question.title} ${question.questionText}</td>
                                            </tr>`;
        }

        stringBuilder = stringBuilder + `   </table>
                                        </div>`;

        listedQuestionsDiv.innerHTML = stringBuilder;

        utility.addEventListenerToQuestions();
    }

    initEventListener();
    loadQuestions();
}

allQuestionsPage()
