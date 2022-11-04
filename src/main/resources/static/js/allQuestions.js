import {utility} from "./utility.js";

function allQuestionsPage() {
    // TODO add input fields to search for specific question
    const listedQuestionsDiv = document.querySelector("#listed-questions");

    function initEventListener() {
        document.querySelector("#filter-search-button").addEventListener('click', filterQuestions);
    }

    async function filterQuestions(event) {
        event.preventDefault();
        let titleFieldValue = document.querySelector("#filter-title").value;
        let descriptionFieldValue = document.querySelector("#filter-description").value;

        if (!allFilterFieldsValidation(titleFieldValue, descriptionFieldValue)) {
            return;
        }

        let filteredQuestionList = await utility.apiGet(`/api/get-all-questions-filtered/?title=${titleFieldValue}&description=${descriptionFieldValue}`)
            .then(response => response.json());

        console.log(filteredQuestionList);
        // TODO uncomment loadQuestions()
        // loadQuestions(filteredQuestionList);
    }

    function allFilterFieldsValidation(titleFieldValue, descriptionFieldValue) {
        return !(titleFieldValue === "" && descriptionFieldValue === "");
    }

    async function loadQuestions(qList = [], pageNumbers = -1) {
        listedQuestionsDiv.innerHTML = `<img src="/images/owl.png" alt="Picture of a cute owl." width="300" height="400">`;
        let pageNumberDiv = document.querySelector("#page-number-div");

        let allQuestions = qList.length <= 0 ?
            await utility.apiGet("/api/get-all-questions").then(response => response.json()) : qList;
        let pages = pageNumbers === -1 ?
            await utility.apiGet("/api/getTotalPageCount").then(response => response.json()) : pageNumbers;

        if (allQuestions.length <= 0) {
            listedQuestionsDiv.innerHTML = `<div>There are no asked questions yet!</div>`;
        }

        let questionsStringBuilder = `<div>
                                <table>
                                    <tr>
                                        <tr>Questions: </tr>
                                    </tr>`;

        for (let question of allQuestions) {
            questionsStringBuilder = questionsStringBuilder + `<tr>
                                                <td id="question-id-${question.id}">${question.title} ${question.questionText}</td>
                                            </tr>`;
        }

        questionsStringBuilder = questionsStringBuilder + `   </table>
                                        </div>`;

        let pageNumbersStringBuilder = ``;

        for (let i = 1; i <= pages; i++) {
            pageNumbersStringBuilder = pageNumbersStringBuilder + `<span id="page-${i}">${i}</span>`;
        }

        listedQuestionsDiv.innerHTML = questionsStringBuilder;
        pageNumberDiv.innerHTML = pageNumbersStringBuilder;

        utility.addEventListenerToQuestions();
    }

    initEventListener();
    loadQuestions();
}

allQuestionsPage()
