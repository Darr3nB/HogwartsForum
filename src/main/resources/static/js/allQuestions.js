import {utility} from "./utility.js";

function allQuestionsPage() {
    // TODO add input fields to search for specific question
    const listedQuestionsDiv = document.querySelector("#listed-questions");

    async function loadAllQuestions() {
        listedQuestionsDiv.innerHTML = `<img src="/images/owl.png" alt="Picture of a cute owl." width="300" height="400">`;
        let allQuestions = await utility.apiGet("/api/get-all-questions").then(response => response.json());

        if (allQuestions.length <= 0){
            listedQuestionsDiv.innerHTML = `<div>There are no asked questions yet!</div>`;
        }

        let stringBuilder = `<div>
                                <table>
                                    <tr>
                                        <tr>Questions: </tr>
                                    </tr>`;
        for (let i = 0; i < allQuestions.length; i++){
            stringBuilder = stringBuilder + `<tr>
                                                <td id="question-id-${allQuestions[i].id}">${allQuestions[i].title} ${allQuestions[i].questionText}</td>
                                            </tr>`;
        }
        stringBuilder = stringBuilder + `   </table>
                                        </div>`;

        listedQuestionsDiv.innerHTML = stringBuilder;

        utility.addEventListenerToQuestions();
    }

    loadAllQuestions();
}

allQuestionsPage()