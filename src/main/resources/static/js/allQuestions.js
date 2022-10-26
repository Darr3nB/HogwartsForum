import {utility} from "./utility.js";

function allQuestionsPage(){
    async function loadAllQuestions(){
        let allQuestions = await utility.apiGet("/api/get-all-questions").then(response => response.json());
        allQuestions.forEach(e => console.log(e));
    }

    loadAllQuestions();
}

allQuestionsPage()